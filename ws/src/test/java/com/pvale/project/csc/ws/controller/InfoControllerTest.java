package com.pvale.project.csc.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.response.InfoResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import com.pvale.project.csc.bsl.util.CscApiSampleResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class InfoControllerTest {

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public CscApiService cscApiService;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void whenCallDefault_thenReturnCscApiInfo() throws Exception {
        this.mockMvc.perform(get(InfoController.ROOT_CONTEXT_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void whenCallInfo_thenReturnCscApiInfo() throws Exception {

        InfoResponse mockedResponse = CscApiSampleResponses.info();

        Mockito.when(this.cscApiService.info(anyObject())).thenReturn(mockedResponse);

        this.mockMvc.perform(post(InfoController.INFO_CONTEXT_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(this.objectMapper.writeValueAsString(mockedResponse))));
    }
}