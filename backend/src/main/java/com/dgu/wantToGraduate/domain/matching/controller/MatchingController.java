package com.dgu.wantToGraduate.domain.matching.controller;

import com.dgu.wantToGraduate.domain.matching.dto.MatchDto;
import com.dgu.wantToGraduate.domain.matching.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchService matchService;

    @PostMapping("/test/matching")
    public ResponseEntity<?> matching(@RequestBody MatchDto.RequestDto requestDto) {
        matchService.createWaitRoom(requestDto);
        return ResponseEntity.ok().build();
    }
}
