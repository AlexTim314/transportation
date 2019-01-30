package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public List<Appointment> findByStatus(AppointmentStatus status);
    @Query(value = "select * from appointment where id = (select max(id) from appointment where record_id = :record_id)", nativeQuery = true)
    public Appointment getLastByRecordId(@Param("record_id") Long recordId);
    
    @Query(value = "select appointment.* from appointment " +
            "where transport_dep_id = :transport_dep_id", nativeQuery = true)
    public List<Appointment> findAppointmentsByTransportDep(@Param("transport_dep_id") Long transportDepId);
    
    
    
}
