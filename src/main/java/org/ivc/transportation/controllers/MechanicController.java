package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Mechanic;
import org.ivc.transportation.services.MechanicService;
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
 * @author Sokolov Slava
 */
@RestController
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;

    @GetMapping("/dispatcher/mechanics")
    public List<Mechanic> getMechanics(Principal principal) {
        return mechanicService.findMechanicsByTransportDep(principal);
    }

    @PostMapping("/dispatcher/mechanic_create")
    public Mechanic createMechanic(@RequestBody Mechanic mechanic) {
        return mechanicService.saveMechanic(mechanic);
    }

    @PutMapping("/dispatcher/mechanic_update")
    public Mechanic updateMechanic(@RequestBody Mechanic mechanic) {
        return mechanicService.saveMechanic(mechanic);
    }

    @DeleteMapping("/dispatcher/mechanic_delete")
    public ResponseEntity<String> deleteMechanic(@RequestBody Mechanic mechanic) {
        mechanicService.deleteMechanic(mechanic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
