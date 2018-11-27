/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.config.trUtils.AppointmentStatus;
import org.ivc.transportation.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nesterov Yuriy
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

//    List<Appointment> findAllOrderByDateTimeDesc();
//
//    List<Appointment> findAllOrderByDateTimeAsc();
//
//    List<Appointment> findByRecordIdOrderByDateTimeAsc(Long id);
//
    List<Appointment> findByRecordIdAndStatusOrderByDateTimeDesc(Long id, AppointmentStatus status);
    
    List<Appointment> findByRecordIdInAndStatusOrderByDateTimeDesc(List <Long> id, AppointmentStatus status);
    
    List<Appointment> findByRecordIdAndStatusAndDateTimeBetweenOrderByDateTimeDesc(Long id, AppointmentStatus status, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);
//    
//    List<Appointment> findByStatusOrderByDateTimeAsc(String s);
//    
//    List<Appointment> findByStatusOrderByDateTimeDesc(String s);
//    
//    List<Appointment> findByDriverIdOrderByDateTimeDesc(Long id);
//    
//    List<Appointment> findByDriverIdOrderByDateTimeAsc(Long id);
//   
//    List<Appointment> findByVechicleIdOrderByDateTimeDesc(Long id);
//    
//    List<Appointment> findByVechicleIdOrderByDateTimeAsc(Long id);

}
