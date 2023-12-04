package com.dgu.wantToGraduate.domain.matching.controller;

import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.service.MatchingService;
import com.dgu.wantToGraduate.domain.matching.service.MatchingService_Q_version;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dgu.wantToGraduate.domain.matching.service.MatchingService_Q_version.totalGroupCnt;
import static com.dgu.wantToGraduate.domain.matching.service.MatchingService_Q_version.totalScore;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingService matchingService;
    private final MatchingService_Q_version matchingServiceQversion;

    @PostMapping("/run/v2")
    public ResponseEntity<?> match(@RequestBody MatchingDto.RequestDto requestDto) {
        log.info("[MatchingController] 진입");
        MatchingDto.ResponseDto matchingResult = matchingService.matching(requestDto);
        log.info("[MatchingController] 종료");
        return ResponseEntity.ok(matchingResult);
    }

    @PostMapping("/run/v1")
    public ResponseEntity<?> q_match(@RequestBody MatchingDto.RequestDto requestDto){
        MatchingDto.ResponseDto matching = matchingServiceQversion.matching(requestDto);
        if(matching != null) matchingServiceQversion.testEvaluatePriority(matching);
        log.info("[최종요소]\nToTscore: {} \n ToTcnt : {}", totalScore, totalGroupCnt);
        if(totalGroupCnt>=3000) {
            log.info("[최종] matching: {}", (double)totalScore/9000*100);
        }
        return ResponseEntity.ok(matching);
    }
}
