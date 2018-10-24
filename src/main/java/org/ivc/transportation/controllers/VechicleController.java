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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author first
 */
@RestController
@RequestMapping("/vechicles")
public class VechicleController {
    
    @Autowired
    private VechicleService localServ;

    @GetMapping()
    public Collection<Vechicle> getVechicles() {
        return localServ.getVechicles();
    }

    @GetMapping("/{id}")
    public Vechicle getVechicle(@PathVariable Long id) {
        Optional<Vechicle> dep = localServ.getVechicleById(id);
        return dep.get();
    }

    @DeleteMapping("/{id}")
    public void delVechicle(@PathVariable Long id) {
        localServ.removeVechicle(id);
    }

    @PostMapping()
    public void addVechicle(@RequestBody Vechicle department) {
        localServ.addVechicle(department);
    }

    @PutMapping("/{id}")
    public void updateVechicle(@RequestBody Vechicle dep, @PathVariable Long id) {
        localServ.updateVechicle(dep, id);
    }
    
}
