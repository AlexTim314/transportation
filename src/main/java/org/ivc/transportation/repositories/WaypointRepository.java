package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("waypointRepository")
public interface WaypointRepository extends JpaRepository<Waypoint, Long> {

}
