/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Override
    @Transactional
    public void saveDepartment(Department d) {
        this.departmentRepository.save(d);
    }
    
    @Override
    @Transactional
    public void updateDepartment(Department d, Long id) {
        d.setId(id);
        departmentRepository.save(d);
    }       
    
    @Override
    @Transactional
    public Collection<Department> getDepartments() {
        return departmentRepository.findAll();
    }
    
    @Override
    @Transactional
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }
    
    @Override
    @Transactional
    public void removeDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
