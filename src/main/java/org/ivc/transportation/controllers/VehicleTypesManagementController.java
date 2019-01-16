/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.List;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.services.VehicleTypesManagementService;
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
 * @author user
 */
@RestController
public class VehicleTypesManagementController {

    @Autowired
    private VehicleTypesManagementService vehicleTypesManagementService;

    @GetMapping("/management/vehicleTypes")
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypesManagementService.getAllVehicleTypes();
    }

    @PostMapping("/management/vehicleType_create")
    public VehicleType createVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypesManagementService.saveVehicleType(vehicleType);
    }

    @PutMapping("/management/vehicleType_update")
    public VehicleType updateVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypesManagementService.saveVehicleType(vehicleType);
    }

    @DeleteMapping("/management/vehicleType_delete")
    public ResponseEntity<String> deleteVehicleType(@RequestBody VehicleType vehicleType) {
        vehicleTypesManagementService.deleteVehicleType(vehicleType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/management/vehicleModels")
    public List<VehicleModel> getAllVehicleModels() {
        return vehicleTypesManagementService.getAllVehicleModels();
    }

    @GetMapping("/management/vehicleModelsByVehicleType")
    public List<VehicleModel> getVehicleModelsByVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypesManagementService.getVehicleModelsByVehicleType(vehicleType);
    }

    @PostMapping("/management/vehicleModel_create")
    public VehicleModel createVehicleModel(@RequestBody VehicleModel vehicleModel) {
        return vehicleTypesManagementService.saveVehicleModel(vehicleModel);
    }

    @PutMapping("/management/vehicleModel_update")
    public VehicleModel updateVehicleModel(@RequestBody VehicleModel vehicleModel) {
        return vehicleTypesManagementService.saveVehicleModel(vehicleModel);
    }

    @DeleteMapping("/management/vehicleModel_delete")
    public ResponseEntity<String> deleteVehicleModel(@RequestBody VehicleModel vehicleModel) {
        vehicleTypesManagementService.deleteVehicleModel(vehicleModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
