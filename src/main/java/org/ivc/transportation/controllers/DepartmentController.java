package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.exceptions.NonExistingDepartmentException;
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
//@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public Collection<Department> getAllDepartments() {
        return departmentService.getDepartments();
    }

//TODO: сомнительная потребность в этом запросе, оценить
    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable long id) {
        Optional<Department> optDep = departmentService.getDepartmentById(id);
        return optDep.orElseThrow(() -> {

            String ruMessage = "Транспортный отдел с запрошенным номером не"
                    + " найден в базе. ID = " + id + ". ";
            String engMessage = "Error in " + DepartmentController.class
                    + " when try get Department by id = " + id + ".";

            return new NonExistingDepartmentException(ruMessage
                    + "<br>" + engMessage);
        });
    }

    @DeleteMapping("/departments/delete")
    public Collection<Department> deleteDepartment(@RequestBody Department department) {
        departmentService.removeDepartment(department.getId());
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

}
