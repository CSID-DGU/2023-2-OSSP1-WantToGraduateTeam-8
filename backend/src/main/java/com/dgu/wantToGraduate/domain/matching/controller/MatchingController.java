package com.dgu.wantToGraduate.domain.matching.controller;

import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.service.MatchingService;
import com.dgu.wantToGraduate.domain.matching.service.MatchingService_Q_version;
import com.dgu.wantToGraduate.domain.user.entity.User;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import com.dgu.wantToGraduate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.dgu.wantToGraduate.domain.matching.service.MatchingService_Q_version.totalGroupCnt;
import static com.dgu.wantToGraduate.domain.matching.service.MatchingService_Q_version.totalScore;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/matching")
public class MatchingController {
    /*테스트용 코드*/
    private final UserService userservice;
    private final UserRepository userRepository;
    /*테스트용 코드*/

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
//        if(matching != null) matchingServiceQversion.testEvaluatePriority(matching);
//        log.info("[최종요소]\nToTscore: {} \n ToTcnt : {}", totalScore, totalGroupCnt);
//        if(totalGroupCnt>=3000) {
//
//            log.info("[최종] matching: {}", (double)totalScore/9000*100);
//        }
        return ResponseEntity.ok(matching);
    }

//    @GetMapping("/Ttest/{userId}") //TEST_RESULT: SUCCESS✅
//    public ResponseEntity<?> test(@PathVariable Long userId){
//
//        User user = userRepository.findById(userId).orElseThrow(()
//                -> new IllegalArgumentException("[예외발생]존재하지 않는 유저입니다."));
//        boolean b1 = userservice.toggleUserMatchFlag(userId);
//        log.info("[Ttest] b1: {}  ,  actual_user_flag : {}", b1, user.isMatched());
//
//        return ResponseEntity.ok("test");
//    }

    @GetMapping("/run/status/flag/{userId}")
    public ResponseEntity<?> checkUserFlag(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(()
                -> new IllegalArgumentException("[예외발생]존재하지 않는 유저입니다."));
        return ResponseEntity.ok(user.isMatched());
    }

    @GetMapping("/run/status/flag")
    public ResponseEntity<?> checkUserFlag(){
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("[예외발생]존재하지 않는 유저입니다."));
        return ResponseEntity.ok(user.isMatched());
    }
}
