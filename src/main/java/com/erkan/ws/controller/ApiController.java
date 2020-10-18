package com.erkan.ws.controller;

import com.erkan.ws.model.GenericRequest;
import com.erkan.ws.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", path = "/api")
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping(value = "/callRestService")
    ResponseEntity<String> callRestServiceController(@RequestBody GenericRequest genericRequest){
        return apiService.callRestService(genericRequest);
    }
}
