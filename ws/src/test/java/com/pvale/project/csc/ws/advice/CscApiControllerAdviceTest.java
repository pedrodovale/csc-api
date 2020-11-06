package com.pvale.project.csc.ws.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.enumerator.CscApiErrorType;
import com.pvale.project.csc.api.enumerator.CscApiRequestParameter;
import com.pvale.project.csc.api.exception.CscCredentialDisabledException;
import com.pvale.project.csc.api.exception.CscEmptyHashArrayException;
import com.pvale.project.csc.api.exception.CscExpiredCertificateException;
import com.pvale.project.csc.api.exception.CscInvalidBase64ParameterException;
import com.pvale.project.csc.api.exception.CscInvalidDigestValueLengthException;
import com.pvale.project.csc.api.exception.CscInvalidOtpException;
import com.pvale.project.csc.api.exception.CscInvalidParameterException;
import com.pvale.project.csc.api.exception.CscInvalidParameterValueException;
import com.pvale.project.csc.api.exception.CscInvalidPinException;
import com.pvale.project.csc.api.exception.CscNumberSignaturesTooHighException;
import com.pvale.project.csc.api.exception.CscOtpLockedException;
import com.pvale.project.csc.api.exception.CscPinLockedException;
import com.pvale.project.csc.api.exception.CscRevokedCertificateException;
import com.pvale.project.csc.api.exception.CscSadExpiredException;
import com.pvale.project.csc.api.exception.CscServerErrorException;
import com.pvale.project.csc.api.exception.CscSuspendedCertificateException;
import com.pvale.project.csc.api.exception.CscUnauthorizedHashException;
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

    @Test
    void whenCallCredentialsAuthorize_thenReturnNumberSignaturesTooHigh() throws Exception {

        CscNumberSignaturesTooHighException numberSignaturesTooHighException = new CscNumberSignaturesTooHighException();
        Mockito.when(this.cscApiService.credentialsAuthorize(any())).thenThrow(numberSignaturesTooHighException);

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId("credentialId");
        credentialsAuthorizeRequest.setNumSignatures(1000);

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.NUM_SIGNATURES_TOO_HIGH, null)));
    }

    @Test
    void whenCallCredentialsAuthorize_thenReturnInvalidOtp() throws Exception {

        CscInvalidOtpException invalidOtpException = new CscInvalidOtpException();
        Mockito.when(this.cscApiService.credentialsAuthorize(any())).thenThrow(invalidOtpException);

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId("credentialId");
        credentialsAuthorizeRequest.setNumSignatures(1);
        credentialsAuthorizeRequest.setOtp("invalidOtp");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_OTP, null)));

    }

    @Test
    void whenCallCredentialsAuthorize_thenReturnLockedOtp() throws Exception {

        CscOtpLockedException otpLockedException = new CscOtpLockedException();
        Mockito.when(this.cscApiService.credentialsAuthorize(any())).thenThrow(otpLockedException);

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId("credentialId");
        credentialsAuthorizeRequest.setNumSignatures(1);
        credentialsAuthorizeRequest.setOtp("lockedOtp");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.LOCKED_OTP, null)));

    }

    @Test
    void whenCallCredentialsAuthorize_thenReturnInvalidPin() throws Exception {

        CscInvalidPinException invalidPinException = new CscInvalidPinException();
        Mockito.when(this.cscApiService.credentialsAuthorize(any())).thenThrow(invalidPinException);

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId("credentialId");
        credentialsAuthorizeRequest.setNumSignatures(1);
        credentialsAuthorizeRequest.setPin("invalidPin");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_PIN, null)));

    }

    @Test
    void whenCallCredentialsAuthorize_thenReturnLockedPin() throws Exception {

        CscPinLockedException pinLockedException = new CscPinLockedException();
        Mockito.when(this.cscApiService.credentialsAuthorize(any())).thenThrow(pinLockedException);

        CredentialsAuthorizeRequest credentialsAuthorizeRequest = new CredentialsAuthorizeRequest();
        credentialsAuthorizeRequest.setCredentialId("credentialId");
        credentialsAuthorizeRequest.setNumSignatures(1);
        credentialsAuthorizeRequest.setPin("lockedPin");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_AUTHORIZE_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsAuthorizeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.LOCKED_PIN, null)));

    }

    @Test
    void whenCallCredentialsExtendTransaction_thenReturnInvalidBase64ParameterErrorForInvalidBase64Hash() throws Exception {

        CscInvalidBase64ParameterException invalidBase64ParameterException = new CscInvalidBase64ParameterException(CscApiRequestParameter.HASH);
        Mockito.when(this.cscApiService.credentialsExtendTransaction(any())).thenThrow(invalidBase64ParameterException);

        CredentialsExtendTransactionRequest credentialsExtendTransactionRequest = new CredentialsExtendTransactionRequest();
        credentialsExtendTransactionRequest.setCredentialId("credentialId");
        credentialsExtendTransactionRequest.setSad("sad");
        credentialsExtendTransactionRequest.setHash(Collections.singleton("notBase64String.!@#"));

        CscApiRequestParameter cscApiRequestParameter = invalidBase64ParameterException.getCscApiRequestParameter();
        String parameterName = cscApiRequestParameter.getParameterName();
        String parameterType = cscApiRequestParameter.getParameterType();

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsExtendTransactionRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_BASE64_PARAMETER, new String[]{parameterName, parameterType})));

    }

    @Test
    void whenCallSignaturesSignHash_thenReturnUnauthorizedHashError() throws Exception {

        CscUnauthorizedHashException unauthorizedHashException = new CscUnauthorizedHashException();
        Mockito.when(this.cscApiService.signaturesSignHash(any())).thenThrow(unauthorizedHashException);

        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId("credentialId");
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setHash(Collections.singleton("unauthorizedHash"));
        signaturesSignHashRequest.setSignAlgo("signAlgo");

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.UNAUTHORIZED_HASH, null)));

    }

    @Test
    void whenCallCredentialsExtendTransaction_thenReturnSadExpiredError() throws Exception {

        CscSadExpiredException sadExpiredException = new CscSadExpiredException();
        Mockito.when(this.cscApiService.credentialsExtendTransaction(any())).thenThrow(sadExpiredException);

        CredentialsExtendTransactionRequest credentialsExtendTransactionRequest = new CredentialsExtendTransactionRequest();
        credentialsExtendTransactionRequest.setCredentialId("credentialId");
        credentialsExtendTransactionRequest.setSad("expiredSad");

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsExtendTransactionRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.SAD_EXPIRED, null)));

    }

    @Test
    void whenCallCredentialsExtendTransaction_thenReturnInvalidDigestValueLengthError() throws Exception {

        CscInvalidDigestValueLengthException invalidDigestValueLengthException = new CscInvalidDigestValueLengthException();
        Mockito.when(this.cscApiService.credentialsExtendTransaction(any())).thenThrow(invalidDigestValueLengthException);

        CredentialsExtendTransactionRequest credentialsExtendTransactionRequest = new CredentialsExtendTransactionRequest();
        credentialsExtendTransactionRequest.setCredentialId("credentialId");
        credentialsExtendTransactionRequest.setSad("sad");
        credentialsExtendTransactionRequest.setHash(Collections.singleton("invalidDigestValueLengthHash"));

        this.mockMvc.perform(
                post(CREDENTIALS_BASE_URL + CredentialsController.CREDENTIALS_EXTEND_TRANSACTION_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(credentialsExtendTransactionRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.INVALID_DIGEST_VALUE_LENGTH, null)));

    }

    @Test
    void whenCallSignaturesSignHash_thenReturnExpiredCertificateError() throws Exception {

        CscExpiredCertificateException expiredCertificateException = new CscExpiredCertificateException("O=[organization],CN=[common_name]");
        Mockito.when(this.cscApiService.signaturesSignHash(any())).thenThrow(expiredCertificateException);

        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId("credentialId");
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setHash(Collections.singleton("hash"));
        signaturesSignHashRequest.setSignAlgo("signAlgo");

        String subjectDn = expiredCertificateException.getSubjectDn();

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.EXPIRED_CERTIFICATE, new String[]{subjectDn})));

    }

    @Test
    void whenCallSignaturesSignHash_thenReturnRevokedCertificateError() throws Exception {

        CscRevokedCertificateException revokedCertificateException = new CscRevokedCertificateException("O=[organization],CN=[common_name]");
        Mockito.when(this.cscApiService.signaturesSignHash(any())).thenThrow(revokedCertificateException);

        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId("credentialId");
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setHash(Collections.singleton("hash"));
        signaturesSignHashRequest.setSignAlgo("signAlgo");

        String subjectDn = revokedCertificateException.getSubjectDn();

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.REVOKED_CERTIFICATE, new String[]{subjectDn})));

    }

    @Test
    void whenCallSignaturesSignHash_thenReturnSuspendedCertificateError() throws Exception {

        CscSuspendedCertificateException suspendedCertificateException = new CscSuspendedCertificateException("O=[organization],CN=[common_name]");
        Mockito.when(this.cscApiService.signaturesSignHash(any())).thenThrow(suspendedCertificateException);

        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId("credentialId");
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setHash(Collections.singleton("hash"));
        signaturesSignHashRequest.setSignAlgo("signAlgo");

        String subjectDn = suspendedCertificateException.getSubjectDn();

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.SUSPENDED_CERTIFICATE, new String[]{subjectDn})));

    }

    @Test
    void whenCallSignaturesSignHash_thenReturnEmptyHashArrayError() throws Exception {

        CscEmptyHashArrayException emptyHashArrayException = new CscEmptyHashArrayException();
        Mockito.when(this.cscApiService.signaturesSignHash(any())).thenThrow(emptyHashArrayException);

        SignaturesSignHashRequest signaturesSignHashRequest = new SignaturesSignHashRequest();
        signaturesSignHashRequest.setCredentialId("credentialId");
        signaturesSignHashRequest.setSad("sad");
        signaturesSignHashRequest.setHash(Collections.emptySet());
        signaturesSignHashRequest.setSignAlgo("signAlgo");

        this.mockMvc.perform(
                post(SIGNATURES_BASE_URL + SignaturesController.SIGNATURES_SIGN_HASH_CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(signaturesSignHashRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(this.getJsonErrorMessage(CscApiErrorType.EMPTY_HASH_ARRAY, null)));

    }

    private String getJsonErrorMessage(CscApiErrorType cscApiErrorType, Object[] args) throws JsonProcessingException {
        String errorDescription = this.messageSource.getMessage(cscApiErrorType.getApiError(), args, Locale.ENGLISH);
        return this.objectMapper.writeValueAsString(new CscApiErrorResponse(cscApiErrorType, errorDescription));
    }
}