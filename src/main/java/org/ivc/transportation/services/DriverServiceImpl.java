/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author first
 */
@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRep;

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
    public Collection<Driver> getDrivers() {
        return driverRep.findAll();
    }

    @Override
    @Transactional
    public Optional<Driver> getDriverById(Long id) {
        return driverRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Driver> getDriversByTransportDepId(Long id) {
        return driverRep.findByTransportDepId(id);
    }

    @Override
    public Collection<Driver> findByVacant(Boolean d) {
        return driverRep.findByVacant(d);
    }

}
