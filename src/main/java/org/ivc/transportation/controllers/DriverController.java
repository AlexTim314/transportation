package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Refueling;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.DriverInfo;
import org.ivc.transportation.services.DriverService;
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
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/dispatcher/drivers")
    public List<Driver> getDrivers(Principal principal) {
        return driverService.findDriversByTransportDep(principal);
    }

    @PostMapping("/dispatcher/driver_create")
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    @PutMapping("/dispatcher/driver_update")
    public Driver updateDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    @PostMapping("/dispatcher/driver_updateState")
    public DriverInfo updateDriverStatus(@RequestBody DriverInfo driverInfo) {
        return driverService.updateDriverStatus(driverInfo);
    }

    @DeleteMapping("/dispatcher/drivers_delete")
    public ResponseEntity<String> deleteDriver(@RequestBody List<Long> ids) {
        driverService.deleteDrivers(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
