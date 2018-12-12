package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.ivc.transportation.config.trUtils;
import static org.ivc.transportation.config.trUtils.errNotSpecifiedDepartmentException;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.exceptions.NotSpecifiedDepartmentException;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author first
 */
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRep;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addAppointment(Appointment ap) {
        appointmentRep.save(ap);
    }

    @Override
    @Transactional
    public Collection<Appointment> getAppointmentByRecordAndStatus(Record r, trUtils.AppointmentStatus aps) {
        return appointmentRep.findByRecordIdAndStatusOrderByDateTimeDesc(r.getId(), aps);
    }

    @Override
    @Transactional
    public Collection<Appointment> getAppointmentByRecordsAndStatus(List<Record> r, trUtils.AppointmentStatus aps) {
        List<Long> ids = r.stream().map(u -> u.getId()).collect(Collectors.toList());
        return appointmentRep.findByRecordIdInAndStatusOrderByDateTimeDesc(ids, aps);
    }

    @Override
    @Transactional
    public Collection<Appointment> getAppointmentByRecordAndStatusAndDate(Record r, trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        return appointmentRep.findByRecordIdAndStatusAndDateTimeBetweenOrderByDateTimeDesc(r.getId(), aps, dateTimeStart, dateTimeEnd);
    }

    @Override
    public Collection<Appointment> getAppointmentByStatusAndDate(trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        return appointmentRep.findByStatusAndDateTimeBetweenOrderByDateTimeDesc(aps, dateTimeStart, dateTimeEnd);
    }

    @Override
    public Collection<Appointment> getAppointmentByDate(Principal principal, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointmentRep.findByDateTimeBetweenOrderByDateTimeDesc(dateTimeStart, dateTimeEnd);
        }
        return null;
    }

    @Override
    public Collection<Appointment> getAppointmentByDate(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        return appointmentRep.findByDateTimeBetweenOrderByDateTimeDesc(dateTimeStart, dateTimeEnd);
    }

    @Override
    public Collection<Appointment> getAppointmentByDriverAndDate(Driver d, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        return appointmentRep.findByDriverIdAndDateTimeBetweenOrderByDateTimeDesc(d.getId(), dateTimeStart, dateTimeEnd);
    }

    @Override
    public Collection<Appointment> getAppointmentByVehicleAndDate(Vehicle v, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        return appointmentRep.findByVehicleIdAndDateTimeBetweenOrderByDateTimeDesc(v.getId(), dateTimeStart, dateTimeEnd);
    }

    @Override
    public Collection<Appointment> getAppointmentByDriverAndStatusAndDate(Driver d, trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        return appointmentRep.findByDriverIdAndStatusAndDateTimeBetweenOrderByDateTimeDesc(d.getId(), aps, dateTimeStart, dateTimeEnd);
    }

    @Override
    public Collection<Appointment> getAppointmentByVehicleAndStatusAndDate(Vehicle v, trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        return appointmentRep.findByVehicleIdAndStatusAndDateTimeBetweenOrderByDateTimeDesc(v.getId(), aps, dateTimeStart, dateTimeEnd);
    }

    @Override
    public void removeAppointment(Long id) {
        appointmentRep.deleteById(id);
    }

    @Override
    public void updateAppointment(Appointment ap, Long id) {
        ap.setId(id);
        appointmentRep.save(ap);
    }

    @Override
    public Collection<Appointment> getAppointmentByRecordsAndStatusAndDate(List<Record> r, trUtils.AppointmentStatus aps, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        List<Long> ids = r.stream().map(u -> u.getId()).collect(Collectors.toList());
        return appointmentRep.findByRecordIdInAndStatusAndDateTimeBetweenOrderByDateTimeDesc(ids, aps, dateTimeStart, dateTimeEnd);
    }

}
