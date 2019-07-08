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
 * @author first
 */

@Data
public class CompositeAppointmentClaim {
    
    private AppointmentClaim appClaim;
    private List<RouteTask> routeTasks;
    
    public CompositeAppointmentClaim(AppointmentClaim appClaim, List<RouteTask> routeTasks) {
        this.appClaim = appClaim;
        this.routeTasks = routeTasks;
    }

}