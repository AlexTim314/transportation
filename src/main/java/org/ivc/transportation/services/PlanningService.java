package org.ivc.transportation.services;

import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private AppointmentRepository appointmentRepository;

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
}
