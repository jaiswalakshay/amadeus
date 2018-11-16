package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceResults {

    @JsonProperty("rows")
    List<DistanceRows> distanceRows;

    public List<DistanceRows> getDistanceRows() {
        return distanceRows;
    }

    public void setDistanceRows(List<DistanceRows> distanceRows) {
        this.distanceRows = distanceRows;
    }

    @Override
    public String toString() {
        return "DistanceResults [distanceRows=" + distanceRows + "]";
    }


}
