package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long> {

Department findByShortname(String shortname);
    

}
