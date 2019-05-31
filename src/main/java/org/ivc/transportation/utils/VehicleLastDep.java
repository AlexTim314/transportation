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
public interface VehicleLastDep {    
    Long getVehicleid();
    Long getDepartmentid();
    Integer getDepartmentOrder();
    LocalDateTime getStartDate();
}
