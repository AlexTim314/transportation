/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nesterov Yuriy
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByRecordIdOrderByDateTimeAsc(Long id);

    List<Appointment> findByRecordIdOrderByDateTimeDesc(Long id);
    
    List<Appointment> findByStatusOrderByDateTimeAsc(String s);
    
    List<Appointment> findByStatusOrderByDateTimeDesc(String s);
    
    List<Appointment> findByDriverIdOrderByDateTimeDesc(Long id);
    
    List<Appointment> findByDriverIdOrderByDateTimeAsc(Long id);
   
    List<Appointment> findByVechicleIdOrderByDateTimeDesc(Long id);
    
    List<Appointment> findByVechicleIdOrderByDateTimeAsc(Long id);

}
