package com.dgu.wantToGraduate.domain.brand.entity;

import com.dgu.wantToGraduate.domain.category.BrandCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "brand_id")
    private Long id;

    @OneToMany(mappedBy = "brand")
    private List<Menu> menus=new ArrayList<>();

    @Column(nullable = false)
    private String brandName;

    @Enumerated(value=EnumType.STRING)
    @Column(nullable = false)
    private BrandCategory brandCategory;
}
