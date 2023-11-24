package com.dgu.wantToGraduate.domain.chat.controller;


import com.dgu.wantToGraduate.domain.chat.repository.ChatRoomRepository;
import com.dgu.wantToGraduate.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;
    @PostMapping ("/room/new")
    public String makeRoom(){
        return null;
    }
}
