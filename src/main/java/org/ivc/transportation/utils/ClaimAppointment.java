package org.ivc.transportation.utils;

import lombok.Data;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;

/**
 *
 * @author Sokolov Slava
 */
@Data
public class ClaimAppointment {

    private Claim claim;
    private Appointment appointment;

    public ClaimAppointment(Claim claim, Appointment appointment) {
        this.claim = claim;
        this.appointment = appointment;
    }
    
}
