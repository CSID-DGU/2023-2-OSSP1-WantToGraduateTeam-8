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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchingService {
    private final BrandRepository brandRepository;

    private final UserRepository userRepository;
    private List<WaitUser> waitUsers = new ArrayList<>();
    //단순 대기열. 매칭 대기하는 모든 유저
    private PreferTable preferTable = new PreferTable();
    //key: UserId, value: 해당 User의 모든 다른 User의 리스트(sameNum, grade로 정렬)
    public void matching(MatchingDto.RequestDto selectInfo) {
        makePreferTable(selectInfo);

        //todo: 정렬 기준대로 잘 정렬되나 확인
        //todo: 선호도 테이블 작성 구현 완료!!(아마..? 좀 더 테스팅+디버깅) 나머지 구현 해야됨. 상황 주혁이형한테 공유
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
        List<String> brandNameList = selectInfo.getPreferBrandList();
        User insertUser = userRepository.findById(selectInfo.getUserId()).orElseThrow();
        List<Long> brandIdList = brandNameList.stream()
                .map(brandRepository::findByBrandName)
                .map(Brand::getId)
                .collect(Collectors.toList());
        WaitUser insertWaitUser = WaitUser.builder()
                .user(insertUser)
                .grade(insertUser.getGrade())
                .brandList(brandIdList)
                .build();
        waitUsers.add(insertWaitUser);
        for(WaitUser waitUser : waitUsers){
            if(selectInfo.getUserId() == waitUser.getUser().getId()) continue;
            int sameNum = calculateSameNum(waitUser.getBrandList(), brandIdList);
            WaitUser preferTableUser = new WaitUser(waitUser);
            preferTableUser.setSameNum(sameNum);
            insertWaitUser.setSameNum(sameNum);
            preferTable.addUser(insertWaitUser.getUser().getId(), preferTableUser);
            preferTable.addUser(waitUser.getUser().getId(), insertWaitUser);
        }
    }
}
