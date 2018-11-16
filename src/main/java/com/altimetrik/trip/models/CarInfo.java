package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarInfo {
    private Location location;
    private String airport;
    @JsonProperty("branch_id")
    private String branchId;
    private List<Cars> cars;

    private String durationToAirport;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    public String getDurationToAirport() {
        return durationToAirport;
    }

    public void setDurationToAirport(String durationToAirport) {
        this.durationToAirport = durationToAirport;
    }
}
