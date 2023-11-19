package com.dgu.wantToGraduate.domain.user.controller;


import com.dgu.wantToGraduate.domain.user.dto.ReviewDto;
import com.dgu.wantToGraduate.domain.user.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/matching/review/{userid}")
    public String writeReview(@PathVariable Long userid, @RequestBody ReviewDto.ReviewRequestDto reviewRequestDto){
        reviewService.writeReview(userid, reviewRequestDto);
        return "리뷰 등록 완료";
    }

}
