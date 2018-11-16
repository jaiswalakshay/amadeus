package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceRows {
    @JsonProperty("elements")
    List<DistanceElements> distanceElements;

    public List<DistanceElements> getDistanceElements() {
        return distanceElements;
    }

    public void setDistanceElements(List<DistanceElements> distanceElements) {
        this.distanceElements = distanceElements;
    }

    @Override
    public String toString() {
        return "DistanceRows [distanceElements=" + distanceElements + "]";
    }


}
