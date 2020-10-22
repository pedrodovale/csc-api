package com.pvale.project.csc.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.response.SignaturesSignHashResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import com.pvale.project.csc.bsl.util.CscApiSampleResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SignaturesControllerTest {

    public static final String BASE_URL = "/signatures";

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CscApiService cscApiService;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void whenCallSignaturesSignHash_returnSignaturesSignHashResponse() throws Exception {

        SignaturesSignHashResponse mockedSignaturesSignHashResponse = CscApiSampleResponses.signaturesSignHash();

        Mockito.when(this.cscApiService.signaturesSignHash(anyObject())).thenReturn(mockedSignaturesSignHashResponse);

        this.mockMvc.perform(
                post(BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedSignaturesSignHashResponse)));

    }
}