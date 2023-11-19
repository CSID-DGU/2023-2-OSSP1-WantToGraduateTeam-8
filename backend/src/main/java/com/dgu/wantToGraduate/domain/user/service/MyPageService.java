package com.dgu.wantToGraduate.domain.user.service;


import com.dgu.wantToGraduate.domain.user.dto.MyPageDto;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    public MyPageDto.MyPageResponseDto getMyPage(Long userId) {
        return userRepository.findById(userId)
                .map(user -> MyPageDto.MyPageResponseDto.builder()
                        .nickname(user.getNickname())
                        .userid(user.getId())
                        .grade(user.getGrade())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 정보가 없습니다: " + userId));
    }
}
