package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelResults {
    private List<Hotels> results;

    public List<Hotels> getResults() {
        return results;
    }

    public void setResults(List<Hotels> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "HotelResults [results=" + results + "]";
    }


}
