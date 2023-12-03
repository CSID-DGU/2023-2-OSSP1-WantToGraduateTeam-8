package com.dgu.wantToGraduate.domain.chat.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;
import com.dgu.wantToGraduate.domain.user.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "chatRoom_id")
    private Long id;

    @OneToMany(mappedBy = "chatRoom")
    List<User> users = new ArrayList<User>();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> messageList = new ArrayList<>();

    @Column(nullable = false)
    private String roomName;
}
