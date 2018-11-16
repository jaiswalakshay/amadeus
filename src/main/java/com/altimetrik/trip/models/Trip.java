package com.altimetrik.trip.models;

public class Trip {

    private Results result;

    private Hotels hotels;

    private CarInfo carInfo;

    public Results getResult() {
        return result;
    }

    public void setResult(Results result) {
        this.result = result;
    }

    public Hotels getHotels() {
        return hotels;
    }

    public void setHotels(Hotels hotels) {
        this.hotels = hotels;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public String toString() {
        return "CheapestTrip [result=" + result + ", hotels=" + hotels + ", carInfo=" + carInfo + "]";
    }


}
