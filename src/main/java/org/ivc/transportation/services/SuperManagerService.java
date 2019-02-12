package org.ivc.transportation.services;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.utils.CompositeClaimRecord;
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

    private Appointment prepareAppointment(Appointment appointment) {
        return appointment == null ? new Appointment() : appointment;
    }

    public List<CompositeClaimRecord> getCompositeClaimRecordsAll(Principal principal) {
        List<Department> departments = departmentRepository.findDepartmentsBySuperManagerId(getUser(principal).getId());
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        if (!departments.isEmpty()) {
            for (int i = 0; i < departments.size(); i++) {
                recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullAndActualIsTrue(departments.get(i).getId())
                        .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                        prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
            }
        }
        return result;
    }

    public List<CompositeClaimRecord> getCompositeClaimRecordsTimeFilter(Principal principal, ZonedDateTime dateStart, ZonedDateTime dateEnd) {
        List<Department> departments = departmentRepository.findDepartmentsBySuperManagerId(getUser(principal).getId());
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        if (!departments.isEmpty()) {
            for (int i = 0; i < departments.size(); i++) {
                recordRepository.findByDepartmentIdAndAffirmationDateIsNotNullAndActualIsTrueTimeFilter(departments.get(i).getId(), dateStart, dateEnd)
                        .forEach(u -> result.add(new CompositeClaimRecord(new Claim(claimRepository.findByRecordId(u.getId())), u,
                        prepareAppointment(appointmentRepository.getLastByRecordId(u.getId())))));
            }
        }
        return result;
    }

    private AppUser getUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername());
        }
        return null;
    }
}
