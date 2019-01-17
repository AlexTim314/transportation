package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("mechanicRepository")
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

}
