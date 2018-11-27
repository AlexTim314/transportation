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

    List<Appointment> findByRecordIdAndStatusOrderByDateTimeDesc(Long id, AppointmentStatus status);

    List<Appointment> findByRecordIdInAndStatusOrderByDateTimeDesc(List<Long> id, AppointmentStatus status);

    List<Appointment> findByRecordIdAndStatusAndDateTimeBetweenOrderByDateTimeDesc(Long id, AppointmentStatus status, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    List<Appointment> findByStatusAndDateTimeBetweenOrderByDateTimeDesc(AppointmentStatus status, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    List<Appointment> findByDateTimeBetweenOrderByDateTimeDesc(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    List<Appointment> findByDriverIdAndDateTimeBetweenOrderByDateTimeDesc(Long id, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    List<Appointment> findByVechicleIdAndDateTimeBetweenOrderByDateTimeDesc(Long id, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    List<Appointment> findByDriverIdAndStatusAndDateTimeBetweenOrderByDateTimeDesc(Long id, AppointmentStatus status, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    List<Appointment> findByVechicleIdAndStatusAndDateTimeBetweenOrderByDateTimeDesc(Long id, AppointmentStatus status, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

}
