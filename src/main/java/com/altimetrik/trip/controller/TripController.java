package com.altimetrik.trip.controller;

import com.altimetrik.trip.models.Trip;
import com.altimetrik.trip.service.CheapestTripService;
import com.altimetrik.trip.service.FastestTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {

    @Autowired
    CheapestTripService cheapestTripService;

    @Autowired
    FastestTripService fastestTripService;

    @GetMapping("/cheapestflights")
    public Trip getChepeastFlight(@RequestParam("origin") String origin, @RequestParam("destination") String destination, @RequestParam("departureDate") String departureDate, @RequestParam("returnDate") String returnDate) {
        return cheapestTripService.getCheapestFlight(origin, destination, departureDate, returnDate);
    }

    @GetMapping("/fastestflights")
    public Trip getFastestFlight(@RequestParam("origin") String origin, @RequestParam("destination") String destination, @RequestParam("departureDate") String departureDate, @RequestParam("returnDate") String returnDate) {
        return fastestTripService.getFastestTrip(origin, destination, departureDate, returnDate);
    }


}
