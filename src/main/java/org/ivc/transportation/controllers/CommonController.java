package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Fuel;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
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

    @Autowired
    private DepartmentRepository dr;

    @GetMapping("/transportDep")
    public TransportDep getTransportDep(Principal principal) {
        return commonService.findTransportDepByUser(principal);
    }

    @GetMapping("/department")
    public Department getDepartment(Principal principal) {
        return commonService.findDepartmentByUser(principal);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        return commonService.findAllDepartments();
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

    @GetMapping("/fuels")
    public List<Fuel> getAllFuels() {
        return commonService.findAllFuels();
    }

}
