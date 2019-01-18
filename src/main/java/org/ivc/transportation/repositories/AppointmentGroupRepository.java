package org.ivc.transportation.repositories;


import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentGroup;
import org.ivc.transportation.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("appointmentGroupRepository")
public interface AppointmentGroupRepository extends JpaRepository<AppointmentGroup, Long> {
    
    List<Record> findRecordsByAppointment(Appointment appointment);
    
    List<Appointment> findAppointmentsByRecord(Record record);
    
}
