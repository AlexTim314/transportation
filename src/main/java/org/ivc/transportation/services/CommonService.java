package org.ivc.transportation.services;

import java.util.List;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
@Transactional
public class CommonService {

    @Autowired
    private TransportDepRepository transportDepRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }
    
    public List<TransportDep> findAllTransportDeps(){
        return transportDepRepository.findAll();
    }
    
    public Department getDepartmentByName(String shortname) {
        return departmentRepository.findByShortname(shortname);
    }
    
}
