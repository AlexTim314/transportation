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
import org.ivc.transportation.utils.EntitiesUtils;
import static org.ivc.transportation.utils.EntitiesUtils.SUPERMANAGER_CANCEL_STR;
import static org.ivc.transportation.utils.EntitiesUtils.USER_CANCEL_STR;
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
public class SuperManagerService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;

    private Appointment prepareAppointment(Appointment appointment) {
        return appointment == null ? new Appointment() : appointment;
    }

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsAll(Principal principal) {
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        if (!departmentRepository.findDepartmentsBySuperManagerWithAffirmedClaims(getUser(principal).getId()).isEmpty()) {
            departmentRepository.findDepartmentsBySuperManagerWithAffirmedClaims(getUser(principal).getId()).forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
            result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecordsAll(u.getDepartment())));
        }
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsTimeFilter(ZonedDateTime dateStart, ZonedDateTime dateEnd, Principal principal) {
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        if (!departmentRepository.findDepartmentsBySuperManagerWithAffirmedClaims(getUser(principal).getId()).isEmpty()) {
            departmentRepository.findDepartmentsBySuperManagerWithAffirmedClaimsByTimeFilter(dateStart, dateEnd, getUser(principal).getId()).forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
            result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecordsTimeFilter(u.getDepartment(), dateStart, dateEnd)));
        }
        return result;
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

    private AppUser getUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername());
        }
        return null;
    }

    public void signRecords(Principal principal, List<Long> recIds) {
        AppUser affirmator = getUser(principal);
        recIds.forEach(id -> {
            Record record = recordRepository.findById(id).get();
            record.setAffirmator(affirmator);
            record.setAffirmationDate(LocalDateTime.now());
            recordRepository.save(record);
        });
    }

    public void cancelRecords(Principal principal, List<Long> recIds) {
        recIds.forEach(id -> {
            Record record = recordRepository.findById(id).get();
            Appointment app = appointmentRepository.getLastByRecordId(id);
            if (app == null) {
                app = appointmentRepository.save(new Appointment(LocalDateTime.now(), EntitiesUtils.AppointmentStatus.CANCELED_BY_SUPERMANAGER, SUPERMANAGER_CANCEL_STR, getUser(principal)));
                appointmentInfoRepository.save(new AppointmentInfo(LocalDateTime.now(), app.getStatus(), app.getNote(), app, getUser(principal)));
                record.getAppointments().add(app);
            } else {
                app.setNote(SUPERMANAGER_CANCEL_STR + app.getNote());
                app.setStatus(EntitiesUtils.AppointmentStatus.CANCELED_BY_SUPERMANAGER);
                app = appointmentRepository.save(app);
                appointmentInfoRepository.save(new AppointmentInfo(LocalDateTime.now(), app.getStatus(), app.getNote(), app, getUser(principal)));
                record.getAppointments().add(app);
            }
            recordRepository.save(record);
        });
    }
}
