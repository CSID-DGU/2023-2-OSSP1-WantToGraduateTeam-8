package com.dgu.wantToGraduate.domain.chat.service;

import com.dgu.wantToGraduate.domain.chat.entity.ChatMessage;
import com.dgu.wantToGraduate.domain.chat.entity.ChatRoom;
import com.dgu.wantToGraduate.domain.chat.model.ChatMessageDto;
import com.dgu.wantToGraduate.domain.chat.repository.ChatMessageRepository;
import com.dgu.wantToGraduate.domain.chat.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;


    public List<ChatMessageDto> getChatMessages(String roomId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByChatRoomOrderByCreateAtAsc(roomId);
        return convertToDtoList(chatMessages);
    }
    public List<ChatMessageDto> convertToDtoList(List<ChatMessage> chatMessages) {
        return chatMessages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 채팅방의 메시지 목록을 가져오는 메서드
    public ChatMessageDto convertToDto(ChatMessage chatMessage) {
        return ChatMessageDto.builder()
                .type(chatMessage.getType())
                .roomId(chatMessage.getChatRoom().getRoomId())
                .sender(chatMessage.getSender())
                .message(chatMessage.getMsg())
                .build();
    }

    // 다른 필요한 메서드들도 추가할 수 있습니다.
}