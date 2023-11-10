package com.dgu.wantToGraduate.domain.brand.entity;

import com.dgu.wantToGraduate.domain.category.BrandType;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String grade;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BrandType type;
}
