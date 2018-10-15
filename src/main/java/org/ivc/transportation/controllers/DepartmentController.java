package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.stream.Collectors;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;

    @GetMapping("/combat-departments")
    public Collection<Department> getCombatDepartments() {
        return repository.findAll().stream()
                .filter(this::isCombat)
                .collect(Collectors.toList());
    }

    @GetMapping("/departments")
    public Collection<Department> getDepartments() {
        return repository.findAll();
    }

    private boolean isCombat(Department dep) {
        return dep.getName().equals("ЦИ-1")
                || dep.getName().equals("ЦИ-2")
                || dep.getName().equals("ЦИ-4")
                || dep.getName().equals("ЦИ-7");
    }

}
