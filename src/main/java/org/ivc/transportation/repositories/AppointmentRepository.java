package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public List<Appointment> findByStatus(AppointmentStatus status);
    
    
}
