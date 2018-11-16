package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarResults {

    private List<CarInfo> results;

    public List<CarInfo> getResults() {
        return results;
    }

    public void setResults(List<CarInfo> results) {
        this.results = results;
    }


}
