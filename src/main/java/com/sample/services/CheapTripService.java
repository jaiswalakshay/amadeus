package com.sample.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheapTripService {
    private final String url = "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?";
    private final String apiKey = "1AZaNBScC2jCA67MmHxxUqXZTIpPQGA9";

    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private String getQuer(String originCity,
                           String destCity,
                           Date startDate,
                           Integer noOfDays,
                           String comfortOpts) throws ParseException {
        String todayWithZeroTime = formatter.format(startDate);
        System.out.print("Date =====> "+ todayWithZeroTime.toString());
        return "origin="+originCity+"&destination="+destCity+"&departure_date="+todayWithZeroTime+"&apikey="+apiKey;
    }


    public List<JsonNode> getTrips( String originCity,
                              String destCity,
                              Date startDate,
                              Integer noOfDays,
                              String comfortOpts){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity = null;
        try {
            System.err.println("URL : "+ url + getQuer(originCity,destCity,startDate,noOfDays,comfortOpts));
            responseEntity = restTemplate.exchange(url + getQuer(originCity,destCity,startDate,noOfDays,comfortOpts), HttpMethod.GET, null, JsonNode.class);
        } catch (Exception e) {
            System.err.println("Error while parsing date" + e.getMessage());
            return null;
        }
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<JsonNode> resultFor3Trips = new ArrayList<>();
            for(int i=0;i<3;i++){
                resultFor3Trips.add(responseEntity.getBody().get("results").get(i));
            }
            return resultFor3Trips;
        } else {
            return null;
        }
    }
}
