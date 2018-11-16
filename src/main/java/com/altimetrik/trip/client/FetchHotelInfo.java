package com.altimetrik.trip.client;

import com.altimetrik.trip.models.HotelResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class FetchHotelInfo {

    @Value("${amadeus.api.key}")
    String apikey;

    private RestTemplate restTemplate = new RestTemplate();

    private String getLowerHotelUri() {
        return "https://api.sandbox.amadeus.com/v1.2/hotels/search-airport?apikey=" + apikey;
    }

    public HotelResults getLowerFlightDetails(String location, String check_in, String check_out) {
        String URI = this.getLowerHotelUri() + "&location=" + location + "&check_in=" + check_in
                + "&check_out=" + check_out + "&number_of_results=" + 3;
        //System.out.println("/nURI " +URI);
        ResponseEntity<HotelResults> hotelResults = restTemplate.getForEntity(URI, HotelResults.class);

        //List<Hotels> hotels = ;
        //System.out.println(hotelResults);
        return hotelResults.getBody();
    }
}
