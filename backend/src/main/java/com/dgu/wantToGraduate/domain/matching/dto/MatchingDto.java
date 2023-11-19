package com.dgu.wantToGraduate.domain.matching.dto;

import lombok.Getter;

import java.util.List;

public class PreferBrandDto {

    @Getter
    public static class RequestDto{
        private List<SelectDto> preferBrandList;
        public static class SelectDto{
            private Long userId;
            private String brandName;
            private int priority;
        }
    }
}
