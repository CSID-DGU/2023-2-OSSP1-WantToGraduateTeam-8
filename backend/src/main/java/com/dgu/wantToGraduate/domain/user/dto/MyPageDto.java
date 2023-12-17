package com.dgu.wantToGraduate.domain.user.dto;

import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MyPageDto {

    @Getter
    public static class MyPageResponseDto{
        //        public static MyPageResponseDto of(User user){
//            return new MyPageResponseDto(
//                    user.getNickname(),
//                    user.getId(),
//                    user.getGrade());
//        }
        private String nickname;
        private Long userid;
        private float grade;
        private String account_number;
        private String password;
        private List<String> commentList;


        @Builder
        public MyPageResponseDto(User user, List<String>commentList) {
            this.nickname = user.getNickname();
            this.userid = user.getId();
            this.grade = user.getGrade();
            this.account_number = user.getAccount_number();
            this.password = user.getPassword();
            this.commentList = commentList;
        }
    }
}
