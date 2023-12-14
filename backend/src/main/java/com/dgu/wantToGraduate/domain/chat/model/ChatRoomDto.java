package com.dgu.wantToGraduate.domain.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private String roomId;

    public static ChatRoomDto create(String roomId) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.roomId = roomId;
        return chatRoomDto;
    }
}
