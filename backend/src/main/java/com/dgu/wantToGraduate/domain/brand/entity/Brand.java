package com.dgu.wantToGraduate.domain.brand.entity;

import com.dgu.wantToGraduate.domain.category.BrandCategory;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "brand_id")
    private Long id;

//    @OneToMany(mappedBy = "brand")
//    @Column(nullable = true)
//    private List<Menu> menu=new ArrayList<>();

    @Column(nullable = false)
    private String brandName;

    @Enumerated(value=EnumType.STRING)
    @Column(nullable = true)
    private BrandCategory brandCategory;

    @OneToMany(mappedBy = "brand")
    private List<PreferBrand> preferBrandList=new ArrayList<>();

    @Builder
    public Brand(String brandName, BrandCategory brandCategory){
        this.brandName=brandName;
        this.brandCategory=brandCategory;
    }
}
