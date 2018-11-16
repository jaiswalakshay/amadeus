package com.altimetrik.trip.client;

import com.altimetrik.trip.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class FetchMapInfo {

    @Value("${googleapis.api.key}")
    String apikey;

    RestTemplate restTemplate = new RestTemplate();

    private String getDistanceApi() {
        return "https://maps.googleapis.com/maps/api/distancematrix/json?key=" + apikey;
    }

    private String getLocationApi() {
        return "https://maps.googleapis.com/maps/api/geocode/json?key=" + apikey;
    }

    public GeoLocation getlatlongDetails(String destination) {
        String url = this.getLocationApi() + "&address=" + destination + "+Airport";
        System.out.println("latituteLongitude" + url);
        ResponseEntity<LocationResult> output = restTemplate.getForEntity(url, LocationResult.class);
        //System.out.println(output.getBody());
        return output.getBody().getLocationvalues().get(0).getGeometry().getLocation();
    }

    public DistanceRows getDistanceMatrix(GeoLocation location1, List<Location> point2) {
        StringBuilder url = new StringBuilder();
        url = url.append(this.getDistanceApi()).append("&origins=").append(location1.getLatitude()).append(",").append(location1.getLongitude()).append("&destinations=");
        for (Location l : point2) {
            url.append(l.getLatitude()).append(",").append(l.getLongitude()).append("|");
        }
        url.deleteCharAt(url.length() - 1);
        String u1 = url.toString();
        //System.out.println("distancematrix: "+u1);
        //String url = distanceApi+ "&origins="+point1.getLatitude()+","+point1.getLongitude()+"&destinations="+point2.getLatitude()+","+point2.getLongitude();
        ResponseEntity<DistanceResults> output = restTemplate.getForEntity(u1, DistanceResults.class);

        return output.getBody().getDistanceRows().get(0);
    }

}