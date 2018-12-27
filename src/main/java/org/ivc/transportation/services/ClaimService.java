package org.ivc.transportation.services;

import java.util.Collection;
import java.sql.Date;
import java.util.Optional;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.DateRange;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.FileStorage;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.VehicleType;
import org.springframework.web.multipart.MultipartFile;

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

    public Collection<Claim> getClaimsByClType(ClaimType t);

    public Collection<Claim> getClaimsByDepartmentAndClType(Long id, ClaimType t);

    public Collection<Claim> getClaimsByClTypeAsc(ClaimType t);

    public Collection<Claim> getClaimsByDepAndAffirmation(Long id, Boolean a);

    public Collection<Claim> getClaimsByDepAndAffirmationAsc(Long id, Boolean a);

    public Collection<Claim> getAllClaimsByDate(DateRange dr);

    public Collection<Claim> getAllClaimsByDepartmentAndDate(Long id, DateRange dr);
    
    public Collection<Claim> getAllClaimsByDepartmentAndAffirmationAndDate(Long id, Boolean a, DateRange dr);

    public void addRecord(Record d);

    public void updateRecord(Record d, Long id);

    public void removeRecord(Long id);

    public Optional getRecordsById(Long id);

    public Collection<Record> getRecords();

    public Collection<Record> getRecordsByClaim(Long id);

    public Collection<Record> getRecordsByState(RecordStatus t);

    public Collection<Record> getRecordsByDate(Date d);

    public Collection<Record> getRecordsByHash(String d);

    public Collection<VehicleType> getVehicleTypes();

    public Collection<VehicleType> getVehicleTypesBySpicialization(String s);
    
    public FileStorage storeFile(MultipartFile file);
    
    public Collection<FileStorage> getFiles(Long id);
    
    public FileStorage getFile(Long id);
}
