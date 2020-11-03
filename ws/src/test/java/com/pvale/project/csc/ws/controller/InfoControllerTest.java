package com.pvale.project.csc.ws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvale.project.csc.api.request.InfoRequest;
import com.pvale.project.csc.api.response.InfoResponse;
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

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(WsTestConfig.class)
public class InfoControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public CscApiService cscApiService;

    @Test
    void whenCallDefault_thenReturnCscApiInfo() throws Exception {
        this.mockMvc.perform(get(InfoController.ROOT_CONTEXT_PATH))
                .andExpect(status().isOk());
    }

    @Test
    void whenCallInfo_thenReturnCscApiInfo() throws Exception {

        InfoResponse mockedResponse = CscApiSampleResponses.info();
        Mockito.when(this.cscApiService.info(any())).thenReturn(mockedResponse);

        InfoRequest infoRequest = this.infoRequest();

        this.mockMvc.perform(post(InfoController.INFO_CONTEXT_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(infoRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(this.objectMapper.writeValueAsString(mockedResponse))));
    }

    private InfoRequest infoRequest() {
        return new InfoRequest();
    }
}