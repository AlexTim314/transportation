/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ivc.transportation.repositories.VehicleRepository;
import org.ivc.transportation.repositories.VehicleTypeRepository;

/**
 *
 * @author user
 */
@Service
@Transactional
public class TransportDepServiceImpl implements TransportDepService {

    @Autowired
    private TransportDepRepository transportDepRep;
    @Autowired
    private DriverRepository driverRep;
    @Autowired
    private VehicleRepository vechicleRep;
    @Autowired
    private VehicleTypeRepository typeVechicleRep;

    @Override
    @Transactional
    public void addTransportDep(TransportDep d) {
        this.transportDepRep.save(d);
    }

    @Override
    @Transactional
    public void updateTransportDep(TransportDep d, Long id) {
        d.setId(id);
        transportDepRep.save(d);
    }

    @Override
    @Transactional
    public void removeTransportDep(Long id) {
        transportDepRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<TransportDep> getTransportDeps() {
        return transportDepRep.findAll();
    }

    @Override
    @Transactional
    public Optional<TransportDep> getTransportDepById(Long id) {
        return transportDepRep.findById(id);
    }

    @Override
    @Transactional
    public void addDriver(Driver d) {
        this.driverRep.save(d);
    }

    @Override
    @Transactional
    public void updateDriver(Driver d, Long id) {
        d.setId(id);
        driverRep.save(d);
    }

    @Override
    @Transactional
    public void removeDriver(Long id) {
        driverRep.deleteById(id);
    }

    @Override
    @Transactional
    public Optional getDriverById(Long id) {
        return driverRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Driver> getDrivers() {
        return driverRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Driver> findDriversByVacant(Boolean d) {
        return driverRep.findByVacant(d);
    }

    @Override
    @Transactional
    public Collection<Driver> getDriversByTransportDepId(Long id) {
        return driverRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public void addVechicle(Vehicle d) {
        this.vechicleRep.save(d);
    }

    @Override
    @Transactional
    public void updateVechicle(Vehicle d, Long id) {
        d.setId(id);
        vechicleRep.save(d);
    }

    @Override
    @Transactional
    public void removeVechicle(Long id) {
        vechicleRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<Vehicle> getVechicles() {
        return vechicleRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Vehicle> findVechiclesByVacant(Boolean d) {
        return vechicleRep.findByVacant(d);
    }

    @Override
    @Transactional
    public Collection<Vehicle> getVechiclesByTransportDepId(Long id) {
        return vechicleRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public Optional getVechicleById(Long id) {
        return vechicleRep.findById(id);
    }

    @Override
    public void addTypeVechicle(VehicleType d) {
        this.typeVechicleRep.save(d);
    }
    
    @Override
    public void updateTypeVechicle(VehicleType d, Long id) {
         d.setId(id);
        typeVechicleRep.save(d);
    }

    @Override
    public void removeTypeVechicle(Long id) {
        typeVechicleRep.deleteById(id);
    }

    @Override
    public Collection<VehicleType> getTypeVechicles() {
        return typeVechicleRep.findAll();
    }

    @Override
    public Collection<Vehicle> getVechiclesByTypeVechicleId(Long id) {
        return vechicleRep.findByTypeVechicleId(id);
    }

    @Override
    public Collection<VehicleType> getTypeVechiclesBySpicialization(String s) {
       return typeVechicleRep.findBySpecialization(s);
    }

}
