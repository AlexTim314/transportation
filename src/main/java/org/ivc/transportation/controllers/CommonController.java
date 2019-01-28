package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.RouteTask;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.services.CommonService;
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

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return commonService.findAllDepartments();
    }

    @GetMapping("/department")
    public Department getDepartment(Principal principal) {
        return commonService.findDepartmentByUser(principal);
    }

    @GetMapping("/transportDeps")
    public List<TransportDep> getTransportDeps() {
        return commonService.findAllTransportDeps();
    }

    @GetMapping("/vehicleTypes")
    public List<VehicleType> getVehicleTypes() {
        return commonService.findAllVehicleTypes();
    }

    @GetMapping("/places")
    public List<Place> getAllPlaces() {
        return commonService.findAllPlaces();
    }

    @Data
    public class Composite {
    private Claim claim;
    private Record record;
    public Composite(Claim claim, Record record) {
        this.claim = claim;
        this.record = record;
    }
}
    
    Claim findByRecordsWithoutRecords(Record record) {
        Claim cl = cr.findByRecords(record);
        cl.setRecords(null);
        return cl;
    }
    
    @GetMapping("/test")
    public List<Composite> test() {
        List<Composite> cl = new ArrayList<Composite>();
        rr.findAll().forEach(u -> cl.add(new Composite(findByRecordsWithoutRecords(u),u)));
        return cl;
    }

}
