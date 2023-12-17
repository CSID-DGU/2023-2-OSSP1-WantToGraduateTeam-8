package com.dgu.wantToGraduate.domain.user.controller;

import com.dgu.wantToGraduate.domain.user.dto.UserDto;
import com.dgu.wantToGraduate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user/join")
    public ResponseEntity<?> signUp(@RequestBody UserDto.ReqSignUpDto reqSignUpDto){
        return ResponseEntity.ok(userService.signUp(reqSignUpDto));
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> signIn(@RequestBody UserDto.ReqLoginDto reqLoginDto){

        return ResponseEntity.ok(userService.userValidation(reqLoginDto));
    }

    @GetMapping("/")
    public String home() {
        return "hihihihihi";
    }

//    @GetMapping("/toggle/test/{userId}")
//    public ResponseEntity<?> toggleTest(@PathVariable Long userId){
//        boolean b = userService.toggleTest(userId);
//
//        return ResponseEntity.ok(b);
//    }
}
