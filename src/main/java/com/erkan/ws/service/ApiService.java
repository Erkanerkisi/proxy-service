package com.erkan.ws.service;

import com.erkan.ws.model.RestRequest;
import com.erkan.ws.model.SoapRequest;
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

    public ResponseEntity<String> callRestService(RestRequest restRequest) {

        Assert.notNull(restRequest.getUri(), "URI is required");
        Assert.notNull(restRequest.getHttpMethod(), "Http Method is required");
        //Set headers if exist
        HttpHeaders headers = getHeaders(restRequest.getHeaders());
        //Set Uri parameters for Get methods if exist any
        Map<String, ?> param = getUriVariables(restRequest.getParameters());
        //Set body and header
        HttpEntity<String> entity = new HttpEntity<>(restRequest.getRequestBody(), headers);
        //call Rest Service

        System.out.println("Header And Body -> " + entity.toString());

        ResponseEntity<String> response = restTemplate.exchange(
                restRequest.getUri(),
                getHttpMethod(restRequest.getHttpMethod()),
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

    public ResponseEntity<String> callSoapService(SoapRequest soapRequest) {
        Assert.notNull(soapRequest.getUri(), "URI is required");
        //Set headers if exist
        HttpHeaders headers = getHeaders(soapRequest.getHeaders());
        //Set body and header
        HttpEntity<String> entity = new HttpEntity<>(soapRequest.getRequestBody(), headers);
        //call Rest Service
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(
                    soapRequest.getUri(),
                    HttpMethod.POST,
                    entity,
                    String.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        //Return Response
        System.out.println(response);
        return response;
    }
}
