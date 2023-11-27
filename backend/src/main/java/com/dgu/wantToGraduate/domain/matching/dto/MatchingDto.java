package com.dgu.wantToGraduate.domain.matching.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MatchingDto {
    @Getter
    public static class RequestDto{
        private Long userId;
        private List<String> preferBrandList;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class ResponseDto{
        private List<String> matchList;
    }
}
