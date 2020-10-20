package com.pvale.project.csc.bsl.service;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.response.InfoResponse;

/**
 * Provides a bridge between ws and bsl, i.e. processes requests, call core services and return responses
 */
public interface CscApiService {

    InfoResponse info(InfoRequest infoRequest) throws CscApiException;

}