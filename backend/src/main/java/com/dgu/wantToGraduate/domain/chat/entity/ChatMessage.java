package com.dgu.wantToGraduate.domain.chat.entity;

import javax.persistence.*;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "chatMessage_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ChatRoom_id")
    private ChatRoom chatRoom;

    @Column
    private String msg;

    @Column
    private String receiver;

    @Column
    private String sender;
}
