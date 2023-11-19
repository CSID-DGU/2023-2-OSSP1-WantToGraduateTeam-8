package com.dgu.wantToGraduate.domain.matching.controller;

import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/matching")
public class MatchingController {

    @PostMapping("/run")
    public ResponseEntity<?> match(@RequestBody MatchingDto.RequestDto requestDto){
        return ResponseEntity.ok().build();
    }
}
