package com.pvale.project.csc.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.request.CredentialsAuthorizeRequest;
import com.pvale.project.csc.api.request.CredentialsExtendTransactionRequest;
import com.pvale.project.csc.api.request.CredentialsInfoRequest;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.request.CredentialsSendOtpRequest;
import com.pvale.project.csc.api.response.CredentialsAuthorizeResponse;
import com.pvale.project.csc.api.response.CredentialsExtendTransactionResponse;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsListResponse;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(WsTestConfig.class)
public class CredentialsControllerTest {

    public static final String BASE_URL = "/credentials";
    public static final String CREDENTIAL_ID = "credentialId";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public CscApiService cscApiService;

    @Test
    public void whenCallCredentialsList_thenReturnCredentialsIdList() throws Exception {

        CredentialsListResponse mockedCredentialsListResponse = CscApiSampleResponses.credentialsList();
        Mockito.when(this.cscApiService.credentialsList(any())).thenReturn(mockedCredentialsListResponse);

        CredentialsListRequest credentialsListRequest = this.credentialsListRequest();

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_LIST_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.objectMapper.writeValueAsString(credentialsListRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsListResponse)));

    }

    @Test
    public void whenCallCredentialsInfo_thenReturnCredentialsInfoResponse() throws Exception {

        CredentialsInfoResponse mockedCredentialsInfoResponse = CscApiSampleResponses.credentialsInfo();
        Mockito.when(this.cscApiService.credentialsInfo(any())).thenReturn(mockedCredentialsInfoResponse);

        CredentialsInfoRequest credentialsInfoRequest = this.credentialsInfoRequest();

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_INFO_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.objectMapper.writeValueAsString(credentialsInfoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsInfoResponse)));

    }

    @Test
    public void whenCallCredentialsAuthorize_thenReturnCredentialsAuthorizeResponse() throws Exception {

        CredentialsAuthorizeResponse mockedCredentialsAuthorizeResponse = CscApiSampleResponses.credentialsAuthorize();
        Mockito.when(this.cscApiService.credentialsAuthorize(any())).thenReturn(mockedCredentialsAuthorizeResponse);

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = this.credentialsAuthorizeRequest();

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsAuthorizeResponse)));
    }

    @Test
    public void whenCallCredentialsExtendTransaction_thenReturnCredentialsExtendTransactionResponse() throws Exception {

        CredentialsExtendTransactionResponse mockedCredentialsExtendTransactionResponse = CscApiSampleResponses.credentialsExtendTransaction();
        Mockito.when(this.cscApiService.credentialsExtendTransaction(any())).thenReturn(mockedCredentialsExtendTransactionResponse);

        CredentialsExtendTransactionRequest credentialsExtendTransactionRequest = this.credentialsExtendTransactionRequest();

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.objectMapper.writeValueAsString(credentialsExtendTransactionRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(mockedCredentialsExtendTransactionResponse)));
    }

    @Test
    public void whenCallCredentialsSendOtp_thenReturnCredentialsSendOtpResponse() throws Exception {

        Mockito.doNothing().when(this.cscApiService).credentialsSendOtp(any());

        CredentialsSendOtpRequest credentialsSendOtpRequest = this.credentialsSendOtpRequest();

        this.mockMvc.perform(
                post(BASE_URL + CredentialsController.CREDENTIALS_SEND_OTP_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(this.objectMapper.writeValueAsString(credentialsSendOtpRequest)))
                .andExpect(status().isOk());
    }

    private CredentialsListRequest credentialsListRequest() {
        return new CredentialsListRequest();
    }

    private CredentialsInfoRequest credentialsInfoRequest() {
        CredentialsInfoRequest credentialsInfoRequest = new CredentialsInfoRequest();
        credentialsInfoRequest.setCredentialId(CREDENTIAL_ID);
        return credentialsInfoRequest;
    }

    private CredentialsAuthorizeRequest credentialsAuthorizeRequest() {
        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId(CREDENTIAL_ID);
        credentialsAuthorizeRequest.setNumSignatures(0);
        return credentialsAuthorizeRequest;
    }

    private CredentialsExtendTransactionRequest credentialsExtendTransactionRequest() {
        CredentialsExtendTransactionRequest credentialsExtendTransactionRequest = new CredentialsExtendTransactionRequest();
        credentialsExtendTransactionRequest.setCredentialId(CREDENTIAL_ID);
        credentialsExtendTransactionRequest.setSad("sad");
        return credentialsExtendTransactionRequest;
    }

    private CredentialsSendOtpRequest credentialsSendOtpRequest() {
        CredentialsSendOtpRequest credentialsSendOtpRequest = new CredentialsSendOtpRequest();
        credentialsSendOtpRequest.setCredentialId(CREDENTIAL_ID);
        return credentialsSendOtpRequest;
    }
}