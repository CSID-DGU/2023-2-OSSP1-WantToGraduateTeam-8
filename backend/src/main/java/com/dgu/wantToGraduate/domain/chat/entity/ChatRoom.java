package com.dgu.wantToGraduate.domain.chat.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;
import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private String roomId;
}
