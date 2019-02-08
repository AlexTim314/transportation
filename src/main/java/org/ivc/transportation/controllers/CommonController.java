package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Fuel;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.services.CommonService;
import org.ivc.transportation.utils.CompositeTransportDepVehicleModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class CommonController {

    public static final String[] COMMON_PATHES = {"/transportDep", "/department",
        "/departments", "/transportDeps", "/vehicleTypes", "/vehicleModels",
        "/places", "/fuels", "/vehicleModelsByTransportDep", "getNow"};

    @Autowired
    private CommonService commonService;

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

    @GetMapping("/vehicleModelsByTransportDep")
    public List<CompositeTransportDepVehicleModels> getVehicleModelsByTransportDep() {
        return commonService.findVehicleModelsByTransportDep();
    }

    @GetMapping("/getNow")
    public ZonedDateTime getNow() {
        return ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
    }

}
