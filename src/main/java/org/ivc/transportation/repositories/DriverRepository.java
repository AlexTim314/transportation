package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByTransportDep(TransportDep transportDep);

    void deleteByIdIn(List<Long> ids);

}
