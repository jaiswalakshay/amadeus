package com.altimetrik.trip.client;

import com.altimetrik.trip.models.Output;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class FetchFlightInfo {
    @Value("${amadeus.api.key}")
    String apikey;

    private RestTemplate restTemplate = new RestTemplate();

    private String getURI() {
        return "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?apikey=" + apikey;
    }

    public Output getFlightInfo(String origin, String destination, String departureDate, String returnDate) {
        String URI = this.getURI() + "&origin=" + origin + "&destination=" + destination
                + "&departure_date=" + departureDate + "&return_date=" + returnDate;
        //System.out.println("/nURI " +URI);
        ResponseEntity<Output> output = restTemplate.getForEntity(URI, Output.class);
        if (output.getBody() != null) {
            return output.getBody();
        } else
            return null;
    }
}
