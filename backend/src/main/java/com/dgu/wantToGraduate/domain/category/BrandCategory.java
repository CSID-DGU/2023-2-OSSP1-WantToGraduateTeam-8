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

    //치킨
    CHICKEN("치킨"),
    // 피자/양식
    PIZZA_AND_WESTERN( "피자/양식"),
    // 중국집
    CHINESE( "중국집"),
    // 한식
    KOREAN( "한식"),
    // 일식/돈까스
    JAPANESE_AND_PORK_CUTLET( "일식/돈까스"),
    // 족발/보쌈
    PIG_FEET_AND_BOSSAM( "족발/보쌈"),
    // 야식
    NIGHT_MEAL( "야식"),
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
