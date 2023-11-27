package com.dgu.wantToGraduate.domain.matching.dto;

import lombok.Getter;

import java.util.List;

public class MatchDto {

    @Getter
    public static class RequestDto{
        private Long userId;
        private List<Prefer> preferBrandList;

        @Getter
        public static class Prefer{
            private int priority;
            private String brandName;
        }
    }
}
