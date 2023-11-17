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
        User user = userRepository.findById(userId).get();
        if (user != null) {
            return MyPageDto.MyPageResponseDto.builder()
                    .nickname(user.getNickname())
                    .userid(user.getId())
                    .grade(user.getGrade())
                    .build();
        } else {
            // 유저를 찾지 못한 경우 처리. 예를 들어 예외를 던지거나 기본 DTO를 반환하는 등의 방식으로 처리 가능
            return null; // 또는 throw new UserNotFoundException("ID에 해당하는 사용자를 찾을 수 없습니다: " + userId);
        }
    }
}
