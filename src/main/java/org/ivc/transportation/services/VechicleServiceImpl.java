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
import org.springframework.data.jpa.domain.Specification;
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
    private VechicleRepository vechicleRep;

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
    public Collection<Vechicle> getVechiclesByTransportDepId(Long id) {
        return vechicleRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public Optional<Vechicle> getVechicleById(Long id) {
        return vechicleRep.findById(id);
    }

    @Override
    public Collection<Vechicle> findByVacant(Boolean d) {
        return vechicleRep.findByVacant(d);
    }
}
