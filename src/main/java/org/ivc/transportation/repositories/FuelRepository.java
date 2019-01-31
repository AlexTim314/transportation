package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("fuelRepository")
public interface FuelRepository extends JpaRepository<Fuel, Long> {

}
