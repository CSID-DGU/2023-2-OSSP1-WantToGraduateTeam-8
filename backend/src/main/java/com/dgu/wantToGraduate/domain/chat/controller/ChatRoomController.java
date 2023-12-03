package com.dgu.wantToGraduate.domain.chat.controller;

import com.dgu.wantToGraduate.domain.chat.model.ChatRoom;
import com.dgu.wantToGraduate.domain.chat.repository.ChatRoomRepository;
import com.dgu.wantToGraduate.domain.chat.service.ChatService;
import com.dgu.wantToGraduate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//todo: mapping 안된다!!!!!!!!!!!!!!!!!!!
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatService chatService;

    @GetMapping("/room/test/{userId}")
    public ResponseEntity<Map<String, String>> enterChatRoom(@PathVariable String userId) {
        String roomId = chatService.findRoomIdByUserId(Long.parseLong(userId));
        String nickName = userRepository.findById(Long.parseLong(userId)).get().getNickname();
        Map<String, String> result = new HashMap<>();
        result.put("roomId", roomId);
        result.put("nickName", nickName);
        return ResponseEntity.ok(result);
    }
//유저 아이디 넘겨주기 + 채팅방 생성
    @GetMapping("/room/test")
    public String teest() {
        chatService.setTestUser();
        chatService.createTestChatRoom();
        return "chat/chatstart";
    }

    @GetMapping("/room")
    public String rooms() {
        return "chat/room";
    }

    @GetMapping("/room/test/a")
    public void findtest(@RequestParam("userId") Long userId) {
        System.out.println(userId);
        System.out.println(chatService.findRoomIdByUserId(userId));
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(@PathVariable String roomId) {
        return "chat/roomdetail";
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
