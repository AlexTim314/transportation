package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.services.DispatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController("/dispatcher")
public class DispatcherController {

    @Autowired
    private DispatcherService dispatcherService;

    @GetMapping("/drivers")
    public List<Driver> getDriversByDispatcher(Principal principal) {
        return dispatcherService.getDrivers(principal);
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getVehiclesByDispatcher(Principal principal) {
        return dispatcherService.getVehicles(principal);
    }

}
