package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Locationvalues {
    LocationGeography geometry;

    public LocationGeography getGeometry() {
        return geometry;
    }

    public void setGeometry(LocationGeography geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "Locationvalues [geometry=" + geometry + "]";
    }


}
