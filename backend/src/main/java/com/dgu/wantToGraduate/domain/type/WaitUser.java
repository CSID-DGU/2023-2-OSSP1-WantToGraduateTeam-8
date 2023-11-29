package com.dgu.wantToGraduate.domain.type;


import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
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
    private List<MatchingDto.RequestDto.SelectDto> preferBrand;
    private float priority = 0;

    public void setSameNum(int sameNum) {
        this.sameNum = sameNum;
    }

    public void setPriority(int priority) { this.priority = priority; }

    public int findPriorityByBrandName(String brandName) {
        for(MatchingDto.RequestDto.SelectDto selectDto : preferBrand) {
            if(selectDto.getBrandName().equals(brandName))
                return selectDto.getPriority();
        }
        return 0;
    }
    public WaitUser(WaitUser original) {
        this.user = original.user;
        this.sameNum = original.sameNum;
        this.grade = original.grade;
        this.preferBrand = new ArrayList<>(original.preferBrand);
    }
}

