package com.dgu.wantToGraduate.domain.user.service;


import com.dgu.wantToGraduate.domain.user.dto.ReviewDto;
import com.dgu.wantToGraduate.domain.user.entity.UserReview;
import com.dgu.wantToGraduate.domain.user.repository.ReviewRepository;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    public void writeReview(Long writeUserId, ReviewDto.ReviewRequestDto reviewRequestDto){
        UserReview userReview_1 = UserReview.builder()
                .id(reviewRequestDto.getUserId_1())
                .user(userRepository.findById(writeUserId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.")))
                .grade(reviewRequestDto.getGrade_1())
                .comment(reviewRequestDto.getComment_1())
                .build();

        UserReview userReview_2 = UserReview.builder()
                .id(reviewRequestDto.getUserId_2())
                .user(userRepository.findById(writeUserId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.")))
                .grade(reviewRequestDto.getGrade_2())
                .comment(reviewRequestDto.getComment_2())
                .build();

        reviewRepository.save(userReview_1);
        reviewRepository.save(userReview_2);

    }
}
