/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.Collection;
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.repositories.VechicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nesterov Yuriy
 */
@RestController
public class VechicleController {

    @Autowired
    private VechicleRepository repository;

    @GetMapping("/vechicle")
    public Collection<Vechicle> getVehicles() {
        return repository.findAll();

    }

}
