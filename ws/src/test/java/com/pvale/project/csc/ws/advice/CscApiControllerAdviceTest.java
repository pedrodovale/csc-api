package com.pvale.project.csc.ws.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.enumerator.CscApiErrorType;
import com.pvale.project.csc.api.enumerator.CscApiRequestParameter;
import com.pvale.project.csc.api.exception.CscCredentialDisabledException;
import com.pvale.project.csc.api.exception.CscInvalidParameterException;
import com.pvale.project.csc.api.exception.CscInvalidParameterValueException;
import com.pvale.project.csc.api.exception.CscServerErrorException;
import com.pvale.project.csc.api.request.CredentialsAuthorizeRequest;
import com.pvale.project.csc.api.request.CredentialsExtendTransactionRequest;
import com.pvale.project.csc.api.request.CredentialsInfoRequest;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.request.SignaturesSignHashRequest;
import com.pvale.project.csc.api.response.CscApiErrorResponse;
import com.pvale.project.csc.bsl.service.CscApiService;
import com.pvale.project.csc.ws.config.WsTestConfig;
import com.pvale.project.csc.ws.controller.CredentialsController;
import com.pvale.project.csc.ws.controller.InfoController;
import com.pvale.project.csc.ws.controller.SignaturesController;
import org.json.JSONArray;
import org.json.JSONObject;
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

import java.util.Collections;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(WsTestConfig.class)
class CscApiControllerAdviceTest {

