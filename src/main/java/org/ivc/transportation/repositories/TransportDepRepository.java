package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.TransportDep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("transportDepRepository")
public interface TransportDepRepository extends JpaRepository<TransportDep, Long>{

}
