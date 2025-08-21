package com.semenov.cash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semenov.cash.dto.CashDto;
import com.semenov.cash.service.CashService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CashControllerTest {

    private MockMvc mockMvc;
    private CashService cashService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        cashService = mock(CashService.class);
        CashController cashController = new CashController(cashService);
        mockMvc = MockMvcBuilders.standaloneSetup(cashController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testMoveCash() throws Exception {
        CashDto dto = new CashDto();
        // Заполни dto нужными тестовыми данными, если есть поля

        mockMvc.perform(post("/cash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(cashService, times(1)).moveCash(dto);
    }
}
