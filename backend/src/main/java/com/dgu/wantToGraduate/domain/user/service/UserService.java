package com.dgu.wantToGraduate.domain.user.service;

import com.dgu.wantToGraduate.domain.user.dto.UserDto;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import com.dgu.wantToGraduate.global.exception.CustomException;
import com.dgu.wantToGraduate.global.exception.ErrorCode;
import com.dgu.wantToGraduate.global.util.JwtTokenProvider;
import com.dgu.wantToGraduate.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> signUp(UserDto.ReqSignUpDto reqSignUpDto){
        String encPassword = passwordEncoder.encode(reqSignUpDto.getPassword());
        User user = User.builder()
                .nickname(reqSignUpDto.getNickname())
                .account_number(reqSignUpDto.getAccount_number())
                .email(reqSignUpDto.getEmail())
                .password(encPassword) //TODO✅: passwordEncoder로 암호화
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

    public boolean toggleUserMatchFlag(Long userId){
        Optional<User> userEntity = userRepository.findById(userId);
        if(!userEntity.isPresent()){
            throw new IllegalArgumentException("[예외발생] 존재하지 않는 유저입니다.");
        }
        User user = userEntity.get();
        userRepository.updateUserFlag(user.getId(), !user.isMatched());


        return user.isMatched();
    }

    //init to false
    public boolean toggleInit(Long userId) {
        Optional<User> userEntity = userRepository.findById(userId);
        if (!userEntity.isPresent()) {
            throw new IllegalArgumentException("[예외발생] 존재하지 않는 유저입니다.");
        }
        User user = userEntity.get();
        userRepository.updateUserFlag(user.getId(), false);

        return user.isMatched();
    }
}
