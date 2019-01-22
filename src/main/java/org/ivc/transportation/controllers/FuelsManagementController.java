/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.List;
import org.ivc.transportation.entities.Fuel;
import org.ivc.transportation.services.FuelsManagementService;
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
public class FuelsManagementController {

    @Autowired
    private FuelsManagementService fuelsManagementService;

    @GetMapping("/management/fuels")
    public List<Fuel> getAllFuels() {
        return fuelsManagementService.findAllFuels();
    }

    @PostMapping("/management/fuel_create")
    public Fuel createFuel(@RequestBody Fuel fuel) {
        return fuelsManagementService.saveFuel(fuel);
    }

    @PutMapping("/management/fuel_update")
    public Fuel updateFuel(@RequestBody Fuel fuel) {
        return fuelsManagementService.saveFuel(fuel);
    }

    @DeleteMapping("/management/fuel_delete")
    public ResponseEntity<String> deleteFuel(@RequestBody Fuel fuel) {
        fuelsManagementService.deleteFuel(fuel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
