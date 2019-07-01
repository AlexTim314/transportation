/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public interface AppointmentClaim {

    Long getVehicleTypeId();

    Long getVehicleTypeSpecialization();

    LocalDateTime getStartdate();

    LocalDateTime getEnddate();

    LocalDateTime getEntrancedate();

    String getRoute();

    Long getVehicleModelId();
    
    Long getVehicleid();

    String getPurpose();

    Long getDriverid();

    String getDepshortname();

    String getCarbossfirstname();

    String getCarbossname();

    String getCarbosssurname();

    String getCarbossphone();
    
    String getAppstatus();
    
    String getAppnote();
    
    Long getAppointmentid();
    
    LocalDateTime getAppcrdate();
    
    Long getCreatorid();
        
    Long getRecordid();
    
    Long getClaimid();

}
