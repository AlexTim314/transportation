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
import org.ivc.transportation.entities.TypeVechicle;
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.ivc.transportation.repositories.TypeVechicleRepository;
import org.ivc.transportation.repositories.VechicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private VechicleRepository vechicleRep;
    @Autowired
    private TypeVechicleRepository typeVechicleRep;

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
    public void addVechicle(Vechicle d) {
        this.vechicleRep.save(d);
    }

    @Override
    @Transactional
    public void updateVechicle(Vechicle d, Long id) {
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
    public Collection<Vechicle> getVechicles() {
        return vechicleRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Vechicle> findVechiclesByVacant(Boolean d) {
        return vechicleRep.findByVacant(d);
    }

    @Override
    @Transactional
    public Collection<Vechicle> getVechiclesByTransportDepId(Long id) {
        return vechicleRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public Optional getVechicleById(Long id) {
        return vechicleRep.findById(id);
    }

    @Override
    public void addTypeVechicle(TypeVechicle d) {
        this.typeVechicleRep.save(d);
    }
    
    @Override
    public void updateTypeVechicle(TypeVechicle d, Long id) {
         d.setId(id);
        typeVechicleRep.save(d);
    }

    @Override
    public void removeTypeVechicle(Long id) {
        typeVechicleRep.deleteById(id);
    }

    @Override
    public Collection<TypeVechicle> getTypeVechicles() {
        return typeVechicleRep.findAll();
    }

    @Override
    public Collection<Vechicle> getVechiclesByTypeVechicleId(Long id) {
        return vechicleRep.findByTypeVechicleId(id);
    }

    @Override
    public Collection<TypeVechicle> getTypeVechiclesBySpicialization(String s) {
       return typeVechicleRep.findBySpecialization(s);
    }

}
