package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentInfo;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.AppointmentInfoRepository;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import static org.ivc.transportation.utils.EntitiesUtils.DISPATCHER_CANCEL_STR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alextim
 */
@Service
@Transactional
public class DispatcherService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    public List<Appointment> findByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

    public Claim findClaimByRecord(Record record) {
        return claimRepository.findByRecords(record);
    }

    public Record findRecordByAppointment(Appointment appointment) {
        return recordRepository.findRecordByAppointmentId(appointment.getId());
    }

    public List<CompositeClaimRecord> getAppointments(Principal principal) {
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByTransportDep(findTransportDepByUser(principal).getId());
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        appointmentList.forEach(u -> result.add(
                new CompositeClaimRecord(new Claim(claimRepository.findClaimByAppointmentId(u.getId())),
                        recordRepository.findRecordByAppointmentId(u.getId()), u)
        ));
        return result;
    }

    public List<CompositeClaimRecord> getAppointmentsTimeFilter(Principal principal, ZonedDateTime dateStart, ZonedDateTime dateEnd) {
        List<Appointment> appointmentList = appointmentRepository.findAppointmentsByTransportDepTimeFilter(findTransportDepByUser(principal).getId(), dateStart, dateEnd);
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        appointmentList.forEach(u -> result.add(
                new CompositeClaimRecord(new Claim(claimRepository.findClaimByAppointmentId(u.getId())),
                        recordRepository.findRecordByAppointmentId(u.getId()), u)
        ));
        return result;
    }

    private TransportDep findTransportDepByUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }

    private void updateClaimActual(Appointment appointment) {
        Claim claim = claimRepository.findClaimByAppointmentId(appointment.getId());
        List<Appointment> appList = new ArrayList<Appointment>();
        claim.getRecords().forEach(u -> appList.add(appointmentRepository.getLastByRecordId(u.getId())));
        boolean fl = true;
        for (Appointment appt : appList) {
            if (appt == null || appt.getStatus() != AppointmentStatus.COMPLETED) {
                fl = false;
                break;
            }
        }
        if (fl) {
            claim.setActual(false);
            claimRepository.save(claim);
        }
    }

    public List<Appointment> updateAppointments(Principal principal, List<Appointment> appointments) {
        List<Appointment> result = new ArrayList<Appointment>();
        appointments.forEach(appt -> {
            appt.setCreationDate(LocalDateTime.now());
            appt.setStatus(AppointmentStatus.READY);
            appt.setNote("Транспорт и водитель назначены");
            appt = appointmentRepository.save(appt);
            updateClaimActual(appt);
            result.add(appt);
            appointmentInfoRepository
                    .save(new AppointmentInfo(LocalDateTime.now(),
                            appt.getStatus(),
                            appt.getNote(),
                            appt));
        });
        return result;
    }

    public Appointment updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
        appointmentInfoRepository
                .save(new AppointmentInfo(LocalDateTime.now(),
                        appointment.getStatus(),
                        appointment.getNote(),
                        appointment));
        updateClaimActual(appointment);
        return appointment;
    }

    public List<Appointment> getAppointmentsForPlan(AppointmentStatus status, ZonedDateTime date) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(0, 0), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(23, 59), ZoneId.systemDefault());
        return appointmentRepository.findAppointmentsForPlan(status.ordinal(), dStart, dEnd);
    }

    public Appointment getAppointmentById(Long apptId) {
        return appointmentRepository.findById(apptId).get();
    }
    
    public Record recordCancel(Principal principal, CompositeRecordIdAppointment compositeRecordIdAppointment) {
        System.out.println(compositeRecordIdAppointment);
        Appointment app = compositeRecordIdAppointment.getAppointment();
        if (app.getId() == null) {
            app.setCreationDate(LocalDateTime.now());
            app.setCreator(getUser(principal));
            app.setNote(DISPATCHER_CANCEL_STR + app.getNote());
        }
        app.setStatus(AppointmentStatus.CANCELED_BY_DISPATCHER);
        app = appointmentRepository.save(app);
        appointmentInfoRepository.save(new AppointmentInfo(LocalDateTime.now(), app.getStatus(), app.getNote(), app));
        Record record = recordRepository.findById(compositeRecordIdAppointment.getRecordId()).get();
        record.getAppointments().add(app);
        return recordRepository.save(record);
    }
    
    private AppUser getUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername());
        }
        return null;
    }

    public List<Driver> getVacantDrivers(Principal principal) {
        return driverRepository.findVacantByTransportDepId(findTransportDepByUser(principal).getId());
    }

}
