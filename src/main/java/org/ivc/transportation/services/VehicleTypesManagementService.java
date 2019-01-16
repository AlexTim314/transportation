/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.repositories.VehicleTypeRepository;
import org.ivc.transportation.repositories.VehicleModelRepository;

/**
 *
 * @author user
 */
@Service
@Transactional
public class VehicleTypesManagementService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    public List<VehicleType> findAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    public VehicleType saveVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public void deleteVehicleType(VehicleType vehicleType) {
        vehicleTypeRepository.delete(vehicleType);
    }

    public List<VehicleModel> findAllVehicleModels() {
        return vehicleModelRepository.findAll();
    }
    
    public List<VehicleModel> findVehicleModelsByVehicleType(VehicleType vehicleType) {
        return vehicleModelRepository.findByVehicleTypeId(vehicleType.getId());
    }

    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) {
        return vehicleModelRepository.save(vehicleModel);
    }

    public void deleteVehicleModel(VehicleModel vehicleModel) {
        vehicleModelRepository.delete(vehicleModel);
    }

}
