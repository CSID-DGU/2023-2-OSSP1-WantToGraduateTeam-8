package com.dgu.wantToGraduate.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "userReview_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_Id")
    private User user;

    @Column
    private String comment;

    @Column
    private float grade;

}