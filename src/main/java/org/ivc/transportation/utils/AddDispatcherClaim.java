/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.util.List;
import lombok.Data;
import org.ivc.transportation.entities.RouteTask;

/**
 *
 * @author user
 */
@Data
public class AddDispatcherClaim {

    private Long vehicleId;
    private Long driverId;
    private Long carBossId;
    private String purpose;
    private List<RouteTask> routeTasks;
    private List<RDate> dates;
    
    AddDispatcherClaim(Long VehicleId, Long DriverId, Long CarBossId, String Purpose, List<RouteTask> RouteTasks, List<RDate> Dates) {
        this.vehicleId = VehicleId;
        this.driverId = DriverId;
        this.carBossId = CarBossId;
        this.purpose = Purpose;
        this.routeTasks = RouteTasks;
        this.dates = Dates;
    }

}
