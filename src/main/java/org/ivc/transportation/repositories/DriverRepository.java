package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nesterov Yuriy
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByTransportDepId(Long id);

    List<Driver> findByVacant(Boolean d);
    
}
