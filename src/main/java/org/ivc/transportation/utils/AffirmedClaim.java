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
public interface AffirmedClaim {
    Long getDepartmentid();
    String getDepartmentfullname();
    Long getClaimid();
    String getVehicletypename();
    Long getClaimspecialization();
    String getRoute();
    Long getRecordid();
    Long getAppointmentid();
    String getAppointmentstatus();
    String getAppointmentnote();
    Long getVehicletypeid();
    String getClaimpurpose();
    LocalDateTime getStartdate();
    LocalDateTime getEnddate();
    LocalDateTime getEntrancedate();
    Long getDriverid();
    Long getVehicleid();
    Long getTransportdepid();
    Long getModelid();
    Long getAppcreatorid();
    Long getAppmodifid();
    Long getCreatorid();
    String getCreatorname();
    Long getModificatorid();
    String getModificatorname();
}
