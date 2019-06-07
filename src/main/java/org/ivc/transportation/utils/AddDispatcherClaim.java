/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.RouteTask;

/**
 *
 * @author user
 */
class RDate {

    LocalDateTime startDate;
    LocalDateTime entranceDate;
    LocalDateTime endDate;

    RDate(LocalDateTime StartDate, LocalDateTime EntranceDate, LocalDateTime EndDate) {
        this.startDate = StartDate;
        this.entranceDate = EntranceDate;
        this.endDate = EndDate;
    }
}

public class AddDispatcherClaim {

    Long vehicleId;
    Long driverId;
    Long carBossId;
    String purpose;
    List<RouteTask> routeTasks;
    List<RDate> dates;
    
    AddDispatcherClaim(Long VehicleId, Long DriverId, Long CarBossId, String Purpose, List<RouteTask> RouteTasks, List<RDate> Dates) {
        this.vehicleId = VehicleId;
        this.driverId = DriverId;
        this.carBossId = CarBossId;
        this.purpose = Purpose;
        this.routeTasks = RouteTasks;
        this.dates = Dates;
    }

}
