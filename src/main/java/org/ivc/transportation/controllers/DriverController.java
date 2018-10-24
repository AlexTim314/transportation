/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nesterov Yuriy
 */
@RestController
public class DriverController {
    
    private static final String url = "/drivers";

    @Autowired
    private DriverService localServ;

    @GetMapping(url)
    public Collection<Driver> getDrivers() {
        return localServ.getDrivers();
    }

    @GetMapping(url + "/{id}")
    public Driver getDriver(@PathVariable Long id) {
        Optional<Driver> dep = localServ.getDriverById(id);
        return dep.get();
    }

    @DeleteMapping(url + "/{id}")
    public void delDriver(@PathVariable Long id) {
        localServ.removeDriver(id);
    }

    @PostMapping(url)
    public void addDriver(@RequestBody Driver department) {
        localServ.addDriver(department);
    }

    @PutMapping(url + "/{id}")
    public void updateDriver(@RequestBody Driver dep, @PathVariable Long id) {
        localServ.updateDriver(dep, id);
    }
}
