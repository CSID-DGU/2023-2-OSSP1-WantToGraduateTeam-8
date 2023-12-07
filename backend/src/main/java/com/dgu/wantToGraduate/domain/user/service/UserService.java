package com.dgu.wantToGraduate.domain.user.service;

import com.dgu.wantToGraduate.domain.user.dto.UserDto;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import com.dgu.wantToGraduate.global.exception.CustomException;
import com.dgu.wantToGraduate.global.exception.ErrorCode;
import com.dgu.wantToGraduate.global.util.JwtTokenProvider;
import com.dgu.wantToGraduate.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> signUp(UserDto.ReqSignUpDto reqSignUpDto){
        User user = User.builder()
                .nickname(reqSignUpDto.getNickname())
                .account_number(reqSignUpDto.getAccount_number())
                .email(reqSignUpDto.getEmail())
                .password(reqSignUpDto.getPassword())
                .build();

        userRepository.findByEmail(reqSignUpDto.getEmail())
                .ifPresent(m -> {
                    throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
                });


        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    public UserDto.ResLoginDto userValidation(UserDto.ReqLoginDto reqLoginDto) {
        User user = userRepository.findByEmail(reqLoginDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_REGISTERED));

        return UserDto.ResLoginDto.builder()
                    .accessToken(jwtTokenProvider.createAccessToken(user.getId()))
//                    .refreshToken("null")
                    .build();

    }
}
