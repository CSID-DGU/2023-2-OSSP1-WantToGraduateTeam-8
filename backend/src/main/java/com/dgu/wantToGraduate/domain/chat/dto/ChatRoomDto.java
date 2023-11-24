package com.dgu.wantToGraduate.domain.chat.dto;

import com.dgu.wantToGraduate.domain.chat.entity.ChatMessage;
import com.dgu.wantToGraduate.domain.chat.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ChatRoomDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ChatRoomRequestDto {
        private List<Long> matchedUser;
        private Long brandId;
    }
}
