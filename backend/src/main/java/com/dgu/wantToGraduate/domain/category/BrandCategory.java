package com.dgu.wantToGraduate.domain.category;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BrandCategory {
    //치킨
    CHICKEN(0, "치킨"),
    // 피자/양식
    PIZZA_AND_WESTERN(1, "피자/양식"),
    // 중국집
    CHINESE(2, "중국집"),
    // 한식
    KOREAN(3, "한식"),
    // 일식/돈까스
    JAPANESE_AND_PORK_CUTLET(4, "일식/돈까스"),
    // 족발/보쌈
    PIG_FEET_AND_BOSSAM(5, "족발/보쌈"),
    // 야식
    NIGHT_MEAL(6, "야식"),
    // 분식
    SNACK(7, "분식"),
    // 카페/디저트
    CAFE_AND_DESSERT(8, "카페/디저트");

    private final Integer value;
    private final String foodType;

    @JsonValue
    public String getFootType() {
        return this.foodType;
    }
}
