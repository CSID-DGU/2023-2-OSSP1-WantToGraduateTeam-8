package com.dgu.wantToGraduate.domain.matching.entity;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;

import javax.persistence.*;

@Entity
public class WaitingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "waitingRoom_id")
    private Long id;

    @OneToOne(mappedBy = "waitingRoom") // WaitingRoom이 연관관계의 주인이 아님을 명시
    private Brand brand; //연관관계 주인이 아니므로, read only입니다.
}
