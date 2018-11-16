package com.altimetrik.trip.client;

import com.altimetrik.trip.models.CarResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class FetchCarInfo {

    @Value("${amadeus.api.key}")
    String apikey;

    RestTemplate restTemplate = new RestTemplate();

    private String getLowestPriceFlightUri() {
        return "https://api.sandbox.amadeus.com/v1.2/cars/search-airport?apikey=" + apikey;
    }

    public CarResults getLowerFlightDetails(String location, String pick_up, String drop_off) {
        String URI = this.getLowestPriceFlightUri() + "&location=" + location + "&pick_up=" + pick_up
                + "&drop_off=" + drop_off;
        //System.out.println("/nURI " +URI);
        ResponseEntity<CarResults> output = restTemplate.getForEntity(URI, CarResults.class);

        return output.getBody();
    }
}
