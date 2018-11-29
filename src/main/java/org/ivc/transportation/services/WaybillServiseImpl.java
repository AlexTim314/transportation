/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import org.ivc.transportation.entities.Waybill;
import org.ivc.transportation.repositories.WaybillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alextim
 */
@Service
@Transactional
public class WaybillServiseImpl implements WaybillService {

    @Autowired
    private WaybillRepository waybillRepository;

    @Override
    @Transactional
    public void addWaybill(Waybill waybill) {
        waybillRepository.save(waybill);
    }

    @Override
    @Transactional
    public void updateWaybill(Waybill waybill, Long id) {
        waybill.setId(id);
        waybillRepository.save(waybill);
    }

    @Override
    @Transactional
    public void removeWaybill(Long id) {
        waybillRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Waybill getWaybillBySeriesAndNumber(String series, String number) {
        return waybillRepository.findBySeriesAndNumber(series, number);
    }

}
