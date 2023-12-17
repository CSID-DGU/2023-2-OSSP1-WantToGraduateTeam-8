package com.dgu.wantToGraduate.domain.chat.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class ChatMessage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "chatMessage_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="ChatRoom_id")
    private ChatRoom chatRoom;

    @Column
    private String msg;

    @Column
    private String receiver;

    @Column
    private String sender;
}
