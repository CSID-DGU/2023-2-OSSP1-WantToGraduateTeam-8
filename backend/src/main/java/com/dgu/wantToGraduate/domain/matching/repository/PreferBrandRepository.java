package com.dgu.wantToGraduate.domain.matching.repository;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.matching.entity.PreferBrand;
import com.dgu.wantToGraduate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreferBrandRepository extends JpaRepository<PreferBrand, Long> {

    //설명: user가 선호하는 브랜드 리스트를 가져온다.
    @Query("select pb.brand from PreferBrand pb where pb.user = :user")
    List<Brand> searchBrandListByUser(/*@Param("user")*/User user);
}
