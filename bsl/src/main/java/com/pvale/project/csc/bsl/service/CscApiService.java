package com.pvale.project.csc.bsl.service;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.CredentialsAuthorizeRequest;
import com.pvale.project.csc.api.request.CredentialsExtendTransactionRequest;
import com.pvale.project.csc.api.request.CredentialsInfoRequest;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.request.CredentialsSendOtpRequest;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.request.SignaturesSignHashRequest;
import com.pvale.project.csc.api.response.CredentialsAuthorizeResponse;
import com.pvale.project.csc.api.response.CredentialsExtendTransactionResponse;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsListResponse;
import com.pvale.project.csc.api.response.InfoResponse;
import com.pvale.project.csc.api.response.SignaturesSignHashResponse;

/**
 * Provides a bridge between ws and bsl, i.e. processes requests, call core services and return responses
 */
public interface CscApiService {

    InfoResponse info(InfoRequest infoRequest) throws CscApiException;

    CredentialsListResponse credentialsList(CredentialsListRequest credentialsListRequest) throws CscApiException;

    CredentialsInfoResponse credentialsInfo(CredentialsInfoRequest credentialsInfoRequest) throws CscApiException;

    CredentialsAuthorizeResponse credentialsAuthorize(CredentialsAuthorizeRequest credentialsAuthorizeRequest) throws CscApiException;

    CredentialsExtendTransactionResponse credentialsExtendTransaction(CredentialsExtendTransactionRequest credentialsExtendTransactionRequest) throws CscApiException;

    void credentialsSendOtp(CredentialsSendOtpRequest credentialsSendOtpRequest);

    SignaturesSignHashResponse signaturesSignHash(SignaturesSignHashRequest signaturesSignHashRequest);
}