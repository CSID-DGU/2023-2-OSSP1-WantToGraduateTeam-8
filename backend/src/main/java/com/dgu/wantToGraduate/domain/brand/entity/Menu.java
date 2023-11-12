package com.dgu.wantToGraduate.domain.brand.entity;

import javax.persistence.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "menu_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Brand_Id")
    private Brand brand;

    @Column(nullable = false)
    private String menuName;

    @Column(nullable = false)
    private int price;
}
