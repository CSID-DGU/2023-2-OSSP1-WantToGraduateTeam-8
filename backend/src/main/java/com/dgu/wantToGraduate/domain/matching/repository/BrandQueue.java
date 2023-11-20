package com.dgu.wantToGraduate.domain.matching.repository;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.SortedSet;
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
            this.storeUsers.putIfAbsent(brand.getId(), new ConcurrentSkipListSet<>(sortUserBy(brand.getId()))); //같은 키값에 해당하는 value이면 no action( 다중 스레드 환경에서 동일 값 삽입 방지 )

        }

        //TODO: 매장 대기열에 유저 정렬기준 세워 넣는 작업 필요
        public void addUser(Long userId, Long brandId) {

            userRepository.findById(userId).ifPresent(user -> {
                storeUsers.get(brandId).add(user);
            });


            //TEST: 추가될 때마다 storeUsers 내용 확인
            showBrandQueue();
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

    private void showBrandQueue(){
        storeUsers.forEach((brandId, usersSet) -> {
            String usersInfo = usersSet.stream()
                    .map(user -> user.getId().toString()) // 또는 user.getNickname() 등 다른 식별자를 사용할 수 있음
                    .collect(Collectors.joining(", ", "[", "]"));

            log.info("Brand ID: {}, Users: {}", brandId, usersInfo);
        });

    }
}
