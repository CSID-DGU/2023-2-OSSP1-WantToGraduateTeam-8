package com.dgu.wantToGraduate.domain.user.service;


import com.dgu.wantToGraduate.domain.user.dto.MyPageDto;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.entity.UserReview;
import com.dgu.wantToGraduate.domain.user.repository.ReviewRepository;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MyPageDto.MyPageResponseDto getMyPage(Long userId) {
//        return userRepository.findById(userId)
//                .map(user -> MyPageDto.MyPageResponseDto.builder()
//                        .nickname(user.getNickname())
//                        .userid(user.getId())
//                        .grade(user.getGrade())
//                        .build())
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 정보가 없습니다: " + userId));
//    }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자 정보가 없습니다: " + userId));
        List<UserReview> userReview = reviewRepository.findAllByUserId(userId);

        List<String> commentList = userReview.stream()
                .map(UserReview::getComment)
                .collect(Collectors.toList());

        return MyPageDto.MyPageResponseDto.builder()
                .user(user)
                .commentList(commentList)
                .build();
    }
}
