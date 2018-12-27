package org.ivc.transportation.services;


import java.io.IOException;
import java.util.Collection;
import java.sql.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.DateRange;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.config.trUtils.*;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.FileStorage;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.exceptions.FileNotFoundException;
import org.ivc.transportation.exceptions.FileStorageException;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.FileStorageRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ivc.transportation.repositories.VehicleTypeRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    private VehicleTypeRepository typeVehicleRep;
    
    @Autowired
    private FileStorageRepository fileStorageRep;

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
    public Collection<Claim> getClaimsByClType(ClaimType t) {
        return claimRep.findByClTypeOrderByClDateDesc(t);
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
    public Collection<Claim> getClaimsByClTypeAsc(ClaimType t) {
        return claimRep.findByClTypeOrderByClDateAsc(t);
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
    public Collection<Claim> getClaimsByDepartmentAndClType(Long id, ClaimType t) {
        return claimRep.findByClTypeAndDepartmentIdOrderByClDateDesc(id, t);
    }
    
      @Override
      @Transactional
    public Collection<Claim> getAllClaimsByDepartmentAndAffirmationAndDate(Long id, Boolean a, DateRange dr) {
        return claimRep.findAllByDepartmentIdAndAffirmationAndClDateBetweenOrderByClDateDesc(id, a, dr.StartDate, dr.EndDate);
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
    public Collection<VehicleType> getVehicleTypes() {
        return typeVehicleRep.findAll();
    }

    @Override
    public Collection<VehicleType> getVehicleTypesBySpicialization(String s) {
        return typeVehicleRep.findBySpecialization(s);
    }

    @Override
    public FileStorage storeFile(MultipartFile file) {
       String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Проверка на наличие неверных символов в имени файла
            if(fileName.contains("..")) {
                throw new FileStorageException("Неверный путь к файлу " + fileName);
            }

            FileStorage dbFile = new FileStorage(fileName, file.getContentType(), file.getBytes());

            return fileStorageRep.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Невозможно сохранить файл " + fileName + ". Повторите попытку!", ex);
        } 
    }

    @Override
    public Collection<FileStorage> getFiles(Long id) {
       return fileStorageRep.findByClaimId(id);
    }
    
    @Override
    public FileStorage getFile(Long fileId) {
        return fileStorageRep.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

  




    
    

}
