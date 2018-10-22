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
    private DriverRepository localRep;

    @Override
    @Transactional
    public void addDriver(Driver d) {
        this.localRep.save(d);
    }

    @Override
    @Transactional
    public void updateDriver(Driver d, Long id) {
        d.setId(id);
        localRep.save(d);
    }

    @Override
    @Transactional
    public Collection<Driver> listDrivers() {
        return localRep.findAll();
    }

    @Override
    @Transactional
    public Optional<Driver> getDriverById(long id) {
        return localRep.findById(id);
    }

    @Override
    @Transactional
    public void removeDriver(long id) {
        localRep.deleteById(id);
    }

}
