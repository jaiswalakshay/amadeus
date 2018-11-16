package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceElements {
    DistanceDuration duration;

    public DistanceDuration getDuration() {
        return duration;
    }

    public void setDuration(DistanceDuration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DistanceResources [duration=" + duration + "]";
    }


}
