package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentInfo;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.AppointmentInfoRepository;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
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
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    private Appointment prepareAppointment(Appointment appointment) {
        return appointment == null ? new Appointment() : appointment;
    }
    
    private List<CompositeClaimRecord> getCompositeClaimRecordsAll(Department department) {
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullAndActualIsTrue(department.getId())
                .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
        return result;
    }

    private List<CompositeClaimRecord> getCompositeClaimRecordsTimeFilter(Department department, ZonedDateTime dateStart, ZonedDateTime dateEnd) {
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

    private List<CompositeClaimRecord> getCompositeClaimRecordsTimeFilterPlanned(Department department, ZonedDateTime dateStart, ZonedDateTime dateEnd) {
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullTimeFilterPlanned(department.getId(), dateStart, dateEnd)
                .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsAll() {
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        departmentRepository.findDepartmentsWithAffirmedClaims().forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
        result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecordsAll(u.getDepartment())));
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsTimeFilter(ZonedDateTime dateStart, ZonedDateTime dateEnd) {
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

    public List<CompositeDepartmentClaimRecords> getPlannedClaimsTimeFilter(ZonedDateTime dateStart, ZonedDateTime dateEnd) {
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
        System.out.println(compositeRecordIdAppointment);
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

}
