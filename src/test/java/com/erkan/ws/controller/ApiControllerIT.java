package com.erkan.ws.controller;

import com.erkan.ws.model.RestRequest;
import com.erkan.ws.service.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApiService apiService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void callRestServiceController_statusOK() throws Exception {

        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");

        RestRequest restRequest = new RestRequest();
        restRequest.setHttpMethod("POST");
        restRequest.setUri("ads");
        restRequest.setHeaders(headers);
        restRequest.setRequestBody("{\"id\":\"1\"}");

        ResultActions resultActions = mockMvc.perform(post("/api/callRestService")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(restRequest))).andExpect(status().isOk());
    }

    @Test
    void callRestServiceController_ValidInputSerialization() throws Exception {

        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");

        RestRequest restRequest = new RestRequest();
        restRequest.setHttpMethod("POST");
        restRequest.setUri("ads");
        restRequest.setHeaders(headers);
        restRequest.setRequestBody("{\"id\":\"1\"}");

        ResultActions resultActions = mockMvc.perform(post("/api/callRestService")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(restRequest))).andExpect(status().isOk());
        ArgumentCaptor<RestRequest> a =ArgumentCaptor.forClass(RestRequest.class);
        verify(apiService,times(1)).callRestService(a.capture());
        Assert.assertEquals(restRequest.getUri(), a.getValue().getUri());

    }
/*
    @Test
    void callRestServiceController_missingURI() throws Exception {
        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");

        RestRequest restRequest = new RestRequest();
        restRequest.setHttpMethod("POST");
        restRequest.setHeaders(headers);
        restRequest.setRequestBody("{\"id\":\"1\"}");

        mockMvc.perform(post("/api/callRestService")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(restRequest)));
            System.out.println("sda");
    }*/
}