package com.dgu.wantToGraduate.domain.matching.service;

import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {

    public void matching(List<MatchingDto.RequestDto> selectInfo){
        // 1. selectInfo를 받아서
        // 2. selectInfo를 가지고 matching을 진행한다.
        // 3. matching 결과를 반환한다.
        // 채팅방 매칭은 여기서 반환된 정보들로 따로 controller를 작동시켜도 좋고, service 코드에서 해당 메서드 호출해서 사용해도 좋겠다.

    }
}
