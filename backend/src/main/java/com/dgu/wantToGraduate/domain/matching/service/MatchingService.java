package com.dgu.wantToGraduate.domain.matching.service;


import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.brand.repository.BrandRepository;
import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.repository.PreferTable;
import com.dgu.wantToGraduate.domain.type.WaitUser;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchingService {
    private final BrandRepository brandRepository;

    private final UserRepository userRepository;
    private ConcurrentSkipListSet<WaitUser> waitUsers = new ConcurrentSkipListSet<>(
            Comparator.comparing(WaitUser::getGrade, Comparator.reverseOrder())
                    .thenComparing(wu -> wu.getUser().getId(), Comparator.reverseOrder())
    );
    //단순 대기열. 매칭 대기하는 모든 유저
    private PreferTable preferTable = new PreferTable();
    //key: UserId, value: 해당 User의 모든 다른 User의 리스트(sameNum, grade로 정렬)

    private List<User> finishList = new ArrayList<>();

    private List<User> failList = new ArrayList<>();

    public MatchingDto.ResponseDto matching(MatchingDto.RequestDto selectInfo) {
        makePreferTable(selectInfo);
        MatchingDto.ResponseDto matchingResult = null;
        if(waitUsers.size() > 6) {
            matchingResult = match();
        }
        return matchingResult;
        //todo: matching 결과를 mate들의 list로?
        //todo: sameNum 초기화 문제 해결
    }

    //Brand Id 리스트 두 개로 sameNum 계산
    private int calculateSameNum(List<Long> preferBrandList1, List<Long> preferBrandList2) {
        List<Long> intersection = new ArrayList<>(preferBrandList1);
        intersection.retainAll(preferBrandList2);
        int sameNum = intersection.size();

        return sameNum;
    }

    //RequestDto 받아서 선호도 테이블 작성
    private void makePreferTable(MatchingDto.RequestDto selectInfo) {
        //받은 정보들로 waitUser 빌드
        List<String> brandNameList = selectInfo.getPreferBrandList().stream()
                .map(MatchingDto.RequestDto.SelectDto::getBrandName)
                .collect(Collectors.toList());
        User insertUser = userRepository.findById(selectInfo.getUserId()).orElseThrow();
        List<Long> brandIdList = brandNameList.stream()
                .map(brandRepository::findByBrandName)
                .map(Brand::getId)
                .collect(Collectors.toList());
        WaitUser insertWaitUser = WaitUser.builder()
                .user(insertUser)
                .sameNum(0)
                .grade(insertUser.getGrade())
                .brandList(brandIdList)
                .build();
        waitUsers.add(insertWaitUser);
        for(WaitUser waitUser : waitUsers){
            if(selectInfo.getUserId() == waitUser.getUser().getId()) continue;
            int sameNum = calculateSameNum(waitUser.getBrandList(), brandIdList);
            WaitUser preferTableUser = new WaitUser(waitUser);
            WaitUser insertPreferTableUser = new WaitUser(insertWaitUser);
            preferTableUser.setSameNum(sameNum);
            insertPreferTableUser.setSameNum(sameNum);
            preferTable.addUser(insertWaitUser.getUser().getId(), preferTableUser);
            preferTable.addUser(waitUser.getUser().getId(), insertPreferTableUser);
        }
    }

    private MatchingDto.ResponseDto match() {
        for(WaitUser waitUser : waitUsers){
            if(finishList.contains(waitUser.getUser())) continue;
            List<User> mateData = new ArrayList<>();
            mateData.add(waitUser.getUser());
            for(WaitUser mateUser : preferTable.getWaitUser(waitUser.getUser().getId())){
                if(finishList.contains(mateUser.getUser())) continue;
                if(mateData.size() >= 3) break;
                mateData.add(mateUser.getUser());
            }
            if(mateData.size() > 2) { //결과 반환
                return convertToDto(mateData);
            } else {
                failList.add(waitUser.getUser());
                break;
            }
        }
        return null;
    }

    private MatchingDto.ResponseDto convertToDto(List<User> mateData) {
        List<String> mateNicknameList = mateData.stream()
                .map(User::getNickname)
                .collect(Collectors.toList());
        MatchingDto.ResponseDto matchingResult = MatchingDto.ResponseDto.builder()
                .matchList(mateNicknameList)
                .build();

        return matchingResult;
    }
}
