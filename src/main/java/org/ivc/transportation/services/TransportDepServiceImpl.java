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
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
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
    private TransportDepRepository tdRep;
    @Autowired
    private DriverRepository drRep;
    @Autowired
    private VechicleRepository vclRep;

    @Override
    @Transactional
    public void addTransportDep(TransportDep d) {
        this.tdRep.save(d);
    }

    @Override
    @Transactional
    public void updateTransportDep(TransportDep d, Long id) {
        d.setId(id);
        tdRep.save(d);
    }

    @Override
    @Transactional
    public void removeTransportDep(Long id) {
        tdRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<TransportDep> getTransportDeps() {
        return tdRep.findAll();
    }

    @Override
    @Transactional
    public Optional<TransportDep> getTransportDepById(Long id) {
        return tdRep.findById(id);
    }

    @Override
    @Transactional
    public void addDriver(Driver d) {
        this.drRep.save(d);
    }

    @Override
    @Transactional
    public void updateDriver(Driver d, Long id) {
        d.setId(id);
        drRep.save(d);
    }

    @Override
    @Transactional
    public void removeDriver(Long id) {
        drRep.deleteById(id);
    }

    @Override
    @Transactional
    public Optional getDriverById(Long id) {
        return drRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Driver> getDrivers() {
        return drRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Driver> findDriversByVacant(Boolean d) {
        return drRep.findByVacant(d);
    }

    @Override
    @Transactional
    public Collection<Driver> getDriversByTransportDepId(Long id) {
        return drRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public void addVechicle(Vechicle d) {
        this.vclRep.save(d);
    }

    @Override
    @Transactional
    public void updateVechicle(Vechicle d, Long id) {
        d.setId(id);
        vclRep.save(d);
    }

    @Override
    @Transactional
    public void removeVechicle(Long id) {
        vclRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<Vechicle> getVechicles() {
        return vclRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Vechicle> findVechiclesByVacant(Boolean d) {
        return vclRep.findByVacant(d);
    }

    @Override
    @Transactional
    public Collection<Vechicle> getVechiclesByTransportDepId(Long id) {
        return vclRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public Optional getVechicleById(Long id) {
        return vclRep.findById(id);
    }
}
