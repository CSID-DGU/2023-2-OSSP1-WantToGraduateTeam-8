package com.dgu.wantToGraduate.domain.matching.repository;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import com.dgu.wantToGraduate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreferBrandRepository extends JpaRepository<PreferBrand, Long>{
    public PreferBrand findByUser(User user);

    public PreferBrand findByUserAndBrandId(User user, Long brandId);
}
