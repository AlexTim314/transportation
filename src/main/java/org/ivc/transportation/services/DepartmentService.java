/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Department;

/**
 *
 * @author user
 */
public interface DepartmentService {

    public void addDepartment(Department d);

    public void updateDepartment(Department d, Long id);

    public Collection<Department> listDepartments();

    public Optional getDepartmentById(long id);

    public void removeDepartment(long id);
}
