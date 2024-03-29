package com.arianit.citybe.controller;

import com.arianit.citybe.dto.TripDto;
import com.arianit.citybe.dto.TripReq;
import com.arianit.citybe.entity.TypeOfGastronome;
import com.arianit.citybe.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }
    @PostMapping("/createTripWithReqBody")
    public ResponseEntity<TripDto> createAdvanceTrip(@RequestBody TripReq tripReq) {
        TripDto citiesWithGastronomies = tripService.createAdvanceTrip(tripReq.getCityIds(), tripReq.getTypeOfGastronomes());
        return new ResponseEntity<>(citiesWithGastronomies, HttpStatus.OK);
    }
    @PostMapping("/createTripWithReqBody2")
    public ResponseEntity<TripDto> createAdvanceTrip2(@RequestBody TripReq tripReq) {
        TripDto citiesWithGastronomies = tripService.createAdvanceTrip(tripReq.getCityIds(), tripReq.getTypeOfGastronomes());
        return new ResponseEntity<>(citiesWithGastronomies, HttpStatus.OK);
    }

    @GetMapping("/createTripWithParams")
    public ResponseEntity<TripDto> createAdvanceTrip(
            @RequestParam List<Long> cityIds,
            @RequestParam List<TypeOfGastronome> type) {
        TripDto citiesWithGastronomies = tripService.createAdvanceTrip(cityIds, type);
        return new ResponseEntity<>(citiesWithGastronomies, HttpStatus.OK);
    }

    @GetMapping("/getTrips")
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<TripDto> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/getTrip/{tripId}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long tripId) {
        TripDto trip = tripService.getTripById(tripId);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }


    @DeleteMapping("/deleteTrip/{tripId}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return new ResponseEntity<>("Trip deleted successfully", HttpStatus.OK);
    }
}