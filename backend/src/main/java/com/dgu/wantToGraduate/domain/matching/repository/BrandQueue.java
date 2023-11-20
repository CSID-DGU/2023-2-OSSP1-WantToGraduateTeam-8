package com.dgu.wantToGraduate.domain.matching.repository;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto.ResponseDto;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
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
        private static ConcurrentSkipListSet<Brand> brandQueue = new ConcurrentSkipListSet<>();
        private static ConcurrentHashMap<Long, ConcurrentSkipListSet<User>> storeUsers = new ConcurrentHashMap<>();

        public void addBrand(Brand brand){
            this.storeUsers.putIfAbsent(brand.getId(), new ConcurrentSkipListSet<>(sortUserBy(brand.getId())));
        }
        public ResponseDto addUser(Long userId, Long brandId) {

            Optional<User> userEntity = userRepository.findById(userId);
            if(!userEntity.isPresent()){
                throw new IllegalArgumentException("[예외발생] 존재하지 않는 유저입니다.");
            }
            storeUsers.get(brandId).add(userEntity.get());
//            userRepository.findById(userId).ifPresent(user -> {
//                storeUsers.get(brandId).add(user);
//            });
            showBrandQueue(); //TEST: 추가될 때마다 storeUsers 내용 확인( before match)

            /**
             * 매장 대기열에 유저 삽입 후, 매칭 성공 여부 확인
             * 성공 : 매칭된 유저들 반환 + 매칭된 유저들 모든 대기열에서 삭제
             * 실패 : 빈 리스트 반환
             */
            List<User> matchedUsers = executeMatching(brandId);
            log.info("[###########] matchedUsers: {}", matchedUsers);
            if(matchedUsers!=null){
                ResponseDto matchingResult = ResponseDto.builder()
                        .brandId(brandId)
                        .matchList(matchedUsers.stream()
                                .map(user -> ResponseDto.MatchDto.builder()
                                        .nickname(user.getNickname())
                                        .build())
                                .collect(Collectors.toList()))
                        .build();
                log.info("[###########] matchingResult: {}", matchingResult);
                return matchingResult;
            }
            return null;
        }


        /*선택한 매장 대기열에 유저 삽입(정렬 기준. 1st:우선순위 2nd:타임스탬프)*/
        private Comparator<User> sortUserBy(Long brandId){
            return new Comparator<User>(){
                @Override
                public int compare(User u1, User u2) {
                    int priorityCompare = Integer.compare(getPriority(u1,brandId), getPriority(u2, brandId));
                    if(priorityCompare != 0){
                        return priorityCompare;
                    }
                    return getTimestamp(u1, brandId).compareTo(getTimestamp(u2, brandId));
                }
            };
        }
        private int getPriority(User user, Long brandId) {
            Optional<PreferBrand> preferBrandOpt = user.getPreferBrandList().stream()
                    .filter(pb -> pb.getBrand().getId().equals(brandId))
                    .findFirst();
            return preferBrandOpt.map(PreferBrand::getPriority).orElseThrow(()
                    -> new IllegalArgumentException("[예외발생] 우선순위 정보가 없습니다."));
        }

        private LocalDateTime getTimestamp(User user, Long brandId) {
            Optional<PreferBrand> preferBrandOpt = user.getPreferBrandList().stream()
                    .filter(pb -> pb.getBrand().getId().equals(brandId))
                    .findFirst();
            return preferBrandOpt.map(PreferBrand::getCreateAt).orElseThrow(()
                    -> new IllegalArgumentException("[예외발생] 타임스탬프 정보가 없습니다."));
    }

    private List<User> executeMatching(Long brandId){
        if(storeUsers.get(brandId).size() >= 3){
            log.info("{}매장에서 매칭 성공", brandId);
            //TODO: 매칭 성공한 유저들 반환
            List<User> matchedUsers = storeUsers.get(brandId).stream()
                    .limit(3)
                    .collect(Collectors.toList());
            //모든 매장의 대기열에서 매칭된 유저들 제거
            storeUsers.forEach((key, value) -> {
                value.removeAll(matchedUsers);
            });
            log.info("[executeMatching] matchedUsers: {}", matchedUsers);
            return matchedUsers;
        }else{
            return null;
        }
    }

    private void showBrandQueue(){
        storeUsers.forEach((brandId, usersSet) -> {
            String usersInfo = usersSet.stream()
                    .map(user -> user.getId().toString()) // 또는 user.getNickname() 등 다른 식별자를 사용할 수 있음
                    .collect(Collectors.joining(", ", "[", "]"));

            log.info("Brand ID: {}, Users: {}", brandId, usersInfo);
        });

    }
}
