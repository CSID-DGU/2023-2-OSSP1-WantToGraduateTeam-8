package com.dgu.wantToGraduate.domain.matching.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
public class MatchingDto {


    @Getter
    public static class RequestDto{
        private List<SelectDto> preferBrandList;
        public static class SelectDto{
            private Long userId;
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
