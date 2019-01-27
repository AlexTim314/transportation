package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.RouteTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("routeTaskRepository")
public interface RouteTaskRepository extends JpaRepository<RouteTask, Long> {

    void deleteByIdIn(List<Long> routeTaskIds);

}
