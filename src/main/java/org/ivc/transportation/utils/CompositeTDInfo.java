/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.util.List;
import lombok.Data;

/**
 *
 * @author first
 */

@Data
public class CompositeTDInfo {

    private VehiclesInfo vehInfo;
    private List<VehicleModelInfo> vehicleModelInfos;

    public CompositeTDInfo(VehiclesInfo vehInfo, List<VehicleModelInfo> vehicleModelInfos) {
        this.vehInfo = vehInfo;
        this.vehicleModelInfos = vehicleModelInfos;
    }
}
