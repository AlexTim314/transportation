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

    List<Appointment> findAllOrderByDateTimeDesc();

    List<Appointment> findAllOrderByDateTimeAsk();

    List<Appointment> findByRecordIdOrderByDateTimeAsk(Long id);

    List<Appointment> findByRecordIdOrderByDateTimeDesk(Long id);
    
    List<Appointment> findByStatusOrderByDateTimeAsk(String s);
    
    List<Appointment> findByStatusOrderByDateTimeDesk(String s);
    
    List<Appointment> findByDriverIdOrderByDateTimeDesk(Long id);
    
    List<Appointment> findByDriverIdOrderByDateTimeAsk(Long id);
   
    List<Appointment> findByVechicleIdOrderByDateTimeDesk(Long id);
    
    List<Appointment> findByVechicleIdOrderByDateTimeAsk(Long id);

}
