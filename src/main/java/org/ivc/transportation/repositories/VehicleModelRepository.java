package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleModelRepository")
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    
    List<VehicleModel> findByVehicleTypeId(Long id);
    
    @Query(value = "select vehicle_model.* from vehicle_model, vehicle where "
            + "vehicle.transport_dep_id = :transport_dep_id and "
            + "vehicle_model.id = vehicle.model_id", nativeQuery = true)
    List<VehicleModel> findVehicleModelsByTransportDepId(@Param("transport_dep_id") Long transportDepId);

}
