package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Sokolov Slava
 */
@RepositoryRestResource
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
