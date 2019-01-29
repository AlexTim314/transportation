/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import org.ivc.transportation.entities.Appointment;

/**
 *
 * @author user
 */
public class CompositeRecordIdAppointment {

    private Long recordId;
    private Appointment appointment;

    public CompositeRecordIdAppointment(Long recordId, Appointment appointment) {
        this.recordId = recordId;
        this.appointment = appointment;
    }

}
