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
    private TransportDepRepository localRep;
    @Autowired
    private DriverRepository driverRep;
    @Autowired
    private VechicleRepository vechicleRep;
    
    @Override
    @Transactional
    public void addTransportDep(TransportDep d) {
        this.localRep.save(d);
    }
    
    @Override
    @Transactional
    public void updateTransportDep(TransportDep d, long id) {
        d.setId(id);
        localRep.save(d);
    }       
    
    @Override
    @Transactional
    public Collection<TransportDep> listTransportDeps() {
        return localRep.findAll();
    }
    
    @Override
    @Transactional
    public Optional<TransportDep> getTransportDepById(long id) {
        return localRep.findById(id);
    }
    
    @Override
    @Transactional
    public void removeTransportDep(long id) {
        localRep.deleteById(id);
    }
    
    @Override
    @Transactional
    public Collection<Driver> listDrivers(long id) {
        return driverRep.findByTransportDepId(id);
    }
    
    @Override
    @Transactional
    public Collection<Vechicle> listVechicles(long Id) {
        return vechicleRep.findByTransportDepId(Id);
    }

}
