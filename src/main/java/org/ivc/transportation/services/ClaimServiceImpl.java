/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.sql.Date;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {
    
    @Autowired
    private ClaimRepository localRep;
    
    @Override
    @Transactional
    public void addClaim(Claim d) {
        this.localRep.save(d);
    }
    
    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepartment(Long id) {
        return localRep.findByDepartmentId(id);
        
    }
    
    @Override
    @Transactional
    public Collection<Claim> getClaimsByclDate(Date d) {
        return localRep.findByclDate(d);
        
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByaffirmation(Boolean b) {
        return localRep.findByaffrimation(b);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsBytip(byte t) {
        return localRep.findBytip(t);
    }
        
    @Override
    @Transactional
    public void removeClaim(Long id) {
        localRep.deleteById(id);
    }

}