package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Refueling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("refuelingRepository")
public interface RefuelingRepository extends JpaRepository<Refueling, Long> {

}
