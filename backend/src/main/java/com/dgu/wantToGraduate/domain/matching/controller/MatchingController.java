package com.dgu.wantToGraduate.domain.matching.controller;

import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.service.MatchingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingService matchingService;
    @PostMapping("/run")
    public ResponseEntity<?> match(@RequestBody MatchingDto.RequestDto requestDto){
        log.info("[MatchingController] 진입");
        MatchingDto.ResponseDto matching = matchingService.matching(requestDto);
        log.info("[!!!!MatchingController] matching: {}", matching);
        log.info("[MatchingController] 종료");
        return ResponseEntity.ok(matching);
    }
}
