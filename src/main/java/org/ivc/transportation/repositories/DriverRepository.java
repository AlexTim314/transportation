package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
