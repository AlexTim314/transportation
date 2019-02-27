/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.utils.EntitiesUtils.VehicleSpecialization;

/**
 *
 * @author nodata
 */

public interface CompositeModelTransportDep {
    Long getvehiclemodelid();
    String getmodelname();
    Long gettransportdepid();
    String getshortname();
    VehicleSpecialization getvehiclespecialization();
}
