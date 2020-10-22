package com.pvale.project.csc.bsl.service;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.CredentialsAuthorizeRequest;
import com.pvale.project.csc.api.request.CredentialsExtendTransactionRequest;
import com.pvale.project.csc.api.request.CredentialsInfoRequest;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.response.CredentialsAuthorizeResponse;
import com.pvale.project.csc.api.response.CredentialsExtendTransactionResponse;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsListResponse;
import com.pvale.project.csc.api.response.InfoResponse;
import com.pvale.project.csc.bsl.util.CscApiSampleResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CscApiServiceImpl implements CscApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CscApiServiceImpl.class);

    @Override
    public InfoResponse info(InfoRequest infoRequest) throws CscApiException {
        LOGGER.info("@info - request: {}", infoRequest);
        return CscApiSampleResponses.info();
    }

    @Override
    public CredentialsListResponse credentialsList(CredentialsListRequest credentialsListRequest) {
        LOGGER.info("@credentialsList - request: {}", credentialsListRequest);
        return CscApiSampleResponses.credentialsList();
    }

    @Override
    public CredentialsInfoResponse credentialsInfo(CredentialsInfoRequest credentialsInfoRequest) throws CscApiException {
        LOGGER.info("@credentialsInfo - request: {}", credentialsInfoRequest);
        return CscApiSampleResponses.credentialsInfo();
    }

    @Override
    public CredentialsAuthorizeResponse credentialsAuthorize(CredentialsAuthorizeRequest credentialsAuthorizeRequest) throws CscApiException {
        LOGGER.info("@credentialsAuthorize - request: {}", credentialsAuthorizeRequest);
        return CscApiSampleResponses.credentialsAuthorize();
    }

    @Override
    public CredentialsExtendTransactionResponse credentialsExtendTransaction(CredentialsExtendTransactionRequest credentialsExtendTransactionRequest) throws CscApiException {
        LOGGER.info("@credentialsExtendTransaction - request: {}", credentialsExtendTransactionRequest);
        return CscApiSampleResponses.credentialsExtendTransaction();
    }
}