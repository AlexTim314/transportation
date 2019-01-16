package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
