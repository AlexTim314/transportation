package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleModelRepository")
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

}
