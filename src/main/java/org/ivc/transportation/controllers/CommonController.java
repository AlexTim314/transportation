package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.repositories.DTORepository;
import org.ivc.transportation.services.CommonService;
import org.ivc.transportation.utils.RecordDTO;
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
    private DTORepository repository;

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

    @GetMapping("/test")
    public List<RecordDTO> test() {
        return repository.getDTORecords();
    }

}
