package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.services.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class DispatcherController {

    @Autowired
    private DispatcherService dispatcherService;

    @GetMapping("/dispatcher/drivers")
    public List<Driver> getDriversByDispatcher(Principal principal) {
        return dispatcherService.getDrivers(principal);
    }

    @GetMapping("/dispatcher/vehicles")
    public List<Vehicle> getVehiclesByDispatcher(Principal principal) {
        return dispatcherService.getVehicles(principal);
    }

    @PostMapping("/dispatcher/save_driver")
    public Driver saveDriver(Principal principal, @RequestBody Driver driver) {
        return dispatcherService.saveDriver(principal, driver);
    }

    @PostMapping("/dispatcher/save_vehicle")
    public Vehicle saveVehicle(Principal principal, @RequestBody Vehicle vehicle) {
        return dispatcherService.saveVehicle(principal, vehicle);
    }

    @DeleteMapping("/dispatcher/delete_driver")
    public ResponseEntity<String> deleteDriver(Principal principal, @RequestBody Driver driver) {
        dispatcherService.deleteDriver(principal, driver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/dispatcher/delete_vehicle")
    public ResponseEntity<String> deleteVehicle(Principal principal, @RequestBody Vehicle vehicle) {
        dispatcherService.deleteVehicle(principal, vehicle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
