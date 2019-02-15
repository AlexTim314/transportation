package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.utils.VehicleModelInfo;
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
    
    @Query(value = "select vehicle_model.model_name as model, "
            + "vehicle_type.specialization as specialization, "
            + "count(vehicle_model.model_name) as count "
            + "from vehicle_model, vehicle_type, vehicle "
            + "where vehicle.transport_dep_id = :transport_dep_id and vehicle_type.id = vehicle_model.vehicle_type_id and vehicle_model.id = vehicle.model_id "
            + "group by vehicle_model.model_name, vehicle_type.specialization", nativeQuery = true)
    public List<VehicleModelInfo> findVehicleModelInfos(@Param("transport_dep_id") Long transportDepId);

}
