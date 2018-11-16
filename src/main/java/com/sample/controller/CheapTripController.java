package com.sample.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.sample.services.CheapTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
@RequestMapping("/amadeus")
public class CheapTripController {


    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CheapTripService cheapTripService;

    @RequestMapping(value = "/searchCheapTrips", method = RequestMethod.GET)
    public ResponseEntity<List<JsonNode>> foodsearch(@RequestParam String originCity,
                                               @RequestParam String destCity,
                                               @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd")Date startDate,
                                               @RequestParam Integer noOfDays,
                                               @RequestParam String comfortOpts) {
        List<JsonNode> result= cheapTripService.getTrips(originCity,destCity,startDate,noOfDays,comfortOpts);
        if(result!=null){
            return new ResponseEntity<>(result,HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


}
