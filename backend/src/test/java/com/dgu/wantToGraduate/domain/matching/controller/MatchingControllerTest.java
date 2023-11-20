package com.dgu.wantToGraduate.domain.matching.controller;

import com.dgu.wantToGraduate.domain.matching.dto.MatchingDto;
import com.dgu.wantToGraduate.domain.matching.service.MatchingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


class MatchingControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private MatchingService matchingService;

    @InjectMocks
    private MatchingController matchingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(matchingController).build();
    }

    @Test
    public void matchTest() throws Exception {
        MatchingDto.RequestDto requestDto = new MatchingDto.RequestDto();
        // 데이터 준비...

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/matching/run")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk());

        verify(matchingService).matching(any(MatchingDto.RequestDto.class));
    }
}