/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nesterov Yuriy
 */
@RestController
public class DriverController {

    @Autowired
    private DriverRepository repository;

    @GetMapping("/drivers")
    public Collection<Driver> getDrivers() {
        return repository.findAll();

    }
    
    @GetMapping("/drivers/{id}")
    public Collection<Driver> getDepDrivers(@PathVariable("id") Long id) {
        List<Driver> drivers = repository.findByTransportDepId(id);
        return drivers;

    }
}
