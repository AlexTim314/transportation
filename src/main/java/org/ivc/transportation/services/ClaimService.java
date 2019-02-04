package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
import org.ivc.transportation.repositories.RouteTaskRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.ivc.transportation.utils.EntitiesUtils;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import static org.ivc.transportation.utils.EntitiesUtils.USER_CANCEL_STR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nodata
 */
@Service
@Transactional
public class ClaimService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentInfoRepository appointmentInfoRepository;

    @Autowired
    private RouteTaskRepository routeTaskRepository;

    public List<Claim> findNewClaimsByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndAffirmationDateIsNullAndTemplateNameIsNull(department);
        }
        return null;
    }

    public List<Claim> findClaimTemplatesByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndTemplateNameIsNotNull(department);
        }
        return null;
    }

    public List<Claim> findAffirmedClaimsByDepartmentTimeFilter(Principal principal, ZonedDateTime dStart, ZonedDateTime dEnd) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findAffirmedClaimsByDepartmentTimeFilter(department.getId(), dStart, dEnd);
        }
        return null;
    }

    public List<Claim> findAffirmedClaimsByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndAffirmationDateIsNotNullAndActualIsTrue(department);
        }
        return null;
    }

    public Claim saveClaim(Principal principal, Claim claim) {
        if (claim.getId() == null) {
            claim.setCreationDate(LocalDateTime.now());
        } else {
            recordRepository.deleteByClaimId(claim.getId());
            routeTaskRepository.deleteByClaimId(claim.getId());
        }
        claim.setCreator(getUser(principal));
        claim.setDepartment(getDepartment(principal));
        return claimRepository.save(claim);
    }

    public void affirmClaims(Principal principal, List<Long> claimIds) {
        AppUser affirmator = getUser(principal);
        claimIds.forEach(id -> {
            Claim claim = claimRepository.findById(id).get();
            claim.setAffirmator(affirmator);
            claim.setAffirmationDate(LocalDateTime.now());
            claimRepository.save(claim);
        });
    }

    public Record recordCancel(Principal principal, CompositeRecordIdAppointment compositeRecordIdAppointment) {
        System.out.println(compositeRecordIdAppointment);
        Appointment app = compositeRecordIdAppointment.getAppointment();
        if (app.getId() == null) {
            app.setCreationDate(LocalDateTime.now());
            app.setCreator(getUser(principal));
            app.setNote(USER_CANCEL_STR + app.getNote());
        }
        app.setStatus(AppointmentStatus.CANCELED_BY_USER);
        app = appointmentRepository.save(app);
        appointmentInfoRepository.save(new AppointmentInfo(LocalDateTime.now(), app.getStatus(), app.getNote(), app));
        Record record = recordRepository.findById(compositeRecordIdAppointment.getRecordId()).get();
        record.getAppointments().add(app);
        return recordRepository.save(record);
    }

    public void deleteClaim(Claim claim) {
        claimRepository.deleteByIdAndAffirmationDateIsNull(claim.getId());
    }

    public void deleteClaims(List<Long> claimIds) {
        claimRepository.deleteByIdInAndAffirmationDateIsNull(claimIds);
    }

    public void deleteRecord(Long clmId, Long recId) {
        Claim claim = claimRepository.findById(clmId).get();
        Record record = recordRepository.findById(recId).get();
        claim.getRecords().remove(record);
        claimRepository.save(claim);
        recordRepository.delete(record);
    }

    private Department getDepartment(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getDepartment();
        }
        return null;
    }

    private AppUser getUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername());
        }
        return null;
    }

}
