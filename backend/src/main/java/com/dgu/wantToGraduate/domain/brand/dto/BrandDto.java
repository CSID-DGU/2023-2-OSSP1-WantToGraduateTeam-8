package com.dgu.wantToGraduate.domain.brand.dto;

import com.dgu.wantToGraduate.domain.brand.entity.Brand;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private String brandName;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestBrandList{
        private Integer category;
    }


    public static BrandDto from(Brand brand){
        return new BrandDto(
                brand.getBrandName()
        );
    }

    @Getter
    public static class BrandListResponse {
        private List<BrandDto> brandNameList;

        public BrandListResponse(List<BrandDto> list){
            this.brandNameList = list;
        }

        public static BrandListResponse of(List<Brand> list){
            List<BrandDto> brandDtoList = list
                    .stream()
                    .map(BrandDto::from)
                    .collect(java.util.stream.Collectors.toList());

            return new BrandListResponse(brandDtoList);
        }
    }

    @Getter
    public static class BrandCategoryRequest{
        private String category;
    }
}
