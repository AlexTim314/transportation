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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    


    @Autowired
    private DepartmentService localServ;

    @GetMapping()
    public Collection<Department> getDepartments() {
        return localServ.listDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable long id) {
        Optional<Department> dep = localServ.getDepartmentById(id);
        return dep.get();
    }

    @DeleteMapping("/{id}")
    public void delDepartment(@PathVariable long id) {
        localServ.removeDepartment(id);
    }

    @PostMapping()
    public void addDepartment(@RequestBody Department department) {
        localServ.addDepartment(department);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@RequestBody Department dep, @PathVariable long id) {
        localServ.updateDepartment(dep, id);
    }
}
