package com.pvale.project.csc.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.response.CredentialsAuthorizeResponse;
import com.pvale.project.csc.api.response.CredentialsExtendTransactionResponse;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsListResponse;
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
public class CredentialsControllerTest {

    public static final String BASE_URL = "/credentials";

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public CscApiService cscApiService;

    @BeforeEach
    public void setUp() throws Exception {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void whenCallCredentialsList_thenReturnCredentialsIdList() throws Exception {

        CredentialsListResponse mockedCredentialsListResponse = CscApiSampleResponses.credentialsList();

        Mockito.when(this.cscApiService.credentialsList(anyObject())).thenReturn(mockedCredentialsListResponse);

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_LIST_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsListResponse)));

    }

    @Test
    public void whenCallCredentialsInfo_thenReturnCredentialsInfoResponse() throws Exception {

        CredentialsInfoResponse mockedCredentialsInfoResponse = CscApiSampleResponses.credentialsInfo();

        Mockito.when(this.cscApiService.credentialsInfo(anyObject())).thenReturn(mockedCredentialsInfoResponse);

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_INFO_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsInfoResponse)));

    }

    @Test
    public void whenCallCredentialsAuthorize_thenReturnCredentialsAuthorizeResponse() throws Exception {

        CredentialsAuthorizeResponse mockedCredentialsAuthorizeResponse = CscApiSampleResponses.credentialsAuthorize();

        Mockito.when(this.cscApiService.credentialsAuthorize(anyObject())).thenReturn(mockedCredentialsAuthorizeResponse);

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsAuthorizeResponse)));
    }

    @Test
    public void whenCallCredentialsExtendTransaction_thenReturnCredentialsExtendTransactionResponse() throws Exception {

        CredentialsExtendTransactionResponse mockedCredentialsExtendTransactionResponse = CscApiSampleResponses.credentialsExtendTransaction();

        Mockito.when(this.cscApiService.credentialsExtendTransaction(anyObject())).thenReturn(mockedCredentialsExtendTransactionResponse);

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsExtendTransactionResponse)));
    }

    @Test
    public void whenCallCredentialsSendOtp_thenReturnCredentialsSendOtpResponse() throws Exception {

        Mockito.doNothing().when(this.cscApiService).credentialsSendOtp(anyObject());

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_SEND_OTP_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}