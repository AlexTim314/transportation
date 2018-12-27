package org.ivc.transportation.controllers;

import java.util.List;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.services.DepartmentService;
import org.ivc.transportation.services.PlaceService;
import org.ivc.transportation.services.TransportDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class CommonController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TransportDepService transportDepService;

    @Autowired
    private PlaceService placeService;

    @GetMapping("/management/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/management/department_create")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PutMapping("/management/department_update")
    public Department updateDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @DeleteMapping("/management/department_delete")
    public ResponseEntity<String> deleteDepartment(@RequestBody Department department) {
        departmentService.deleteDepartment(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/management/transportDeps")
    public List<TransportDep> getAllTransportDeps() {
        return transportDepService.getAllTransportDeps();
    }

    @PostMapping("/management/transportDep_create")
    public TransportDep createTransportDep(@RequestBody TransportDep transportDep) {
        return transportDepService.saveTransportDep(transportDep);
    }

    @PutMapping("/management/transportDep_update")
    public TransportDep updateTransportDep(@RequestBody TransportDep transportDep) {
        return transportDepService.saveTransportDep(transportDep);
    }

    @DeleteMapping("/management/transportDep_delete")
    public ResponseEntity<String> deleteTransportDep(@RequestBody TransportDep transportDep) {
        transportDepService.deleteTransportDep(transportDep);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/management/places")
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @PostMapping("/management/place_create")
    public Place createPlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @PutMapping("/management/place_update")
    public Place updatePlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @DeleteMapping("/management/place_delete")
    public ResponseEntity<String> deletePlace(@RequestBody Place place) {
        placeService.deletePlace(place);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
