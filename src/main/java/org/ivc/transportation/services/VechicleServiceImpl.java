/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.repositories.VechicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nesterov Yuriy
 */
@Service
@Transactional
public class VechicleServiceImpl implements VechicleService{
    
    @Autowired
    private VechicleRepository localRep;

    @Override
    @Transactional
    public void addVechicle(Vechicle d) {
        this.localRep.save(d);
    }

    @Override
    @Transactional
    public void updateVechicle(Vechicle d, Long id) {
        d.setId(id);
        localRep.save(d);
    }

    @Override
    @Transactional
    public void removeVechicle(Long id) {
        localRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<Vechicle> getVechicles() {
        return localRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Vechicle> getVechiclesByTransportDepId(Long id) {
        return localRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public Optional<Vechicle> getVechicleById(Long id) {
        return localRep.findById(id);
    }
}
