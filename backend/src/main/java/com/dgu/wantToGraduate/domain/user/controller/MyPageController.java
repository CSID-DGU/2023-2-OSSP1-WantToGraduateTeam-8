package com.dgu.wantToGraduate.domain.user.controller;


import com.dgu.wantToGraduate.domain.user.dto.MyPageDto;
import com.dgu.wantToGraduate.domain.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/user/user-info/{userId}")
    public ResponseEntity<MyPageDto.MyPageResponseDto> getMyPage(@PathVariable Long userId){
        MyPageDto.MyPageResponseDto myPageResponseDto = myPageService.getMyPage(userId);
        return ResponseEntity.ok(myPageResponseDto);
    }
}
