package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentInfo;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.repositories.AppointmentInfoRepository;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.CarBossRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.VehicleModelRepository;
import org.ivc.transportation.repositories.VehicleRepository;
import org.ivc.transportation.utils.AddDispatcherClaim;
import org.ivc.transportation.utils.AppointmentClaim;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import static org.ivc.transportation.utils.EntitiesUtils.DISPATCHER_CANCEL_STR;
import org.ivc.transportation.utils.EntitiesUtils.VehicleStatus;
import org.ivc.transportation.utils.VehicleForPlan;
import org.ivc.transportation.utils.VehicleLastDep;
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

    @Autowired
    private CarBossRepository carBossRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleModelRepository transportDepRepository;

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

    public List<CompositeClaimRecord> getAppointmentsTimeFilter(Principal principal, LocalDateTime dateStart, LocalDateTime dateEnd) {
        if (findTransportDepByUser(principal) == null) {
            System.out.println("Внимание! У пользователя не указан транспортный отдел, необходимый методу DispatcherService.getAppointmentsTimeFilter(...).");
            return null;
        }
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
        LocalDateTime dStart = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(date, LocalTime.of(23, 59));
        return appointmentRepository.findAppointmentsForPlan(status.ordinal(), dStart, dEnd);
    }

    public List<Vehicle> getVehiclesForPlan() {
        return vehicleRepository.findVehiclesForPlan();
    }

    public List<VehicleForPlan> getVehiclesForPlan(LocalDate date) {
        LocalDateTime startTime = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime endTime = LocalDateTime.of(date, LocalTime.of(23, 59));
        return vehicleRepository.findVehiclesForPlan(startTime, endTime);
    }

    public List<VehicleLastDep> getVehicleLastDep() {
        return vehicleRepository.findVehicleLastDep();
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
        LocalDateTime dateStart = recordRepository.findRecordByAppointmentId(appointment.getId()).getStartDate();
        LocalDateTime dateEnd = recordRepository.findRecordByAppointmentId(appointment.getId()).getEndDate();
        //чтобы sql не ругался на дату null
        if (dateEnd == null) {
            dateEnd = LocalDateTime.of(dateStart.getYear(), dateStart.getMonth(), dateStart.getDayOfMonth(), 23, 59);
        }
        List<Driver> dl = driverRepository.findVacantByTransportDepId(findTransportDepByUser(principal).getId(), dateStart, dateEnd);
        return dl;
    }

    public List<Vehicle> getVacantVehicles(Principal principal, Appointment appointment) {
        Record record = recordRepository.findRecordByAppointmentId(appointment.getId());
        LocalDateTime dateStart = record.getStartDate();
        LocalDateTime dateEnd = record.getEndDate();
        //чтобы sql не ругался на дату null
        if (dateEnd == null) {
            dateEnd = LocalDateTime.of(dateStart.getYear(), dateStart.getMonth(), dateStart.getDayOfMonth(), 23, 59);
        }
        if (appointment.getVehicleModel() != null) {
            return vehicleRepository.findVacantByTransportDepIdWithModel(findTransportDepByUser(principal).getId(),
                    appointment.getVehicleModel().getId(), dateStart, dateEnd);
        } else {
            return vehicleRepository.findVacantByTransportDepIdWithoutModel(findTransportDepByUser(principal).getId(), dateStart, dateEnd);
        }
    }

    public Boolean getPermit(Principal principal) {
        AppUser user = getUser(principal);
        for (AppRole role : user.getRoles()) {
            if (role.getRoleName().equals("ROLE_ADMIN") || role.getRoleName().equals("ROLE_MANAGER")) {
                return true;
            }
        }
        return false;
    }

    public String getUserName(Principal principal) {
        final char dm = (char) 34;
        AppUser user = getUser(principal);
        String un = "{" + dm + "username" + dm + ":" + dm + user.getUsername() + dm + "}";
        return un;
    }

    public List<VehicleModel> findVehicleModelsByTransportDep(Principal principal) {
        AppUser user = getUser(principal);
        return vehicleModelRepository.findVehicleModelsByTransportDepId(user.getTransportDep().getId());
    }

    public List<CarBoss> findCarBossesByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return carBossRepository.findByDepartment(department);
        }
        return null;
    }

    private Department getDepartment(Principal principal) {
        if (principal == null) {
            return null;
        }
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        return userRepository.findByUsername(loginedUser.getUsername()).getDepartment();

    }

    public CarBoss saveCarBoss(Principal principal, CarBoss carBoss) {
        return carBossRepository.save(carBoss);
    }

    public void deleteCarBoss(CarBoss carBoss) {
        carBossRepository.delete(carBoss);
    }

    public void createClaim(Principal principal, AddDispatcherClaim tclaim) {
        List<Record> records;
        records = new ArrayList<Record>();
        AppUser disp = getUser(principal);
        Vehicle v = vehicleRepository.findById(tclaim.getVehicleId()).get();
        for (int i = 0; i < tclaim.getDates().size(); i++) {
            Record r = new Record();
            r.setEntranceDate(tclaim.getDates().get(i).getEntranceDate());
            r.setStartDate(tclaim.getDates().get(i).getStartDate());
            r.setEndDate(tclaim.getDates().get(i).getEndDate());
            r.setAffirmationDate(LocalDateTime.now());
            Appointment app = new Appointment();
            app.setCreationDate(LocalDateTime.now());
            app.setCreator(disp);
            app.setDriver(driverRepository.findById(tclaim.getDriverId()).get());
            app.setNote("Заявка подана диспетчером");
            app.setStatus(AppointmentStatus.READY);
            app.setTransportDep(disp.getTransportDep());
            app.setVehicle(v);
            app.setVehicleModel(v.getModel());
            List<Appointment> apps = new ArrayList<Appointment>();
            apps.add(app);
            r.setAppointments(apps);
            records.add(r);
        }
        Claim claim = new Claim();
        claim.setActual(true);
        claim.setAffirmationDate(LocalDateTime.now());
        claim.setAffirmator(disp);
        claim.setCarBoss(carBossRepository.findById(tclaim.getCarBossId()).get());
        claim.setCreationDate(LocalDateTime.now());
        claim.setCreator(disp);
        claim.setDepartment(disp.getDepartment());
        claim.setPurpose(tclaim.getPurpose());
        claim.setRecords(records);
        claim.setRouteTasks(tclaim.getRouteTasks());
        claim.setSpecialization(v.getModel().getVehicleType().getSpecialization());
        claim.setVehicleType(v.getModel().getVehicleType());
        claimRepository.save(claim);
    }

    public List<AppointmentClaim> getAppointments1(Principal principal) {
        return claimRepository.findAppointmentClaims(getUser(principal).getTransportDep().getId());
    }

}
