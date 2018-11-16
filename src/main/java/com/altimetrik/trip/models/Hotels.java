package com.altimetrik.trip.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotels {
    @JsonProperty("property_code")
    private String propertyCode;
    @JsonProperty("property_name")
    private String propertyName;

    private Location location;
    @JsonProperty("total_price")
    private TotalPrice totalPrice;

    private String durationToAirport;


    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public TotalPrice getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDurationToAirport() {
        return durationToAirport;
    }

    public void setDurationToAirport(String durationToAirport) {
        this.durationToAirport = durationToAirport;
    }

    @Override
    public String toString() {
        return "Hotels [propertyCode=" + propertyCode + ", propertyName=" + propertyName + ", location=" + location
                + ", totalPrice=" + totalPrice + ", durationToAirport=" + durationToAirport + "]";
    }


}
