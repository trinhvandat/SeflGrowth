package com.selfgrowth.model.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtils {

    private final LoadBalancerClient loadBalancer;
    private final String port;

    private String serviceAddress = null;

    @Autowired
    public ServiceUtils(@Value("${server.port}") String port, LoadBalancerClient loadBalancer){
        this.port = port;
        this.loadBalancer = loadBalancer;
    }

    // TODO: How to relay the transfer encoding??? The code below makes the fallback method to kick in...
    public <T>ResponseEntity<T> createResponse(ResponseEntity<T> result){

        ResponseEntity<T> response = createResponse(result.getBody(), result.getStatusCode());

        return response;
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus statusCode) {

        return new ResponseEntity<>(body, statusCode);
    }

    public <T>ResponseEntity<T> createOkResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }
}
