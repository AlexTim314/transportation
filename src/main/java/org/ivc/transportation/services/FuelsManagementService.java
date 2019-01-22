/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ivc.transportation.entities.Fuel;
import org.ivc.transportation.repositories.FuelRepository;

/**
 *
 * @author user
 */
@Service
@Transactional
public class FuelsManagementService {

    @Autowired
    private FuelRepository fuelRepository;

    public List<Fuel> findAllFuels() {
        return fuelRepository.findAll();
    }

    public Fuel saveFuel(Fuel fuel) {
        return fuelRepository.save(fuel);
    }

    public void deleteFuel(Fuel fuel) {
        fuelRepository.delete(fuel);
    }
}
