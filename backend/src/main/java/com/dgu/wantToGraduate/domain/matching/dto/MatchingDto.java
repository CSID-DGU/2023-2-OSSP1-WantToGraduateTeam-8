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
        private List<SelectDto> preferBrandList;
        @Getter
        @NoArgsConstructor
        public static class SelectDto{
            private String brandName;
            private int priority;
        }
    }

    @AllArgsConstructor
    @Builder
    public static class ResponseDto{
        private String brandName;
        private List<MatchDto> matchList;

        public static class MatchDto{
            private String nickname;
        }
    }
}
