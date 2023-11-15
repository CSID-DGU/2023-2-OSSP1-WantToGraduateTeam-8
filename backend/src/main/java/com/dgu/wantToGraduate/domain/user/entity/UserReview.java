package com.dgu.wantToGraduate.domain.user.entity;

import javax.persistence.*;

@Entity
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "userReview_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_Id")
    private User user;
}
