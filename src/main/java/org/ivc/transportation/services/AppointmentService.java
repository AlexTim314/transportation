package org.ivc.transportation.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.ivc.transportation.config.trUtils;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vechicle;

/**
 *
 * @author first
 */
public interface AppointmentService {
    
    public void addAppointment(Appointment ap);

    public Collection<Appointment> getAppointmentByRecordAndStatus(Record r, trUtils.AppointmentStatus aps);

    public Collection<Appointment> getAppointmentByRecordsAndStatus(List<Record> r, trUtils.AppointmentStatus aps);

    public Collection<Appointment> getAppointmentByRecordAndStatusAndDate(Record r, trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByStatusAndDate(trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByDate(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByDriverAndDate(Driver d, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByVechicleAndDate(Vechicle v, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByDriverAndStatusAndDate(Driver d, trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public Collection<Appointment> getAppointmentByVechicleAndStatusAndDate(Vechicle v, trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

    public void removeAppointment(Long id);
    
}
