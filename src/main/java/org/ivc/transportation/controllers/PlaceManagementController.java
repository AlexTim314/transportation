/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.List;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */

@RestController
public class PlaceManagementController {
    
    @Autowired
    private PlaceService placeService;
    
    @GetMapping("/management/places")
    public List<Place> getAllPlaces() {
        return placeService.findAllPlaces();
    }

    @PostMapping("/management/place_create")
    public Place createPlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @PutMapping("/management/place_update")
    public Place updatePlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @DeleteMapping("/management/place_delete")
    public ResponseEntity<String> deletePlace(@RequestBody Place place) {
        placeService.deletePlace(place);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
