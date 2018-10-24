/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.TransportDepRepository;
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
}
