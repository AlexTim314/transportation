/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.util.List;
import lombok.Data;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleModel;

/**
 *
 * @author user
 */

@Data
public class CompositeTransportDepVehicleModels {
    private TransportDep transportDep;
    private List<VehicleModel> vehicleModels;
    
    public CompositeTransportDepVehicleModels(TransportDep transportDep, List<VehicleModel> vehicleModels) {
        this.transportDep = transportDep;
        this.vehicleModels = vehicleModels;
    }
}
