package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByTransportDep(TransportDep transportDep);
    
    @Query(value = "select * from driver where transport_dep_id = :transport_dep_id and vacant = true", nativeQuery = true)
    List<Driver> findVacantByTransportDepId(@Param("transport_dep_id") Long transportDepId);

    void deleteByIdIn(List<Long> ids);

}
