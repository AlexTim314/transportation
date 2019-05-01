package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentInfo;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.RouteTask;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.repositories.AppointmentInfoRepository;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.CarBossRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.RouteTaskRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.VehicleModelRepository;
import org.ivc.transportation.repositories.VehicleRepository;
import org.ivc.transportation.utils.AffirmedClaim;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
import org.ivc.transportation.utils.CompositeModelTransportDep;
import org.ivc.transportation.utils.CompositeOtsInfo;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.ivc.transportation.utils.EntitiesUtils;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import static org.ivc.transportation.utils.EntitiesUtils.PLANNER_CANCEL_STR;
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
public class PlanningService {



    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private CarBossRepository carBossRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RouteTaskRepository routeTaskRepository;

    @Autowired
    private TransportDepRepository transportDepRepository;

    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    private Appointment prepareAppointment(Appointment appointment) {
        return appointment == null ? new Appointment() : appointment;
    }

    private List<CompositeClaimRecord> getCompositeClaimRecordsAll(Department department) {
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        LocalDateTime r = LocalDateTime.now();
        recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullAndActualIsTrue(department.getId())
                .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
        System.out.println(LocalDateTime.now() + " - " + r);
        return result;
    }

    private List<CompositeClaimRecord> getCompositeClaimRecordsTimeFilter(Department department, LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullAndActualIsTrueTimeFilter(department.getId(), dateStart, dateEnd)
                .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
        return result;
    }

    private List<CompositeClaimRecord> getCompositeClaimRecordsAllPlanned(Department department) {
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullPlanned(department.getId())
                .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
        return result;
    }

    private List<CompositeClaimRecord> getCompositeClaimRecordsTimeFilterPlanned(Department department, LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullTimeFilterPlanned(department.getId(), dateStart, dateEnd)
                .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsAll() {
        //
//        System.out.println("START: " + LocalDateTime.now());
        //
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        departmentRepository.findDepartmentsWithAffirmedClaims().forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
        result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecordsAll(u.getDepartment())));
        //
//        System.out.println("END: " + LocalDateTime.now());
        //
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsTimeFilter(LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        departmentRepository.findDepartmentsWithAffirmedClaimsByTimeFilter(dateStart, dateEnd).forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
        result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecordsTimeFilter(u.getDepartment(), dateStart, dateEnd)));
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getPlannedClaimsAll() {
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        departmentRepository.findDepartmentsWithPlannedClaims().forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
        result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecordsAllPlanned(u.getDepartment())));
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getPlannedClaimsTimeFilter(LocalDateTime dateStart, LocalDateTime dateEnd) {
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        departmentRepository.findDepartmentsWithPlannedClaimsByTimeFilter(dateStart, dateEnd).forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
        result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecordsTimeFilterPlanned(u.getDepartment(), dateStart, dateEnd)));
        return result;
    }

    public List<Record> createAppointments(Principal principal, List<CompositeRecordIdAppointment> compositeRecordIdAppointmentList) {
        List<Record> result = new ArrayList<Record>();
        for (int i = 0; i < compositeRecordIdAppointmentList.size(); i++) {
            CompositeRecordIdAppointment compositeRecordIdAppointment = compositeRecordIdAppointmentList.get(i);
            Appointment app = compositeRecordIdAppointment.getAppointment();
            app.setCreationDate(LocalDateTime.now());
            app.setCreator(getUser(principal));
            app.setNote("Заявка передана в транспортный отдел");
            app.setStatus(EntitiesUtils.AppointmentStatus.IN_PROGRESS);
            app = appointmentRepository.save(app);
            appointmentInfoRepository.save(new AppointmentInfo(app.getCreationDate(), app.getStatus(), app.getNote(), app, getUser(principal)));
            Record rd = recordRepository.findById(compositeRecordIdAppointment.getRecordId()).get();
            rd.getAppointments().add(app);
            recordRepository.save(rd);
            result.add(rd);
        }
        return result;
    }

    public Record recordCancel(Principal principal, CompositeRecordIdAppointment compositeRecordIdAppointment) {
        Appointment app = compositeRecordIdAppointment.getAppointment();
        if (app.getId() == null) {
            app.setCreationDate(LocalDateTime.now());
            app.setCreator(getUser(principal));
            app.setNote(PLANNER_CANCEL_STR + app.getNote());
        }
        app.setStatus(AppointmentStatus.CANCELED_BY_PLANNER);
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

    public Claim updateRoute(Claim claim) {
        routeTaskRepository.deleteByClaimId(claim.getId());
        Claim tempClaim = claimRepository.findById(claim.getId()).get();
        List<RouteTask> tempRouteTasks = routeTaskRepository.saveAll(claim.getRouteTasks());
        tempClaim.setRouteTasks(tempRouteTasks);
        claimRepository.save(tempClaim);
        return new Claim(tempClaim);
    }

    public Record updateTime(Record record) {
        Record tempRecord = recordRepository.findById(record.getId()).get();
        tempRecord.setEntranceDate(record.getEntranceDate());
        tempRecord.setStartDate(record.getStartDate());
        tempRecord.setEndDate(record.getEndDate());
        return recordRepository.save(tempRecord);
    }

    public List<CarBoss> findCarBossesByDepartment(Principal principal) {
        return carBossRepository.findAll();
    }

    public CarBoss saveCarBoss(Principal principal, CarBoss carBoss) {
        return carBossRepository.save(carBoss);
    }

    public void deleteCarBoss(CarBoss carBoss) {
        carBossRepository.delete(carBoss);
    }

    public Claim saveClaim(Principal principal, Claim claim) {
        claim.setCreationDate(LocalDateTime.now());
        claim.setCreator(getUser(principal));
        claim.setAffirmator(getUser(principal));
        claim.setAffirmationDate(LocalDateTime.now());
        return claimRepository.save(claim);
    }

    public List<CompositeOtsInfo> getOtsInfo() {
        List<CompositeOtsInfo> result = new ArrayList<>();
        transportDepRepository.findOtsInfo().forEach(u -> result.add(new CompositeOtsInfo(u, vehicleModelRepository.findVehicleModelInfos(u.getId()))));
        return result;
    }

    public List<Vehicle> getAllVehicles(Principal principal) {
        return vehicleRepository.findAll();
    }
    
    public List<Driver> getAllDrivers(Principal principal) {
        return driverRepository.findAll();
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

    public List<CompositeModelTransportDep> getTransportDepModels() {
        return transportDepRepository.findModels();
    }

    public String getUserName(Principal principal) {
        final char dm = (char) 34;
        AppUser user = getUser(principal);
        String un = "{" + dm + "username" + dm + ":" + dm + user.getUsername() + dm + "}";
        return un;
    }
    
    public List<AffirmedClaim> getAffirmedClaimsAll1() {
        return claimRepository.findAffirmedClaims();
    }
}
