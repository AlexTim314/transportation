package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleModelRepository")
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    
    List<VehicleModel> findByVehicleTypeId(Long id);

}
