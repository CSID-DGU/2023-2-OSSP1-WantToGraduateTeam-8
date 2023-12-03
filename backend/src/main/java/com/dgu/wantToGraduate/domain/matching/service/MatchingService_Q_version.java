package com.dgu.wantToGraduate.domain.matching.service;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.brand.repository.BrandRepository;
import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto.RequestDto;
import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto.RequestDto.SelectDto;
//import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto.ResponseDto.MatchDto;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import com.dgu.wantToGraduate.domain.matching.repository.BrandQueue;
import com.dgu.wantToGraduate.domain.matching.repository.PreferBrandRepository;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j


public class MatchingService_Q_version {
    public static int totalScore = 0;
    public static int totalGroupCnt=0;

    private final BrandQueue brandQueue;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final PreferBrandRepository preferBrandRepository;

    public MatchingDto.ResponseDto matching(RequestDto selectInfo){
        totalGroupCnt++; // 3000명 카운팅

        MatchingDto.ResponseDto matchingResult = null;

        log.info("[MatchingService] 진입");
        User userEntity = userRepository.findById(selectInfo.getUserId()).orElseThrow(()
                -> new IllegalArgumentException("[예외발생]존재하지 않는 유저입니다."));
        log.info("[MatchingService] userEntity: {}", userEntity);

        Map<Integer,Brand> brandList = new HashMap();
        /*매장 대기열 생성*/
        for(SelectDto selectDto : selectInfo.getPreferBrandList()){
            log.info("[MatchingService] selectDto: {}", selectDto);
            PreferBrand build = PreferBrand.builder()
                    .user(userEntity)
                    .brand(brandRepository.findByBrandName(selectDto.getBrandName()))
                    .priority(selectDto.getPriority())
                    .build();
            log.info("[MatchingService] build: {}", build);
            preferBrandRepository.save(build);
        }

        for(SelectDto selectDto : selectInfo.getPreferBrandList()){
            generateBrandQueue(selectDto); // 매장 큐 생성
            log.info("[MatchingService] brandQueue: {}", brandQueue);
            matchingResult = insertIntoBrandQueue(selectDto, selectInfo.getUserId());// 매장 큐에 유저 삽입
            if(matchingResult != null){
                break;
            }
            log.info("[MatchingService] brandQueue: {}", brandQueue);
        }
        log.info("[@최종반환값@] matchingResult: {}", matchingResult);
        return matchingResult;
    }

    private MatchingDto.ResponseDto insertIntoBrandQueue(SelectDto selectDto, Long userId) {
        log.info("[MatchingService] insertIntoBrandQueue 진입");
        Long brandId = brandRepository.findBrandIdByBrandName(selectDto.getBrandName())
                .orElseThrow(() -> new IllegalArgumentException("[예외발생]해당 브랜드가 없습니다."));
        MatchingDto.ResponseDto matchingResult = brandQueue.addUser(userId, brandId, selectDto.getPriority());
        log.info("[MatchingService] matchingResult: {}", matchingResult);
        return matchingResult;
    }

    private void generateBrandQueue(SelectDto brand){
        log.info("[MatchingService] generateBrandQueue 진입");
        Brand brandEntity = brandRepository.findByBrandName(brand.getBrandName());
        log.info("[MatchingService] brandEntity: {}", brandEntity);
        if(brandEntity!= null){
            brandQueue.addBrand(brandEntity); //처음 선택된 매장은 대기열을 생성합니다. (이미 대기열이 있는 매장은 생성되지 않습니다.)
        }else {
            throw new IllegalArgumentException("[예외발생]해당 브랜드가 없습니다.");
        }
    }


    public void testEvaluatePriority(MatchingDto.ResponseDto matchingResult){


        int groupScore=0;
        int maxScore=0;
        List<String> matchList = matchingResult.getMatchList();
        maxScore += matchList.size() * 3; // 최대 점수 계산(매칭그룹 * 3 : 항상 3명 매칭 가정)

        for(String matchedNickname : matchList){
            String nickname = matchedNickname;
            List<PreferBrand>user_preferBrandList = preferBrandRepository.findByUserNickname(nickname);

            for(PreferBrand user_preferBrand : user_preferBrandList){
                if(user_preferBrand.getBrand().getId() == matchingResult.getBrandId()){
                    if(user_preferBrand.getPriority()==1) groupScore+=3;
                    else if(user_preferBrand.getPriority()==2) groupScore+=2;
                    else if(user_preferBrand.getPriority()==3) groupScore+=1;
                }
            }
        }

        double percentage = (double) groupScore / maxScore * 100;
        log.info("[그룹당 우선순위 반영 비율] percentage per group: {}", percentage);
        totalScore += groupScore;
    }
}