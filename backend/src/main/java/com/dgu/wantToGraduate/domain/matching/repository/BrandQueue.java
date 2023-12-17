package com.dgu.wantToGraduate.domain.matching.repository;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.brand.repository.BrandRepository;
import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto.ResponseDto;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import com.dgu.wantToGraduate.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class BrandQueue {

        private final UserRepository userRepository;
        private final UserService userService;
        private final PreferBrandRepository preferBrandRepository;
        private static ConcurrentSkipListSet<Brand> brandQueue = new ConcurrentSkipListSet<>();
        private static ConcurrentHashMap<Long, ConcurrentSkipListSet<WaitUser>> storeUsers = new ConcurrentHashMap<>();

        public void addBrand(Brand brand){
            this.storeUsers.putIfAbsent(brand.getId(), new ConcurrentSkipListSet<>(Comparator.comparing(WaitUser::getPriority)
                    .thenComparing(WaitUser::getTimeStamp)));
        }
        public ResponseDto addUser(Long userId, Long brandId, int priority) {

            Optional<User> userEntity = userRepository.findById(userId);
            if (!userEntity.isPresent()) {
                throw new IllegalArgumentException("[예외발생] 존재하지 않는 유저입니다.");
            }
            boolean curMatchFlag = userEntity.get().isMatched();
            //TODO: toggle메서드가 내 생각대로 동작하지 않는 것 같음. 수정 필요🧑‍💻
            if (!curMatchFlag) {
                //TODO: PreferBrand는 없는데, 그에 반해 User는 남아있기에 NullPointException 발생
                LocalDateTime timeStamp = preferBrandRepository.findByUserAndBrandId(userEntity.get(), brandId).getCreateAt();
                WaitUser waitUser = new WaitUser(userEntity.get(), priority, timeStamp);
                storeUsers.get(brandId).add(waitUser);

                showBrandQueue(); //TEST: 추가될 때마다 storeUsers 내용 확인( before match)

                List<WaitUser> matchedUsers = executeMatching(brandId);
                log.info("[###########] matchedUsers: {}", matchedUsers);
                if (matchedUsers != null) {
                    ResponseDto matchingResult = ResponseDto.builder()
                            .brandId(brandId)
                            .matchList(matchedUsers.stream()
                                    .map(WaitUser::getUser)
                                    .map(User::getNickname)
                                    .collect(Collectors.toList()))
                            .build();
                    log.info("[###########] matchingResult: {}", matchingResult);
                    return matchingResult;
                }
            }
            return null;
        }


    private List<WaitUser> executeMatching(Long brandId){
        if(storeUsers.get(brandId).size() >= 3){
            log.info("{}매장에서 매칭 성공", brandId);
            //TODO: 매칭 성공한 유저들 반환
            List<WaitUser> matchedUsers = storeUsers.get(brandId).stream()
                    .limit(3)
                    .collect(Collectors.toList());

            //매칭된 유저 PreferBrand 에서 삭제
            matchedUsers.forEach(matchedUser -> {
                userService.toggleUserMatchFlag(matchedUser.getUser().getId());
                log.info("[Delete Test Log] : userId is {} - brandId is {}", matchedUser.getUser().getId(), brandId);
                preferBrandRepository.deleteByUser(matchedUser.getUser().getId());
            });

            for (Long key : storeUsers.keySet()) {
                List<Long> removeUserIds = matchedUsers.stream()
                        .map(WaitUser::getUser)
                        .map(User::getId)
                        .collect(Collectors.toList());
                ConcurrentSkipListSet<WaitUser> waitUsers = storeUsers.get(key);
                waitUsers.removeIf(waitUser -> removeUserIds.contains(waitUser.getUser().getId()));
                log.info("{}의 현재 대기열 출력 {}", key, storeUsers.get(key));
            }
            log.info("[executeMatching] matchedUsers: {}", matchedUsers);
            return matchedUsers;
        }else{
            return null;
        }
    }

    private void showBrandQueue(){
        storeUsers.forEach((brandId, usersSet) -> {
            String usersInfo = usersSet.stream()
                    .map(user -> user.getUser().getId().toString()) // 또는 user.getNickname() 등 다른 식별자를 사용할 수 있음
                    .collect(Collectors.joining(", ", "[", "]"));

            log.info("Brand ID: {}, Users: {}", brandId, usersInfo);
        });

    }
}
