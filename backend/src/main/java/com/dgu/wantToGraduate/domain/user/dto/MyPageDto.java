package com.dgu.wantToGraduate.domain.user.dto;

import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MyPageDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MyPageResponseDto{
        private String nickname;
        private Long userid;
        private float grade;

        public static MyPageResponseDto of(User user){
            return new MyPageResponseDto(
                    user.getNickname(),
                    user.getId(),
                    user.getGrade());
        }
    }
}
