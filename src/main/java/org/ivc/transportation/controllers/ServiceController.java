package org.ivc.transportation.controllers;

import java.util.List;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.repositories.VehicleModelRepository;
import org.ivc.transportation.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class ServiceController {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @GetMapping("/vehicleTypes")
    public List<VehicleType> findAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    @GetMapping("/vehicleModels")
    public List<VehicleModel> findAllVehicleModels() {
        return vehicleModelRepository.findAll();
    }

}
