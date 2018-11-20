package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.exceptions.NonExistingDepartmentException;
import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClaimService claimService;
    


    @GetMapping("/departments")   
    public Collection<Department>getAllDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable long id) {
        Optional<Department> optDep = departmentService.getDepartmentById(id);
        return optDep.orElseThrow(() -> {

            String ruMessage = "Транспортный отдел с запрошенным номером не"
                    + " найден в базе. ID = " + id + ". ";
            String engMessage = "Error in " + DepartmentController.class
                    + " when try get Department by id = " + id + ".";

            return new NonExistingDepartmentException(ruMessage
                    + System.lineSeparator() + engMessage);
        });
    }

    @DeleteMapping("/departments/delete/{id}")
    public Collection<Department> delDepartment(@PathVariable long id) {
        departmentService.removeDepartment(id);
        return departmentService.getDepartments();
    }

    @PostMapping("/departments/create")
    public Collection<Department> addDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
        return departmentService.getDepartments();
    }

    @PutMapping("/departments/update")
    public Collection<Department> updateDepartment(@RequestBody Department dep, @PathVariable long id) {
        departmentService.updateDepartment(dep, id);
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}/claims")
    public Collection<Claim> getClaims(@PathVariable Long id) {
        return claimService.getClaimsByDepartment(id);
    }
    
    @GetMapping("/departments/{idDepartment}/claims/{idClaim}")
    public Collection<Record> getRecordsByClaim(@PathVariable Long idClaim, @PathVariable Long idDepartment) {
        return claimService.getRecordsByClaim(idClaim);
    }
    
     @PostMapping("/departments/{id}/claims/create")
        public Collection<Claim> addClaim(@RequestBody Claim claim, @PathVariable Long id) {
        claimService.addClaim(claim);
        return claimService.getClaimsByDepartment(id);
    }

}
