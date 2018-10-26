package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.exceptions.NonExistingDepartmentException;
import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @Autowired
    private ClaimService claimServ;

    @GetMapping()
    public Collection<Department> getDepartments() {
        return service.getDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable long id) {
        Optional<Department> optDep = service.getDepartmentById(id);
        return optDep.orElseThrow(() -> {
            System.out.println("Error in " + DepartmentController.class
                    + " when try get Department by id = " + id + ".");

            return new NonExistingDepartmentException();
        });
    }

    @DeleteMapping("/{id}")
    public void delDepartment(@PathVariable long id) {
        service.removeDepartment(id);
    }

    @PostMapping()
    public void addDepartment(@RequestBody Department department) {
        service.addDepartment(department);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@RequestBody Department dep, @PathVariable long id) {
        service.updateDepartment(dep, id);
    }

    @GetMapping("/{id}/claims")
    public Collection<Claim> getClaims(@PathVariable Long id) {
        return claimServ.getClaimsByDepartment(id);
    }

}
