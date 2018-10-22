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

@RestController
public class DepartmentController {
    
    private static final String url = "/departments";

    @Autowired
    private DepartmentService localServ;

    @GetMapping(url)
    public Collection<Department> getDepartments() {
        return localServ.listDepartments();
    }

    @GetMapping(url + "/{id}")
    public Department getDepartment(@PathVariable long id) {
        Optional<Department> dep = localServ.getDepartmentById(id);
        return dep.get();
    }

    @DeleteMapping(url + "/{id}")
    public void delDepartment(@PathVariable long id) {
        localServ.removeDepartment(id);
    }

    @PostMapping(url)
    public void addDepartment(@RequestBody Department department) {
        localServ.addDepartment(department);
    }

    @PutMapping(url + "/{id}")
    public void updateDepartment(@RequestBody Department dep, @PathVariable long id) {
        localServ.updateDepartment(dep, id);
    }
}
