package com.dgu.wantToGraduate.domain.user.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class UserReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "userReview_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_Id")
    private User user;
}
