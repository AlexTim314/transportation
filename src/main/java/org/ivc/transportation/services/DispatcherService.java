package org.ivc.transportation.services;

import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alextim
 */
@Service
@Transactional
public class DispatcherService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ClaimRepository claimRepository;

    public Claim findClaimByRecord(Record record) {
        return claimRepository.findByRecords(record);
    }
    
    public Record findRecordByAppointment(Appointment appointment){
        return recordRepository.findRecordByAppointmentId(appointment.getId());
    }

}
