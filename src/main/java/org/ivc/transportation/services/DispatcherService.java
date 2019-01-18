/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.List;
import org.ivc.transportation.entities.AppointmentGroup;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alextim
 */
public class DispatcherService {
    
    @Autowired
    private RecordRepository recordRepository;
    
    @Autowired
    private ClaimRepository claimRepository;
    
    public List<Record> findRecordsByAppointmentGroup(AppointmentGroup appointmentGroup){
        return recordRepository.findRecordsByAppointmentGroup(appointmentGroup);
    }
    
    public Claim findClaimByRecord(Record record){
        return claimRepository.findByRecords(record);
    }
            
    
}
