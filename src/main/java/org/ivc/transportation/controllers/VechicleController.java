/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.services.VechicleService;
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
 * @author first
 */
@RestController
public class VechicleController {
    
    private static final String url = "/vechicles";

    @Autowired
    private VechicleService localServ;

    @GetMapping(url)
    public Collection<Vechicle> getVechicles() {
        return localServ.listVechicles();
    }

    @GetMapping(url + "/{id}")
    public Vechicle getVechicle(@PathVariable long id) {
        Optional<Vechicle> dep = localServ.getVechicleById(id);
        return dep.get();
    }

    @DeleteMapping(url + "/{id}")
    public void delVechicle(@PathVariable long id) {
        localServ.removeVechicle(id);
    }

    @PostMapping(url)
    public void addVechicle(@RequestBody Vechicle department) {
        localServ.addVechicle(department);
    }

    @PutMapping(url + "/{id}")
    public void updateVechicle(@RequestBody Vechicle dep, @PathVariable long id) {
        localServ.updateVechicle(dep, id);
    }
    
}