    public static final String CREDENTIALS_BASE_URL = "/credentials";
    public static final String SIGNATURES_BASE_URL = "/signatures";

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
    void whenCallCredentialsList_thenReturnParameterNullForNotNullUserId() throws Exception {

        CredentialsListRequest credentialsListRequest = new CredentialsListRequest();
        credentialsListRequest.setUserId("userId");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_LIST_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsListRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.PARAMETER_NULL, new String[]{CscApiRequestParameter.USER_ID.getParameterName()})));

    }

    @Test
    void whenCallCredentialsInfo_thenReturnMissingOrInvalidParameterForMissingStringCredentialId() throws Exception {

        CredentialsInfoRequest credentialsInfoRequest = new CredentialsInfoRequest();

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_INFO_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsInfoRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.MISSING_OR_INVALID_TYPE, new String[]{"string", CscApiRequestParameter.CREDENTIAL_ID.getParameterName()})));

    }

    @Test
    void whenCallCredentialsAuthorize_thenReturnMissingOrInvalidParameterForMissingIntegerNumSignatures() throws Exception {

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId("credentialId");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.MISSING_OR_INVALID_TYPE, new String[]{"integer", CscApiRequestParameter.NUM_SIGNATURES.getParameterName()})));

    }

    @Test
    void whenCallSignaturesSignHash_thenReturnMissingOrInvalidParameterForMissingArrayHash() throws Exception {

        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId("credentialId");
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setSignAlgo("signAlgo");

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.MISSING_OR_INVALID_TYPE, new String[]{"array", CscApiRequestParameter.HASH.getParameterName()})));

    }

    @Test
    void whenCallCredentialsSendOtp_thenReturnMissingOrInvalidParameterForInvalidStringCredentialId() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("credentialID", new JSONArray()); // credentialId should be string, not array
        String credentialsSendOtpJsonRequest = jsonObject.toString();

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_SEND_OTP_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(credentialsSendOtpJsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.MISSING_OR_INVALID_TYPE, new String[]{"string", CscApiRequestParameter.CREDENTIAL_ID.getParameterName()})));

    }

    @Test
    void whenCallCredentialsExtendTransaction_thenReturnMissingOrInvalidParameterForInvalidArrayHash() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("credentialID", "credentialId");
        jsonObject.put("SAD", "sad");
        jsonObject.put("hash", "hash"); // hash should be an array, not a string
        String credentialsExtendTransactionJsonRequest = jsonObject.toString();

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(credentialsExtendTransactionJsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.MISSING_OR_INVALID_TYPE, new String[]{"array", CscApiRequestParameter.HASH.getParameterName()})));

    }

    @Test
    void whenCallInfo_thenReturnInvalidRequestForMalformedJsonString() throws Exception {

        String malformedInfoJsonRequest = "{";

        this.mockMvc.perform(
                post(InfoController.INFO_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedInfoJsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_REQUEST, null)));

    }

    @Test
    void whenCallCredentialsSendOtp_thenReturnInvalidRequestForUnrecognizedProperty() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("credentialId", "credentialId"); // 'Id' should be 'ID' in parameter name
        String credentialsSendOtpJsonRequest = jsonObject.toString();

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_SEND_OTP_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(credentialsSendOtpJsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_REQUEST, null)));

    }

    @Test
    void whenCallInfo_thenReturnMethodNotAllowed() throws Exception {

        this.mockMvc.perform(
                get(InfoController.INFO_CONTEXT_PATH))
                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(
                get(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_LIST_CONTEXT_PATH))
                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(
                get(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_INFO_CONTEXT_PATH))
                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(
                get(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH))
                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(
                get(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH))
                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(
                get(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_SEND_OTP_CONTEXT_PATH))
                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(
                get(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void whenCallInfo_thenReturnUnsupportedMediaType() throws Exception {

        this.mockMvc.perform(
                post(InfoController.INFO_CONTEXT_PATH)
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_LIST_CONTEXT_PATH)
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_INFO_CONTEXT_PATH)
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_SEND_OTP_CONTEXT_PATH)
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void whenCallCredentialsExtendTransaction_thenReturnInvalidParameterErrorForInvalidCredentialId() throws Exception {

        CscInvalidParameterException invalidParameterException = new CscInvalidParameterException(CscApiRequestParameter.CREDENTIAL_ID);
        Mockito.when(this.cscApiService.credentialsExtendTransaction(any())).thenThrow(invalidParameterException);

        CredentialsExtendTransactionRequest credentialsExtendTransactionRequest = new CredentialsExtendTransactionRequest();
        credentialsExtendTransactionRequest.setCredentialId("wrongCredentialId");
        credentialsExtendTransactionRequest.setSad("sad");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsExtendTransactionRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_PARAMETER, new String[]{invalidParameterException.getCscApiRequestParameter().getParameterName()})));

    }

    @Test
    void whenCallSignaturesSignHash_thenReturnCredentialIsDisabledError() throws Exception {

        CscCredentialDisabledException credentialDisabledException = new CscCredentialDisabledException();
        Mockito.when(this.cscApiService.signaturesSignHash(any())).thenThrow(credentialDisabledException);

        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId("disabledCredential");
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setHash(Collections.emptySet());
        signaturesSignHashRequest.setSignAlgo("signAlgo");

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.CREDENTIAL_IS_DISABLED, null)));

    }

    @Test
    public void whenCallCredentialsAuthorize_thenReturnInvalidParameterValueForInvalidCertificates() throws Exception {

        CscInvalidParameterValueException invalidParameterValueException = new CscInvalidParameterValueException(CscApiRequestParameter.NUM_SIGNATURES);
        Mockito.when(this.cscApiService.credentialsAuthorize(any())).thenThrow(invalidParameterValueException);

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId("credentialId");
        credentialsAuthorizeRequest.setNumSignatures(-1); // invalid

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_PARAMETER_VALUE, new String[]{invalidParameterValueException.getCscApiRequestParameter().getParameterName()})));


    }

    private String getJsonErrorMessage(CscApiErrorType cscApiErrorType, Object[] args) throws JsonProcessingException {
        String errorDescription = this.messageSource.getMessage(cscApiErrorType.getApiError(), args, Locale.ENGLISH);
        return this.objectMapper.writeValueAsString(new CscApiErrorResponse(cscApiErrorType, errorDescription));
    }
}