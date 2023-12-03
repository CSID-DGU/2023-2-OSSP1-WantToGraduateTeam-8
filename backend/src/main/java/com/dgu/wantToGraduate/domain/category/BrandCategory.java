package com.dgu.wantToGraduate.domain.category;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum BrandCategory {

    // 커피점/카페
    CAFE("카페"),
    // 양식
    WESTERN("양식"),
    // 중식
    CHINESE("중식"),
    // 제과제빵떡케익
    BAKERY("베이커리"),
    // 닭/오리요리
    CHICKEN("닭/오리요리"),
    // 일식/수산물
    JAPANESE("일식/수산물"),
    // 한식
    KOREAN("한식"),
    // 별식/퓨전요리
    FUSION("퓨전요리"),
    // 패스트푸드
    FASTFOOD("패스트푸드"),
    // 분식
    SNACK("분식"),
    // 유흥주점
    PUB("술안주");


    private final String foodType;

    public static BrandCategory fromValue(String value) {
        for (BrandCategory category : BrandCategory.values()) {
            if (category.getFootType().equals(value)) {
                return category;
            }
        }
        return null;
    }

    @JsonValue
    public String getFootType() {
        return this.foodType;
    }
}
