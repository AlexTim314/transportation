package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService depServ;

    @GetMapping("/departments")
    public Collection<Department> getDepartments() {
        return depServ.listDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable long id) {
        Optional<Department> dep = depServ.getDepartmentById(id);
        return dep.get();
    }

    @DeleteMapping("/departments/{id}")
    public void delDepartment(@PathVariable long id) {
        depServ.removeDepartment(id);
    }

    @PostMapping("/departments")
    public void addDepartment(@RequestBody Department department) {
        depServ.addDepartment(department);
    }

    @PutMapping("/departments/{id}")
    public void updateStudent(@RequestBody Department dep, @PathVariable long id) {
        depServ.updateDepartment(dep, id);
    }
}
