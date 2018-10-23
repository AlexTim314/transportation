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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Degtyarev Fedor & Nesterov Yuriy
 */
@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRep;

    @Override
    @Transactional
    public void addClaim(Claim d) {
        this.claimRep.save(d);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepartment(Long id) {
        return claimRep.findByDepartmentId(id);

    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByclDate(Date d) {
        return claimRep.findByClDate(d);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByAffirmation(Boolean b) {
        return claimRep.findByAffirmationOrderByClDateDesc(b);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByTip(byte t) {
        return claimRep.findByTipOrderByClDateDesc(t);
    }

    @Override
    @Transactional
    public void removeClaim(Long id) {
        claimRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepAndAffirmation(Long id, Boolean a) {
        return claimRep.findByDepartmentIdAndAffirmationOrderByClDateDesc(id, a);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByAffirmationAsc(Boolean b) {
        return claimRep.findByAffirmationOrderByClDateAsc(b);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByTipAsc(byte t) {
        return claimRep.findByTipOrderByClDateAsc(t);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepAndAffirmationAsc(Long id, Boolean a) {
        return claimRep.findByDepartmentIdAndAffirmationOrderByClDateAsc(id, a);
    }

    @Override
    public Collection<Claim> getAllClaimsSortByDate() {
        return claimRep.findAll(Sort.by(Sort.Direction.DESC, "clDate"));
    }

    @Override
    public Collection<Claim> getAllClaimsSortByDateAsk() {
        return claimRep.findAll(Sort.by(Sort.Direction.ASC, "clDate"));
    }

}
