/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.List;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
@Transactional
public class PlaceService {
    
    @Autowired
    private PlaceRepository placeRepository;
    
    public List<Place> findAllPlaces() {
        return placeRepository.findAll();
    }
    
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }
    
    public void deletePlace(Place place) {
        placeRepository.delete(place);
    }
    
}
