package com.dgu.wantToGraduate.domain.type;


import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@Builder
public class WaitUser {
    private User user;
    private int sameNum=0;
    private float grade;
    private List<Long> brandList;

    public void setSameNum(int sameNum) {
        this.sameNum = sameNum;
    }

    public WaitUser(WaitUser original) {
        this.user = original.user;
        this.sameNum = original.sameNum;
        this.grade = original.grade;
        this.brandList = new ArrayList<>(original.brandList);
    }
}

