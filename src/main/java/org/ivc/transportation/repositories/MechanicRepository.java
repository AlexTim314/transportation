package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Mechanic;
import org.ivc.transportation.entities.TransportDep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("mechanicRepository")
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

    List<Mechanic> findByTransportDep(TransportDep transportDep);

    void deleteByIdIn(List<Long> ids);

}
