package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
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
    List<Appointment> findAllByTransportDepIdAndAppDateTimeBetweenOrderByAppDateTimeDesc(Long transportDepId, LocalDateTime appDateTimeStart, LocalDateTime appDateTimeEnd);

}
