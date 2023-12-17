package com.dgu.wantToGraduate.domain.chat.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;
import com.dgu.wantToGraduate.domain.chat.model.ChatMessageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "chatMessage_id")
    private Long id;


    @Enumerated(EnumType.STRING)
    private ChatMessageDto.MessageType type;

    @ManyToOne
    @JoinColumn(name="ChatRoom_id")
    private ChatRoom chatRoom;

    @Column
    private String sender;

    @Column
    private String msg;
}
