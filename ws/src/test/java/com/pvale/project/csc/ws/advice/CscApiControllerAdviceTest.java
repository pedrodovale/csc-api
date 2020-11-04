package com.pvale.project.csc.ws.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.enumerator.CscApiErrorType;
import com.pvale.project.csc.api.enumerator.CscApiRequestParameter;
import com.pvale.project.csc.api.exception.CscServerErrorException;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.response.CscApiErrorResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import com.pvale.project.csc.ws.config.WsTestConfig;
import com.pvale.project.csc.ws.controller.CredentialsController;
import com.pvale.project.csc.ws.controller.InfoController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(WsTestConfig.class)
class CscApiControllerAdviceTest {

    public static final String CREDENTIALS_BASE_URL = "/credentials";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public CscApiService cscApiService;

    @Autowired
    @Qualifier("cscApiErrorMessages")
    private MessageSource messageSource;

    @Test
    void whenCallInfo_thenReturnServerError() throws Exception {

        Mockito.when(this.cscApiService.info(any())).thenThrow(new CscServerErrorException());

        InfoRequest infoRequest = new InfoRequest();

        this.mockMvc.perform(
                post(InfoController.INFO_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(infoRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.SERVER_ERROR, null)));

    }

    @Test
    void whenCallCredentialsList_thenReturnParameterNull() throws Exception {

        CredentialsListRequest credentialsListRequest = new CredentialsListRequest();
        credentialsListRequest.setUserId("userId");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_LIST_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsListRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.PARAMETER_NULL, new String[]{CscApiRequestParameter.USER_ID.getParameterName()})));


    }

    private String getJsonErrorMessage(CscApiErrorType cscApiErrorType, Object[] args) throws JsonProcessingException {
        String errorDescription = this.messageSource.getMessage(cscApiErrorType.getApiError(), args, Locale.ENGLISH);
        return this.objectMapper.writeValueAsString(new CscApiErrorResponse(cscApiErrorType, errorDescription));
    }

}