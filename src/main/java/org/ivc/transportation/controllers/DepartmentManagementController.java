/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.List;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.services.DepartmentService;
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
 * @author user
 */

@RestController
public class DepartmentManagementController {
    
    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping("/management/departments")
    public List<Department> getAllDepartments() {
        return departmentService.findAllDepartments();
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
    
}
