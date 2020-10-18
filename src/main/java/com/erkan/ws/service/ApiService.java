package com.erkan.ws.service;

import com.erkan.ws.model.GenericRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> callRestService(GenericRequest genericRequest) {

        Assert.notNull(genericRequest.getUri(), "URI is required");
        Assert.notNull(genericRequest.getHttpMethod(), "Http Method is required");
        //Set headers if exist
        HttpHeaders headers = getHeaders(genericRequest.getHeaders());
        //Set Uri parameters for Get methods if exist any
        Map<String, ?> param = getUriVariables(genericRequest.getParameters());
        //Set body and header
        HttpEntity<String> entity = new HttpEntity<>(genericRequest.getRequestBody(), headers);
        //call Rest Service

        System.out.println("Header And Body -> " + entity.toString());

        ResponseEntity<String> response = restTemplate.exchange(
                genericRequest.getUri(),
                getHttpMethod(genericRequest.getHttpMethod()),
                entity,
                String.class,
                param);
        //Return Response
        System.out.println(response);
        return response;
    }

    private HttpHeaders getHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headers != null)
            headers.forEach(httpHeaders::set);
        return httpHeaders;
    }

    private HttpMethod getHttpMethod(String method) {
        if (method.equals("GET")) return HttpMethod.GET;
        if (method.equals("POST")) return HttpMethod.POST;
        if (method.equals("PUT")) return HttpMethod.PUT;
        if (method.equals("DELETE")) return HttpMethod.DELETE;
        throw new IllegalArgumentException("http method not found!");
    }

    private Map<String, ?> getUriVariables(Map<String, ?> map) {
        if (map != null) return map;
        else return new HashMap<>();
    }
}
