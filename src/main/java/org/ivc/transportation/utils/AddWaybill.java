/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author user
 */

@Data
public class AddWaybill {
    
    private LocalDateTime openedDate;
    private LocalDateTime closedDate;
    private String number;
    private Long vehicleId;
    private Long appointmentId;
    
    public AddWaybill(LocalDateTime OpenedDate, LocalDateTime ClosedDate, String Number, Long VehicleId, Long AppointmentId) {
        this.openedDate = OpenedDate;
        this.closedDate = ClosedDate;
        this.number = Number;
        this.vehicleId = VehicleId;
        this.appointmentId = AppointmentId;
    }
    
}
