package com.dgu.wantToGraduate.domain.matching.repository;


import com.dgu.wantToGraduate.domain.type.WaitUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@AllArgsConstructor
@Slf4j
public class PreferTable {

    private static ConcurrentHashMap<Long, ConcurrentSkipListSet<WaitUser>> preferTable = new ConcurrentHashMap<>();

    public void addUser(Long key, WaitUser waitUser) {
        preferTable.compute(key, (k, waitUsers) -> {
            if(waitUsers == null){
                waitUsers = new ConcurrentSkipListSet<>(
                        Comparator.comparing(WaitUser::getSameNum, Comparator.reverseOrder())
                                .thenComparing(WaitUser::getPriority)
                                .thenComparing(WaitUser::getGrade, Comparator.reverseOrder())
                                .thenComparing(wu -> wu.getUser().getId(), Comparator.reverseOrder())
                );
            }
            waitUsers.add(waitUser);
            return waitUsers;
        });
    }

    public ConcurrentSkipListSet<WaitUser> getWaitUser(Long key) {
        return preferTable.get(key);
    }
}
