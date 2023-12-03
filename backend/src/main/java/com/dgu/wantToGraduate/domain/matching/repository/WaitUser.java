package com.dgu.wantToGraduate.domain.matching.repository;

import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class WaitUser {
    private User user;
    private int priority;
    private LocalDateTime timeStamp;

    WaitUser(User user, int priority, LocalDateTime timeStamp){
        this.user = user;
        this.priority = priority;
        this.timeStamp = timeStamp;
    }
}
