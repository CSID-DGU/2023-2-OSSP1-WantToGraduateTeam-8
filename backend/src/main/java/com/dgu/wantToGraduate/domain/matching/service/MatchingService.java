package com.dgu.wantToGraduate.domain.matching.service;


import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.repository.PreferTable;
import com.dgu.wantToGraduate.domain.type.WaitUser;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
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

    private final UserRepository userRepository;
    private ConcurrentSkipListSet<WaitUser> waitUsers = new ConcurrentSkipListSet<>(
            Comparator.comparing(WaitUser::getGrade, Comparator.reverseOrder())
                    .thenComparing(wu -> wu.getUser().getId(), Comparator.reverseOrder())
    );
    //단순 대기열. 매칭 대기하는 모든 유저
    private PreferTable preferTable = new PreferTable();
    //key: UserId, value: 해당 User의 모든 다른 User의 리스트(sameNum, grade로 정렬)

    private BloomFilter<Long> finishList = BloomFilter.create(
            Funnels.longFunnel(),
            1000,
            0.01
    );

    private List<User> failList = new ArrayList<>();

    public MatchingDto.ResponseDto matching(MatchingDto.RequestDto selectInfo) {
        makePreferTable(selectInfo);
        MatchingDto.ResponseDto matchingResult = null;
        if(waitUsers.size() > 4) {
            matchingResult = match();
        }
        return matchingResult;
    }

    private List<Integer> calculateSameNumAndPriority(WaitUser waitUser1, WaitUser waitUser2) {
        List<String> brandNameList1 = waitUser1.getPreferBrand().stream()
                .map(MatchingDto.RequestDto.SelectDto::getBrandName)
                .collect(Collectors.toList());
        List<String> brandNameList2 = waitUser2.getPreferBrand().stream()
                .map(MatchingDto.RequestDto.SelectDto::getBrandName)
                .collect(Collectors.toList());
        List<Integer> sameNumAndPriority = new ArrayList<>();
        List<String> intersection = new ArrayList<>(brandNameList1);
        intersection.retainAll(brandNameList2);
        int prioirty = 0;
        for(String brandName : intersection) {
            prioirty += Math.abs(waitUser1.findPriorityByBrandName(brandName) - waitUser2.findPriorityByBrandName(brandName));
        }
        sameNumAndPriority.add(intersection.size());
        sameNumAndPriority.add(prioirty);

        return sameNumAndPriority;
    }

    //RequestDto 받아서 선호도 테이블 작성
    private void makePreferTable(MatchingDto.RequestDto selectInfo) {
        //받은 정보들로 waitUser 빌드
        User insertUser = userRepository.findById(selectInfo.getUserId()).orElseThrow();
        WaitUser insertWaitUser = WaitUser.builder()
                .user(insertUser)
                .sameNum(0)
                .grade(insertUser.getGrade())
                .preferBrand(selectInfo.getPreferBrandList())
                .build();
        waitUsers.add(insertWaitUser);
        for(WaitUser waitUser : waitUsers){
            if(selectInfo.getUserId() == waitUser.getUser().getId()) continue;
            List<Integer> sameNumAndPriority = calculateSameNumAndPriority(waitUser, insertWaitUser);
            WaitUser preferTableUser = new WaitUser(waitUser);
            WaitUser insertPreferTableUser = new WaitUser(insertWaitUser);
            preferTableUser.setSameNum(sameNumAndPriority.get(0));
            insertPreferTableUser.setSameNum(sameNumAndPriority.get(0));
            preferTableUser.setPriority(sameNumAndPriority.get(1));
            insertPreferTableUser.setPriority(sameNumAndPriority.get(1));
            preferTable.addUser(insertWaitUser.getUser().getId(), preferTableUser);
            preferTable.addUser(waitUser.getUser().getId(), insertPreferTableUser);
        }
    }

    private MatchingDto.ResponseDto match() {
        for(WaitUser waitUser : waitUsers){
            if(!finishList.mightContain(waitUser.getUser().getId())) {
                List<User> mateData = new ArrayList<>();
                mateData.add(waitUser.getUser());
                for (WaitUser mateUser : preferTable.getWaitUser(waitUser.getUser().getId())) {
                    if (!finishList.mightContain(mateUser.getUser().getId())) {
                        if (mateData.size() >= 3) break;
                        mateData.add(mateUser.getUser());
                    }
                }
                if (mateData.size() > 2) { //결과 반환
                    for (User user : mateData) {
                        finishList.put(user.getId());
                    }
                    return convertToDto(mateData);
                } else {
                    failList.add(waitUser.getUser());
                    break;
                }
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
