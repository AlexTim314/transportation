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
    private ClaimRepository claimRepository;
    
    @Override
    @Transactional
    public void addClaim(Claim d) {
        this.claimRepository.save(d);
    }
    
    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepartment(Long id) {
        return claimRepository.findByDepartmentId(id);
        
    }
    
    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepartmentAndAffirmation(Long id, Boolean a) {
        return claimRepository.findByDepartmentIdAndAffirmation(id ,a);
        
    }
    
    @Override
    @Transactional
    public Collection<Claim> getClaimsByClDate(Date d) {
        return claimRepository.findByClDate(d);
        
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByAffirmation(Boolean b) {
        return claimRepository.findByAffirmation(b);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByTip(byte t) {
        return claimRepository.findByTip(t);
    }
        
    @Override
    @Transactional
    public void removeClaim(Long id) {
        claimRepository.deleteById(id);
    }

}