package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.VehicleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleInfoRepository")
public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, Long> {

}
