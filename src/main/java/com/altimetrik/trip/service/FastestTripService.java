package com.altimetrik.trip.service;

import com.altimetrik.trip.client.FetchCarInfo;
import com.altimetrik.trip.client.FetchFlightInfo;
import com.altimetrik.trip.client.FetchHotelInfo;
import com.altimetrik.trip.client.FetchMapInfo;
import com.altimetrik.trip.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class FastestTripService {
    @Autowired
    FetchFlightInfo fetchFlightInfo;
    @Autowired
    FetchHotelInfo fetchHotelInfo;

    @Autowired
    FetchCarInfo fetchCarInfo;

    @Autowired
    FetchMapInfo fetchMapInfo;

    public Trip getFastestTrip(String origin, String destination, String departureDate, String returnDate) {
        Results result = getFastestFlight(origin, destination, departureDate, returnDate);
        Hotels hotel = getFastestHotel(destination, departureDate, returnDate);
        CarInfo car = getFastestCar(destination, departureDate, returnDate);
        Trip trip = new Trip();
        trip.setHotels(hotel);
        trip.setResult(result);
        trip.setCarInfo(car);
        return trip;

    }

    public Results getFastestFlight(String origin, String destination, String departureDate, String returnDate) {
        Output results = fetchFlightInfo.getLowerFlightDetails(origin, destination, departureDate, returnDate);
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

    public Hotels getFastestHotel(String origin, String check_in, String check_out) {

        HotelResults hotelResults = fetchHotelInfo.getLowerFlightDetails(origin, check_in, check_out);
        List<Hotels> hotels = hotelResults.getResults();
        System.out.println(hotels.get(0).getLocation());
        List<Location> hotelDestinations = new ArrayList<Location>();
        for (Hotels hotel : hotels) {
            hotelDestinations.add(hotel.getLocation());
        }
        DistanceRows hotelDistances = getDistanceInfo(origin, hotelDestinations);

        for (int i = 0; i < hotelDestinations.size(); i++) {
            hotels.get(i).setDurationToAirport(hotelDistances.getDistanceElements().get(i).getDuration().getValue());
        }
        Collections.sort((hotels), new Comparator<Hotels>() {
            @Override
            public int compare(Hotels h1, Hotels h2) {
                return h1.getDurationToAirport().compareTo(h2.getDurationToAirport());
            }
        });
        return hotels.get(0);
    }

    public CarInfo getFastestCar(String origin, String pick_up, String drop_off) {
        CarResults carResults = fetchCarInfo.getLowerFlightDetails(origin, pick_up, drop_off);
        List<CarInfo> carInfo = carResults.getResults();
        System.out.println(carInfo.get(0).getLocation());
        List<Location> carDestinations = new ArrayList<Location>();
        for (CarInfo cars : carInfo) {
            carDestinations.add(cars.getLocation());
        }
        DistanceRows carDistances = getDistanceInfo(origin, carDestinations);

        for (int i = 0; i < carDestinations.size(); i++) {
            carInfo.get(i).setDurationToAirport(carDistances.getDistanceElements().get(i).getDuration().getValue());
        }
        Collections.sort((carInfo), new Comparator<CarInfo>() {
            @Override
            public int compare(CarInfo c1, CarInfo c2) {
                return c1.getDurationToAirport().compareTo(c2.getDurationToAirport());
            }
        });
        return carInfo.get(0);

    }

    private DistanceRows getDistanceInfo(String origin, List<Location> destination) {
        GeoLocation airportlocation = fetchMapInfo.getlatlongDetails(origin);
        return fetchMapInfo.getDistanceMatrix(airportlocation, destination);
    }
}
