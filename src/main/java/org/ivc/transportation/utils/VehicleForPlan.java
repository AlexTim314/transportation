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
public interface VehicleForPlan {    
    Long getDepartmentid();
    Long getVehicleid();
    String getModelname();
    String getNumber();
    String getOtsname();
    String getPurpose();
    String getCarrbossfirstname();
    String getCarrbossname();
    String getCarrbosssurname();
    String getRoute();
    LocalDateTime getEntrancedate();
    LocalDateTime getStartdate();
    LocalDateTime getEnddate();
    String getDriverfirstname();
    String getDrivername();
    String getDriversurname();
}
