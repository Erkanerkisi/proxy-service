package com.erkan.ws.model;


import java.util.Map;

public class RestRequest extends Request {
    private String httpMethod;
    private Map<String, ?> parameters;

    public Map<String, ?> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, ?> parameters) {
        this.parameters = parameters;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

}
