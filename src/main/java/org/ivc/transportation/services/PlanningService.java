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

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsAll() {
        List<CompositeDepartmentClaimRecords> cl = new ArrayList<CompositeDepartmentClaimRecords>();
        departmentRepository.findAll().forEach(u -> cl.add(new CompositeDepartmentClaimRecords(u)));
        cl.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecords(u.getDepartment())));
        return cl;

    }

    private List<CompositeClaimRecord> getCompositeClaimRecords(Department department) {
        List<Record> rl = new ArrayList<Record>();
        claimRepository.findByDepartment(department).forEach(u -> rl.addAll(u.getRecords()));
        List<CompositeClaimRecord> cl = new ArrayList<CompositeClaimRecord>();
        rl.forEach(u -> cl.add(new CompositeClaimRecord(findByRecordsWithoutRecordsAndDepartment(u), u)));
        return cl;
    }

    private Claim findByRecordsWithoutRecordsAndDepartment(Record record) {
        Claim cl = claimRepository.findByRecords(record);
        cl.setRecords(null);
        cl.setDepartment(null);
        return cl;
    }

 

}
