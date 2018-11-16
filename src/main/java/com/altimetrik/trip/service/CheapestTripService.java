package com.altimetrik.trip.service;

import com.altimetrik.trip.client.FetchFlightInfo;
import com.altimetrik.trip.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheapestTripService {

    @Autowired
    FetchFlightInfo fetchFlightInfo;

    public Trip getCheapestFlight(String origin, String destination, String departureDate, String returnDate) {
        Output results = fetchFlightInfo.getFlightInfo(origin, destination, departureDate, returnDate);
        Results result = results.getResults().get(0);
        result.setItineraries(result.getItineraries().subList(0, 3));

        Trip trip = new Trip();
        trip.setHotels(null);
        trip.setResult(result);
        trip.setCarInfo(null);

        return trip;

    }
}
