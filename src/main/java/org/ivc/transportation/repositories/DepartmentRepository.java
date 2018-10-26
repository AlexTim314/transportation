/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

}
