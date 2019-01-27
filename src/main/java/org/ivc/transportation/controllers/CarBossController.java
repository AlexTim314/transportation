package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.services.CarBossService;
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
public class CarBossController {

    @Autowired
    private CarBossService carBossService;

    @GetMapping("/user/carBosses")
    public List<CarBoss> getCarBosses(Principal principal) {
        return carBossService.findCarBossesByDepartment(principal);
    }

    @PostMapping("/user/carBoss_create")
    public CarBoss createCarBoss(Principal principal, @RequestBody CarBoss carBoss) {
        return carBossService.saveCarBoss(principal, carBoss);
    }

    @PutMapping("/user/carBoss_update")
    public CarBoss updateCarBoss(Principal principal, @RequestBody CarBoss carBoss) {
        return carBossService.saveCarBoss(principal, carBoss);
    }

    @DeleteMapping("/user/carBoss_delete")
    public ResponseEntity<String> deleteCarBoss(@RequestBody CarBoss carBoss) {
        carBossService.deleteCarBoss(carBoss);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
