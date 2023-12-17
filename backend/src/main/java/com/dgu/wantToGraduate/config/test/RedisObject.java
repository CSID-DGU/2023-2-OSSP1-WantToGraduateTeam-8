package com.dgu.wantToGraduate.config.test;

import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Setter
@Getter
@RedisHash("redisObject")
public class RedisObject {
    private User user;
    private int sameNum=0;
    private float grade;
    private List<MatchingDto.RequestDto.SelectDto> preferBrand;
    private float priority = 0;


    @Builder
    public RedisObject(User user, int sameNum, float grade,
                       List<MatchingDto.RequestDto.SelectDto> preferBrand, float priority) {
        this.user = user;
        this.sameNum = sameNum;
        this.grade = grade;
        this.preferBrand = preferBrand;
        this.priority = priority;
    }
}
