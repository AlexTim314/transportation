/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface WaybillRepository extends JpaRepository<Waybill, Long> {
    
}
