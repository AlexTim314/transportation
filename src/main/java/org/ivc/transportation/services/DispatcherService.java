package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentInfo;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.repositories.AppointmentInfoRepository;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.VehicleRepository;
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

    @Autowired
    private VehicleRepository vehicleRepository;

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
        return appointmentList.stream().map((app) -> {
            return new CompositeClaimRecord(new Claim(claimRepository.findClaimByAppointmentId(app.getId())),
                    recordRepository.findRecordByAppointmentId(app.getId()), app);
        }).collect(Collectors.toList());
    }

    public List<CompositeClaimRecord> getAppointmentsTimeFilter(Principal principal, ZonedDateTime dateStart, ZonedDateTime dateEnd) {
        List<Appointment> appointmentList = appointmentRepository
                .findAppointmentsByTransportDepTimeFilter(findTransportDepByUser(principal).getId(), dateStart, dateEnd);
        return appointmentList.stream().map((app) -> {
            return new CompositeClaimRecord(new Claim(claimRepository.findClaimByAppointmentId(app.getId())),
                    recordRepository.findRecordByAppointmentId(app.getId()), app);
        }).collect(Collectors.toList());
    }

    private TransportDep findTransportDepByUser(Principal principal) {
        if (principal == null) {
            return null;
        }
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
    }

    /**
     * Метод проверяет остались ли в заявке (Claim) невыполненные назначения
     * (Appointment).
     *
     * @param appointment назначение по которому ищется заявка для проверки
     */
    private void updateClaimActual(Appointment appointment) {
        Claim claim = claimRepository.findClaimByAppointmentId(appointment.getId());

        if (claim.getRecords().stream().anyMatch(
                (record) -> {
                    Appointment appt = appointmentRepository.getLastByRecordId(record.getId());
                    return (appt == null || appt.getStatus() != AppointmentStatus.COMPLETED);
                }
        )) { //если в заявке нашлось хоть одно назначение со статусом отличным от COMPLETED (или равная null), то  метод завершается
            return;
        }
        //если все COMPLETED, то меняем статус Claim и обновляем базу.
        claim.setActual(false);
        claimRepository.save(claim);

    }

    public List<Appointment> updateAppointments(Principal principal, List<Appointment> appointments) {
        List<Appointment> result = new ArrayList<>();
        appointments.forEach(appt -> {
            appt.setStatus(AppointmentStatus.READY);
            appt.setNote("Транспорт и водитель назначены");
            appt.setModificator(getUser(principal));
            appt = appointmentRepository.save(appt);
            updateClaimActual(appt);
            result.add(appt);
            appointmentInfoRepository
                    .save(new AppointmentInfo(LocalDateTime.now(),
                            appt.getStatus(),
                            appt.getNote(),
                            appt, getUser(principal)));
        });
        return result;
    }

    public Appointment updateAppointment(Principal principal, Appointment appointment) {
//        appointment.setStatus(AppointmentStatus.READY);
//        appointment.setNote("Транспорт и водитель назначены");
        appointmentRepository.save(appointment);
        appointmentInfoRepository
                .save(new AppointmentInfo(LocalDateTime.now(),
                        appointment.getStatus(),
                        appointment.getNote(),
                        appointment, getUser(principal)));
        updateClaimActual(appointment);
        return appointment;
    }

    public List<Appointment> getAppointmentsForPlan(AppointmentStatus status, LocalDate date) {
        ZonedDateTime dStart = ZonedDateTime.of(date, LocalTime.of(0, 0), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(date, LocalTime.of(23, 59), ZoneId.systemDefault());
        return appointmentRepository.findAppointmentsForPlan(status.ordinal(), dStart, dEnd);
    }

    public Appointment getAppointmentById(Long apptId) {
        return appointmentRepository.findById(apptId).get();
    }

    public Record recordCancel(Principal principal, CompositeRecordIdAppointment compositeRecordIdAppointment) {
        Appointment app = compositeRecordIdAppointment.getAppointment();
        if (app.getId() == null) {
            app.setCreationDate(LocalDateTime.now());
            app.setCreator(getUser(principal));
            app.setNote(DISPATCHER_CANCEL_STR + app.getNote());
        }
        app.setStatus(AppointmentStatus.CANCELED_BY_DISPATCHER);
        app = appointmentRepository.save(app);
        appointmentInfoRepository.save(new AppointmentInfo(LocalDateTime.now(), app.getStatus(), app.getNote(), app, getUser(principal)));
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

    public List<Driver> getVacantDrivers(Principal principal, Appointment appointment) {
        ZonedDateTime dateStart = recordRepository.findRecordByAppointmentId(appointment.getId()).getStartDate();
        ZonedDateTime dateEnd = recordRepository.findRecordByAppointmentId(appointment.getId()).getEndDate();
        List<Driver> dl = driverRepository.findVacantByTransportDepId(findTransportDepByUser(principal).getId(), dateStart, dateEnd);
        return dl;
    }

    public List<Vehicle> getVacantVehicles(Principal principal, Appointment appointment) {
        ZonedDateTime dateStart = recordRepository.findRecordByAppointmentId(appointment.getId()).getStartDate();
        ZonedDateTime dateEnd = recordRepository.findRecordByAppointmentId(appointment.getId()).getEndDate();
        if (appointment.getVehicleModel() != null)
            return vehicleRepository.findVacantByTransportDepIdWithModel(findTransportDepByUser(principal).getId(),
                    appointment.getVehicleModel().getId(), dateStart, dateEnd);
        else
            return vehicleRepository.findVacantByTransportDepIdWithoutModel(findTransportDepByUser(principal).getId(), dateStart, dateEnd);
    }

}
