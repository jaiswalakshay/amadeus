package com.altimetrik.trip.service;

import com.altimetrik.trip.client.FetchCarInfo;
import com.altimetrik.trip.client.FetchFlightInfo;
import com.altimetrik.trip.client.FetchHotelInfo;
import com.altimetrik.trip.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheapestTripService {

    @Autowired
    FetchFlightInfo fetchFlightInfo;
    @Autowired
    FetchHotelInfo fetchHotelInfo;

    @Autowired
    FetchCarInfo fetchCarInfo;

    public Trip getCheapestFlight(String origin, String destination, String departureDate, String returnDate) {
        Output results = fetchFlightInfo.getLowerFlightDetails(origin, destination, departureDate, returnDate);
        Results result = results.getResults().get(0);
        result.setItineraries(result.getItineraries().subList(0, 3));

        Hotels hotel = getCheapestHotel(destination, departureDate, returnDate);

        CarInfo carinfo = getCheapestCar(destination, departureDate, returnDate);
        carinfo.setCars(carinfo.getCars().subList(0, 1));

        Trip trip = new Trip();
        trip.setHotels(hotel);
        trip.setResult(result);
        trip.setCarInfo(carinfo);

        return trip;

    }

    public Hotels getCheapestHotel(String location, String check_in, String check_out) {
        HotelResults hotels = fetchHotelInfo.getLowerFlightDetails(location, check_in, check_out);
        return hotels.getResults().get(0);
    }

    public CarInfo getCheapestCar(String location, String pick_up, String drop_off) {
        CarResults carResults = fetchCarInfo.getLowerFlightDetails(location, pick_up, drop_off);

        return carResults.getResults().get(0);

    }

}
