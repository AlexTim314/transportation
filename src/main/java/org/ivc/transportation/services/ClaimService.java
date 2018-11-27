/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.sql.Date;
import java.util.Optional;
import org.ivc.transportation.config.trUtils.AppointmentStatus;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.DateRange;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;

/**
 *
 * @author Degtyarev Fedor & Nesterov Yuriy
 */
public interface ClaimService {

    public void addClaim(Claim d);
    
    public void updateClaim(Claim d, Long id);

    public void removeClaim(Long id);

    public Collection<Claim> getAllClaimsSortByDate();

    public Collection<Claim> getAllClaimsSortByDateAsk();

    public Collection<Claim> getClaimsByDepartment(Long id);

    public Collection<Claim> getClaimsByclDate(Date d);

    public Collection<Claim> getClaimsByAffirmation(Boolean b);

    public Collection<Claim> getClaimsByAffirmationAsc(Boolean b);

    public Collection<Claim> getClaimsByTip(ClaimType t);
    
    public Collection<Claim> getClaimsByDepartmentAndTip(Long id, ClaimType t);

    public Collection<Claim> getClaimsByTipAsc(ClaimType t);

    public Collection<Claim> getClaimsByDepAndAffirmation(Long id, Boolean a);

    public Collection<Claim> getClaimsByDepAndAffirmationAsc(Long id, Boolean a);
    
    public Collection<Claim> getAllClaimsByDate(DateRange dr);

    public Collection<Claim> getAllClaimsByDepartmentAndDate(Long id, DateRange dr);

    public void addRecord(Record d);

    public void updateRecord(Record d, Long id);

    public void removeRecord(Long id);

    public Optional getRecordsById(Long id);

    public Collection<Record> getRecords();
    
    public Collection<Record> getRecordsByClaim(Long id);
    
    public Collection<Record> getRecordsByState(RecordStatus t);
    
    public Collection<Record> getRecordsByDate(Date d);
    
    public Collection<Record> getRecordsByHash(String d);
    
    public void addAppointment(Appointment ap);
    
    public Collection<Appointment> getAppointmentByRecordAndStatus(Record r, AppointmentStatus aps);
}
