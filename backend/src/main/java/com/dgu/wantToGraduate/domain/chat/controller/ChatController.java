package com.dgu.wantToGraduate.domain.chat.controller;

import com.dgu.wantToGraduate.domain.chat.entity.ChatMessage;
import com.dgu.wantToGraduate.domain.chat.model.ChatMessageDto;
import com.dgu.wantToGraduate.domain.chat.repository.ChatMessageRepository;
import com.dgu.wantToGraduate.domain.chat.repository.ChatRoomRepository;
import com.dgu.wantToGraduate.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    private final SimpMessageSendingOperations messagingTemplate;

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        if (ChatMessageDto.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        ChatMessage chatMessage = ChatMessage.builder()
                .type(message.getType())
                .chatRoom(chatRoomRepository.findByRoomId(message.getRoomId()))
                .sender(message.getSender())
                .msg(message.getMessage())
                .build();
        chatMessageRepository.save(chatMessage);
    }

    @GetMapping("/chat/messages/{roomId}")
    public ResponseEntity<List<ChatMessageDto>> getChatMessages(@PathVariable String roomId) {
        List<ChatMessageDto> chatMessageDtos = chatMessageService.getChatMessages(roomId);
        log.info(chatMessageDtos.get(0).getMessage());
        return new ResponseEntity<>(chatMessageDtos, HttpStatus.OK);
    }
}
