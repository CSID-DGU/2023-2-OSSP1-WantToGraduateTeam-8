package com.dgu.wantToGraduate.domain.brand.service;

import com.dgu.wantToGraduate.domain.brand.dto.BrandDto;
import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import com.dgu.wantToGraduate.domain.brand.repository.BrandRepository;
import com.dgu.wantToGraduate.domain.category.BrandCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandDto.BrandListResponse searchAll(String category){

        List<Brand> brandList = brandRepository.findByBrandCategory(BrandCategory.fromValue(category));
        return BrandDto.BrandListResponse.of(brandList);
    }

    public BrandDto.BrandListResponse search(String brandName){

        if(brandName == null || brandName.equals("")){
            throw new IllegalArgumentException("브랜드 이름을 입력해주세요.");
        }

        List<Brand> result = brandRepository.searchBrandByName(brandName).
                orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return BrandDto.BrandListResponse.of(result);
    }
}
