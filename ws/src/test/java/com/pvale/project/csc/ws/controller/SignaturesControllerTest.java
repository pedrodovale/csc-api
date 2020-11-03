package com.pvale.project.csc.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.request.SignaturesSignHashRequest;
import com.pvale.project.csc.api.response.SignaturesSignHashResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import com.pvale.project.csc.bsl.util.CscApiSampleResponses;
import com.pvale.project.csc.ws.config.WsTestConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(WsTestConfig.class)
class SignaturesControllerTest {

    public static final String BASE_URL = "/signatures";
    public static final String CREDENTIAL_ID = "credentialId";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CscApiService cscApiService;

    @Test
    void whenCallSignaturesSignHash_returnSignaturesSignHashResponse() throws Exception {

        SignaturesSignHashResponse mockedSignaturesSignHashResponse = CscApiSampleResponses.signaturesSignHash();
        Mockito.when(this.cscApiService.signaturesSignHash(any())).thenReturn(mockedSignaturesSignHashResponse);

        SignaturesSignHashRequest signaturesSignHashRequest = this.signaturesSignHashRequest();

        this.mockMvc.perform(
                post(BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedSignaturesSignHashResponse)));

    }

    private SignaturesSignHashRequest signaturesSignHashRequest() {
        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId(CREDENTIAL_ID);
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setHash(Collections.emptySet());
        signaturesSignHashRequest.setSignAlgo("signAlgo");
        return signaturesSignHashRequest;
    }
}