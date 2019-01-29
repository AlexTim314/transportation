package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleInfoRepository")
public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, Long> {

    @Modifying
    @Query(value = "delete from vehicle_info where vehicle_id = :vehicle_id", nativeQuery = true)
    void deleteByVehicleId(@Param("vehicle_id") Long vehicleId);
}
