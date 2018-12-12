package org.ivc.transportation.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.ivc.transportation.config.trUtils.AppointmentStatus;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;

/**
 *
 * @author first
 */
public interface AppointmentService {
    
    public void addAppointment(Appointment ap);

    public Collection<Appointment> getAppointmentByRecordAndStatus(Record r, AppointmentStatus aps);

    public Collection<Appointment> getAppointmentByRecordsAndStatus(List<Record> r, AppointmentStatus aps);

    public Collection<Appointment> getAppointmentByRecordAndStatusAndDate(Record r, AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByRecordsAndStatusAndDate(List<Record> r, AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);
    
    public Collection<Appointment> getAppointmentByStatusAndDate(AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByDate(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByDriverAndDate(Driver d, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByVechicleAndDate(Vehicle v, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByDriverAndStatusAndDate(Driver d, AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByVechicleAndStatusAndDate(Vehicle v, AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public void updateAppointment(Appointment ap, Long id);
    
    public void removeAppointment(Long id);
    
}
