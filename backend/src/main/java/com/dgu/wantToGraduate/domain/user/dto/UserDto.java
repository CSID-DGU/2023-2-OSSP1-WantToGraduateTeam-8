package com.dgu.wantToGraduate.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class ReqSignUpDto{
        private String nickname;
        private String account_number;
        private String email;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class ReqLoginDto{
        private String email;
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResLoginDto{
        String accessToken;
        String refreshToken;
    }
}
