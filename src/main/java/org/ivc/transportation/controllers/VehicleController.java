package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Refueling;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleInfo;
import org.ivc.transportation.services.VehicleService;
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
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/dispatcher/vehicles")
    public List<Vehicle> getVehicles(Principal principal) {
        return vehicleService.findVehiclesByTransportDep(principal);
    }

    @PostMapping("/dispatcher/vehicle_create")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PutMapping("/dispatcher/vehicle_update")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PostMapping("/dispatcher/vehicle_updateState")
    public VehicleInfo updateVehicleStatus(@RequestBody VehicleInfo vehicleInfo) {
        return vehicleService.updateVehicleStatus(vehicleInfo);
    }

    @PostMapping("/dispatcher/vehicle_refueling")
    public Refueling vehicleRefueling(@RequestBody Refueling refueling) {
        return vehicleService.vehicleRefueling(refueling);
    }

    @DeleteMapping("/dispatcher/vehicles_delete")
    public ResponseEntity<String> deleteVehicle(@RequestBody List<Long> ids) {
        vehicleService.deleteVehicles(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
