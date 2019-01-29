package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentInfo;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.AppointmentInfoRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.ivc.transportation.utils.EntitiesUtils;
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

    private List<CompositeClaimRecord> getCompositeClaimRecords(Department department) {
        List<Record> recordList = new ArrayList<Record>();
        claimRepository.findByDepartment(department).forEach(u -> recordList.addAll(u.getRecords()));
        List<CompositeClaimRecord> result = new ArrayList<CompositeClaimRecord>();
        recordList.forEach(u -> result.add(new CompositeClaimRecord(claimRepository.findByRecords(u), u)));
        return result;
    }

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsAll() {
        List<CompositeDepartmentClaimRecords> result = new ArrayList<CompositeDepartmentClaimRecords>();
        departmentRepository.findAll().forEach(u -> result.add(new CompositeDepartmentClaimRecords(u)));
        result.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecords(u.getDepartment())));
        return result;

    }

    public Record createAppointment(Principal principal, CompositeRecordIdAppointment cria) {
        Appointment app = cria.getAppointment();
        app.setCreationDate(LocalDateTime.now());
        app.setCreator(getUser(principal));
        app.setNote("Заявка передана в транспортный отдел");
        app.setStatus(EntitiesUtils.AppointmentStatus.IN_PROGRESS);
        
        appointmentInfoRepository.save(new AppointmentInfo(app.getCreationDate(), app.getStatus(), app.getNote(), app));

        Record rd = recordRepository.findById(cria.getRecordId()).get();
        rd.getAppointments().add(app);
        recordRepository.save(rd);
        return rd;
    }
    
    private AppUser getUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername());
        }
        return null;
    }

}
