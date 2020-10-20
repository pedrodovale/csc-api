package com.pvale.project.csc.bsl.service;

import com.pvale.project.csc.api.exception.CscApiException;
import com.pvale.project.csc.api.request.InfoRequest;
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
}