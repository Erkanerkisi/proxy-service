package com.erkan.ws.service;

import com.erkan.ws.model.RestRequest;
import com.erkan.ws.model.SoapRequest;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApiServiceTest {

    @InjectMocks
    private ApiService apiService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    void callRestService_missingURI() {

        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");

        RestRequest restRequest = new RestRequest();
        restRequest.setHttpMethod("POST");
        restRequest.setHeaders(headers);
        restRequest.setRequestBody("{\"id\":\"1\"}");
        Assertions.assertThrows(IllegalArgumentException.class, () -> apiService.callRestService(restRequest));
    }

    @Test
    void callRestService_missingHttpMethod() {

        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");

        RestRequest restRequest = new RestRequest();
        restRequest.setUri("https://www.google.com/");
        restRequest.setHeaders(headers);
        restRequest.setRequestBody("{\"id\":\"1\"}");
        Assertions.assertThrows(IllegalArgumentException.class, () -> apiService.callRestService(restRequest));
    }

    @Test
    void callRestService_restTemplate() {

        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");
        ResponseEntity<String> responseEntity = ResponseEntity.of(Optional.ofNullable("{\"response\" : \"erkan\"}"));
        RestRequest restRequest = new RestRequest();
        restRequest.setHttpMethod("POST");
        restRequest.setUri("https://www.google.com/");
        restRequest.setHeaders(headers);
        restRequest.setRequestBody("{\"id\":\"1\"}");

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class),
                ArgumentMatchers.<Class<String>>any(),
                anyMap()))
                .thenReturn(responseEntity);
        Assert.assertEquals(responseEntity.getBody(), apiService.callRestService(restRequest).getBody());
    }

    @Test
    void callSoapService_missingURI() {

        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");

        SoapRequest soapRequest = new SoapRequest();
        soapRequest.setHeaders(headers);
        Assertions.assertThrows(IllegalArgumentException.class, () -> apiService.callSoapService(soapRequest));
    }

    @Test
    void callSoapService_restTemplate() {

        Map headers = new HashMap<>();
        headers.put("content-type", "application/json");
        ResponseEntity<String> responseEntity = ResponseEntity.of(Optional.ofNullable("{\"response\" : \"erkan\"}"));
        SoapRequest soapRequest = new SoapRequest();
        soapRequest.setUri("https://www.google.com/");
        soapRequest.setHeaders(headers);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        Assert.assertEquals(responseEntity.getBody(), apiService.callSoapService(soapRequest).getBody());
    }
}