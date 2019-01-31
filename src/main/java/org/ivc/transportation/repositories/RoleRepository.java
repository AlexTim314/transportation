package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByRoleName(String roleName);
    
}
