package com.dgu.wantToGraduate.domain.chat.entity;

import javax.persistence.*;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(nullable = false)
    private int chatNum;
}
