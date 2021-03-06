package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleTypeRepository")
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

}
