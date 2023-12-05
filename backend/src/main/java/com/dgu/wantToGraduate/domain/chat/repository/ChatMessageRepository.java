package com.dgu.wantToGraduate.domain.chat.repository;

import com.dgu.wantToGraduate.domain.chat.entity.ChatMessage;
import com.dgu.wantToGraduate.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query("select c from ChatMessage c where c.chatRoom.roomId = ?1 order by c.createAt asc")
    public List<ChatMessage>findAllByChatRoomOrderByCreateAtAsc(String chatRoomId);
}
