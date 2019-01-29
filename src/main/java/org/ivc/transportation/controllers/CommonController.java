package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.services.CommonService;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private RecordRepository rr;

    @Autowired
    private ClaimRepository cr;

    @Autowired
    private DepartmentRepository dr;

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return commonService.findAllDepartments();
    }

    @GetMapping("/department")
    public Department getDepartment(Principal principal) {
        return commonService.findDepartmentByUser(principal);
    }

    @GetMapping("/transportDep")
    public TransportDep getTransportDep(Principal principal) {
        return commonService.findTransportDepByUser(principal);
    }

    @GetMapping("/transportDeps")
    public List<TransportDep> getTransportDeps() {
        return commonService.findAllTransportDeps();
    }

    @GetMapping("/vehicleTypes")
    public List<VehicleType> getVehicleTypes() {
        return commonService.findAllVehicleTypes();
    }

    @GetMapping("/vehicleModels")
    public List<VehicleModel> getVehicleModels() {
        return commonService.findAllVehicleModels();
    }

    @GetMapping("/places")
    public List<Place> getAllPlaces() {
        return commonService.findAllPlaces();
    }

    @GetMapping("/test")
    public List<CompositeDepartmentClaimRecords> test() {
        List<CompositeDepartmentClaimRecords> cl = new ArrayList<CompositeDepartmentClaimRecords>();
        dr.findAll().forEach(u -> cl.add(new CompositeDepartmentClaimRecords(u)));
        cl.forEach(u -> u.setCompositeClaimRecords(getCompositeClaimRecords(u.getDepartment())));
        return cl;
    }
    
    private Claim findByRecordsWithoutRecordsAndDepartment(Record record) {
        Claim cl = cr.findByRecords(record);
        cl.setRecords(null);
        cl.setDepartment(null);
        return cl;
    }

    private List<CompositeClaimRecord> getCompositeClaimRecords(Department department) {
        List<Record> rl = new ArrayList<Record>();
        cr.findByDepartment(department).forEach(u -> rl.addAll(u.getRecords()));
        List<CompositeClaimRecord> cl = new ArrayList<CompositeClaimRecord>();
        rl.forEach(u -> cl.add(new CompositeClaimRecord(findByRecordsWithoutRecordsAndDepartment(u), u)));
        return cl;
    }

}
