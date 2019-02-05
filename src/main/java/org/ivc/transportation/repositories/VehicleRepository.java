package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByTransportDep(TransportDep transportDep);

    void deleteByIdIn(List<Long> ids);

    @Query(value = "select * from vehicle where transport_dep_id = :transport_dep_id and vehicle_model_id = :vehicle_model_id and vacant = true", nativeQuery = true)
    List<Vehicle> findVacantByTransportDepIdAndModelId(@Param("transport_dep_id") Long transportDepId, 
            @Param("vehicle_model_id") Long vehicleModelId);

}
