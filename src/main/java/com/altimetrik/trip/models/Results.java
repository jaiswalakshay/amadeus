package com.altimetrik.trip.models;

import java.util.List;

public class Results {
    private List<Itineraries> itineraries;
    private Fare fare;

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public List<Itineraries> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itineraries> itineraries) {
        this.itineraries = itineraries;
    }

    @Override
    public String toString() {
        return "Results [itineraries=" + itineraries + ", fare=" + fare + "]";
    }
}
