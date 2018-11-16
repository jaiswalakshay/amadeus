package com.sample.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.ServiceMode;
import java.util.Date;

@Service
public class CheapTripService {
    private final String url = "http://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?";
    private final String apiKey = "PUT_APIKEY";
    private String getQuer(String originCity,
                           String destCity,
                           Date startDate,
                           Integer noOfDays,
                           String comfortOpts){
        return "origin="+originCity+"&destination="+destCity+"&departure_date="+startDate.toString()+"&apikey="+apiKey;
    }


    public JsonNode getTrips( String originCity,
                              String destCity,
                              Date startDate,
                              Integer noOfDays,
                              String comfortOpts){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(url + getQuer(originCity,destCity,startDate,noOfDays,comfortOpts), HttpMethod.GET, null, JsonNode.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }
}
