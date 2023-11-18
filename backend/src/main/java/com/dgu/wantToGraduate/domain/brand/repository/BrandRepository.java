package com.dgu.wantToGraduate.domain.brand.repository;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.category.BrandCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long>{

    // 카테고리로 브랜드 검색

    public List<Brand> findByBrandCategory(BrandCategory category);
}
