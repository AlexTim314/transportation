/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;

/**
 *
 * @author nodata
 */
@Data
public class CompositeClaimRecord {
    private Claim claim;
    private Record record;
    private Appointment appointment;
    
    public CompositeClaimRecord(Claim claim, Record record, Appointment appointment) {
        this.claim = claim;
        this.record = record;
        this.appointment = appointment;
    }
    
}
