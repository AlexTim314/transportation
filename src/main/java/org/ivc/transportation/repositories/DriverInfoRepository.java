package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.DriverInfo;
import org.ivc.transportation.utils.tdDriverInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("driverInfoRepository")
public interface DriverInfoRepository extends JpaRepository<DriverInfo, Long> {

    @Modifying
    @Query(value = "delete from driver_info where driver_id = :driver_id", nativeQuery = true)
    void deleteByDriverId(@Param("driver_id") Long driverId);

}
