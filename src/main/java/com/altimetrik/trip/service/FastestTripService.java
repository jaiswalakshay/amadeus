package com.altimetrik.trip.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.altimetrik.trip.client.FetchFlightInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.trip.models.Trip;
import com.altimetrik.trip.models.Itineraries;
import com.altimetrik.trip.models.Output;
import com.altimetrik.trip.models.Results;

@Service
public class FastestTripService {
    @Autowired
    FetchFlightInfo fetchFlightInfo;

    public Trip getFastestTrip(String origin, String destination, String departureDate, String returnDate) {
        Results result = getFastestFlight(origin, destination, departureDate, returnDate);

        Trip trip = new Trip();
        trip.setHotels(null);
        trip.setResult(result);
        trip.setCarInfo(null);
        return trip;

    }

    public Results getFastestFlight(String origin, String destination, String departureDate, String returnDate) {
        Output results = fetchFlightInfo.getFlightInfo(origin, destination, departureDate, returnDate);
        List<Results> result = results.getResults();
        Results re = result.get(0);
        List<Itineraries> itineraries = re.getItineraries();
        Collections.sort(itineraries, new Comparator<Itineraries>() {
            public int compare(Itineraries I1, Itineraries I2) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                long duration1 = 0;
                long duration2 = 0;
                try {
                    duration1 = (sdf.parse(I1.getOutbound().getDuration())).getTime()
                            + (sdf.parse(I1.getInbound().getDuration())).getTime();
                    duration2 = (sdf.parse(I2.getOutbound().getDuration())).getTime()
                            + (sdf.parse(I2.getInbound().getDuration())).getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return Long.compare(duration1, duration2);
            }
        });

        re.setItineraries(re.getItineraries().subList(0, 1));
        return re;
    }
}
