package com.pvale.project.csc.bsl.service;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.CredentialsAuthorizeRequest;
import com.pvale.project.csc.api.request.CredentialsInfoRequest;
import com.pvale.project.csc.api.request.CredentialsListRequest;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.response.CredentialsAuthorizeResponse;
import com.pvale.project.csc.api.response.CredentialsInfoResponse;
import com.pvale.project.csc.api.response.CredentialsListResponse;
import com.pvale.project.csc.api.response.InfoResponse;

/**
 * Provides a bridge between ws and bsl, i.e. processes requests, call core services and return responses
 */
public interface CscApiService {

    InfoResponse info(InfoRequest infoRequest) throws CscApiException;

    CredentialsListResponse credentialsList(CredentialsListRequest credentialsListRequest) throws CscApiException;

    CredentialsInfoResponse credentialsInfo(CredentialsInfoRequest credentialsInfoRequest) throws CscApiException;

    CredentialsAuthorizeResponse credentialsAuthorize(CredentialsAuthorizeRequest credentialsAuthorizeRequest) throws CscApiException;
}