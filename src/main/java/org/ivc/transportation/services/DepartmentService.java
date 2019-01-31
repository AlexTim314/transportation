package org.ivc.transportation.services;

import java.util.List;
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
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }
    
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
    
    public void deleteDepartment(Department department) {
        departmentRepository.delete(department);
    }
    
    
}
