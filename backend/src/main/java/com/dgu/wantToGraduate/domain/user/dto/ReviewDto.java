package com.dgu.wantToGraduate.domain.user.dto;

import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.entity.UserReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewRequestDto {
        private Long userId_1;
        private float grade_1;
        private String comment_1;
        private Long userId_2;
        private float grade_2;
        private String comment_2;
    }
}
