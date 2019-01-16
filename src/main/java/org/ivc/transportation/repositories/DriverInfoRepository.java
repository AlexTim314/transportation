package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.DriverInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("driverInfoRepository")
public interface DriverInfoRepository extends JpaRepository<DriverInfo, Long> {

}
