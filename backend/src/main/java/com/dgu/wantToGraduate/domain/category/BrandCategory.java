package com.dgu.wantToGraduate.domain.category;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum BrandCategory {

    /*테스트 데이터셋 입니다.*/
    //🧑‍💻TODO: 실제 브랜드 데이터 포멧으로 변경
    // 찜/탕/찌개
    STEW_SOUP_STEAMED("찜/탕/찌개"),
    // 백반/죽/국수
    RICE_SOUP_NOODLE("백반/죽/국수"),
    //치킨
    CHICKEN("치킨"),
    // 피자
    PIZZA( "피자"),
    // 고기/구이
    MEAT_AND_ROAST( "고기/구이"),
    //양식
    WESTERN( "양식"),
    // 중국집
    CHINESE( "중식"),
    // 아시안
    ASIAN( "아시안"),
    // 일식/돈까스
    JAPANESE_AND_PORK_CUTLET( "돈까스/회/일식"),
    // 족발/보쌈
    PIG_FEET( "족발"),
    // 분식
    SNACK( "분식"),
    // 카페/디저트
    CAFE_AND_DESSERT( "카페/디저트");



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

//    public String getValue() {
//        return value;
//    }
}
