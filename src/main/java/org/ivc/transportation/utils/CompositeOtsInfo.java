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
 * @author user
 */

@Data
public class CompositeOtsInfo {
    
    private OtsInfo otsInfo;
    private List<VehicleModelInfo> vehicleModelInfos;
    
    public CompositeOtsInfo(OtsInfo otsInfo, List<VehicleModelInfo> vehicleModelInfos) {
        this.otsInfo = otsInfo;
        this.vehicleModelInfos = vehicleModelInfos;
    }

}
