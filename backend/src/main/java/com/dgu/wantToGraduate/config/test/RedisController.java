package com.dgu.wantToGraduate.config.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @GetMapping("/redis/test")
    public ResponseEntity<?> test() {

        return ResponseEntity.ok(redisService.redisString());
    }

    @GetMapping("/user/test")
    public ResponseEntity<?> userTest(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(principal);

    }

    @GetMapping("/redis/obj")
    public ResponseEntity<?> testObj(){
        return ResponseEntity.ok(redisService.testSaveOBJ());
    }
}
