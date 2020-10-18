package com.erkan.ws.model;


import java.util.Map;

public class GenericRequest {
    private String uri;
    private String httpMethod;
    private Map<String,String> headers;
    private String requestBody;
    private Map<String, ?> parameters;

    public Map<String, ?> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, ?> parameters) {
        this.parameters = parameters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String url) {
        this.uri = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
