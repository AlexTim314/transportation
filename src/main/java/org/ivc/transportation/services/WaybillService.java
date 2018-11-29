/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import org.ivc.transportation.entities.Waybill;

/**
 *
 * @author alextim
 */
public interface WaybillService {
    
    public void addWaybill(Waybill waybill);
    
    public void updateWaybill(Waybill waybill, Long id);

    public void removeWaybill(Long id);
    
    public Waybill getWaybillBySeriesAndNumber(String series, String number);       

}
