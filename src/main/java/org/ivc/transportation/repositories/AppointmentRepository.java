package org.ivc.transportation.repositories;

import java.time.ZonedDateTime;
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
    
    @Query(value = "select * from appointment where id in (select idResult from (select max(id) as idResult, record_id from appointment group by record_id) as lastAppointments) and " +
            "transport_dep_id = :transport_dep_id", nativeQuery = true)
    public List<Appointment> findAppointmentsByTransportDep(@Param("transport_dep_id") Long transportDepId);
    
    @Query(value = "select appointment.* from appointment, record where appointment.id in (select idResult from (select max(id) as idResult, record_id from appointment group by record_id) as lastAppointments) and " +
            "appointment.transport_dep_id = :transport_dep_id and record.start_date between :date_start and :date_end and appointment.record_id = record.id", nativeQuery = true)
    public List<Appointment> findAppointmentsByTransportDepTimeFilter(@Param("transport_dep_id") Long transportDepId, @Param("date_start") ZonedDateTime dateStart, @Param("date_end") ZonedDateTime dateEnd);
    
    @Query(value = "select appointment.* from appointment, record where " +
            "record.start_date between :date_start and :date_end and appointment.record_id = record.id and appointment.status = :status", nativeQuery = true)
    public List<Appointment> findAppointmentsForPlan(@Param("status") AppointmentStatus status,
            @Param("date_start") ZonedDateTime dateStart, @Param("date_end") ZonedDateTime dateEnd);
}
