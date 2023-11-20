package com.dgu.wantToGraduate.domain.matching.entity;

import com.dgu.wantToGraduate.domain.BaseTimeEntity;
import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PreferBrand extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "preferBrand_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private int priority;

    //TODO: 브랜드가 Many가 아니네... 선호테이블이 Many네 이거 바꿔야해(주현이하고 공유)
    // 왜? 선호테이블은 유저가 여러개의 브랜드를 선호할 수 있으니까
    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

//    //1L [{1순위,BBQ}, mdasf,sdfisdg]
//    // key : value = 순위 : 브랜드ID
//    @OneToMany(mappedBy = "preferBrand")
//    private List<Brand> brandList = new HashMap<>();
}
/*
1. 사용자가 원하는 매장 1,2,3 선택
2. 매장 1,2,3에 존재하는 SortedList에 사용자를 넣는다.(2nd:현재 시각  1st:선호도 가중치)
3. 만약 특정 매장의 sortedList에 3명이 차면 그 매장의 채팅방을 만든다.
 */




