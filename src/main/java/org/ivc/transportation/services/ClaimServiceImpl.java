/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.ivc.transportation.config.trUtils;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.RecordRepository;
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
    @Autowired
    private RecordRepository recRep;

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
    public Collection<Claim> getClaimsByTip(ClaimType t) {
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
    public Collection<Claim> getClaimsByTipAsc(ClaimType t) {
        return claimRep.findByTipOrderByClDateAsc(t);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepAndAffirmationAsc(Long id, Boolean a) {
        return claimRep.findByDepartmentIdAndAffirmationOrderByClDateAsc(id, a);
    }

    @Override
    @Transactional
    public Collection<Claim> getAllClaimsSortByDate() {
        return claimRep.findAll(Sort.by(Sort.Direction.DESC, "clDate"));
    }

    @Override
    @Transactional
    public Collection<Claim> getAllClaimsSortByDateAsk() {
        return claimRep.findAll(Sort.by(Sort.Direction.ASC, "clDate"));
    }

    @Override
    @Transactional
    public void addRecord(Record d) {
        this.recRep.save(d);
    }

    @Override
    @Transactional
    public void updateRecord(Record d, Long id) {
        d.setId(id);
        recRep.save(d);
        updateClaim(d.getClaim().getId());
    }

    @Override
    @Transactional
    public void removeRecord(Long id) {
        recRep.deleteById(id);
    }

    @Override
    @Transactional
    public Optional getRecordsById(Long id) {
        return recRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Record> getRecords() {
       return recRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByClaim(Long id) {
        return recRep.findByClaimId(id);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByState(RecordStatus t) {
        return recRep.findByStatus(t);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByDate(Date d) {
       return recRep.findByDatetime(d);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByHash(String d) {
        return recRep.findByWeekHash(d);
    }
    
    public void updateClaim(Long id){
        List<Record> recList;
        recList = recRep.findByClaimId(id);
        
    
    }

}
