package org.ivc.transportation.services;

import java.util.Collection;
import java.sql.Date;
import java.util.Optional;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.DateRange;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TypeVechicle;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.TypeVechicleRepository;
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
    private RecordRepository recordRep;
    @Autowired
    private TypeVechicleRepository typeVechicleRep;

    @Override
    @Transactional
    public void addClaim(Claim d) {
        this.claimRep.save(d);
    }

    @Override
    @Transactional
    public void updateClaim(Claim d, Long id) {
        d.setId(id);
        claimRep.save(d);
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
        recordRep.deleteByClaimId(id);
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
    public Collection<Claim> getAllClaimsByDate(DateRange dr) {
        return claimRep.findAllByClDateBetweenOrderByClDateDesc(dr.StartDate, dr.EndDate);
    }

    @Override
    @Transactional
    public Collection<Claim> getAllClaimsByDepartmentAndDate(Long id, DateRange dr) {
        return claimRep.findAllByDepartmentIdAndClDateBetweenOrderByClDateDesc(id, dr.StartDate, dr.EndDate);
    }

    @Override
    @Transactional
    public Collection<Claim> getClaimsByDepartmentAndTip(Long id, ClaimType t) {
        return claimRep.findByTipAndDepartmentIdOrderByClDateDesc(id, t);
    }

    @Override
    @Transactional
    public void addRecord(Record d) {
        this.recordRep.save(d);
    }

    @Override
    @Transactional
    public void updateRecord(Record d, Long id) {
        d.setId(id);
        recordRep.save(d);
    }

    @Override
    @Transactional
    public void removeRecord(Long id) {
        recordRep.deleteById(id);
    }

    @Override
    @Transactional
    public Optional getRecordsById(Long id) {
        return recordRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Record> getRecords() {
        return recordRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByClaim(Long id) {
        return recordRep.findByClaimId(id);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByState(RecordStatus t) {
        return recordRep.findByStatus(t);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByDate(Date d) {
        return recordRep.findByDatetime(d);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByHash(String d) {
        return recordRep.findByWeekHash(d);
    }

    @Override
    public Collection<TypeVechicle> getTypeVechicles() {
        return typeVechicleRep.findAll();
    }

    @Override
    public Collection<TypeVechicle> getTypeVechiclesBySpicialization(String s) {
        return typeVechicleRep.findBySpecialization(s);
    }

}
