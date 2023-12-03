package com.dgu.wantToGraduate.domain.Q_matching.controller;

import com.dgu.wantToGraduate.domain.Q_matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.Q_matching.service.MatchingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dgu.wantToGraduate.domain.Q_matching.service.MatchingService.totalGroupCnt;
import static com.dgu.wantToGraduate.domain.Q_matching.service.MatchingService.totalScore;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingService matchingService;
    @PostMapping("/run")
    public ResponseEntity<?> match(@RequestBody MatchingDto.RequestDto requestDto){
        MatchingDto.ResponseDto matching = matchingService.matching(requestDto);
        if(matching != null) matchingService.testEvaluatePriority(matching);
        log.info("[최종요소]\nToTscore: {} \n ToTcnt : {}", totalScore, totalGroupCnt);
        if(totalGroupCnt>=3000) {
            log.info("[최종] matching: {}", (double)totalScore/9000*100);
        }


        return ResponseEntity.ok(matching);
    }
}
