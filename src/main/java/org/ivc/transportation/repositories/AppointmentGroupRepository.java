/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.AppointmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava & Degtyarev Fedor & Nesterov Yuriy
 */
@Repository
public interface AppointmentGroupRepository extends JpaRepository<AppointmentGroup, Long> {
    
    List<AppointmentGroup> findByRecordId(Long recordId);
    
    List<AppointmentGroup> findByAppointmentId(Long appointmentId);

}
