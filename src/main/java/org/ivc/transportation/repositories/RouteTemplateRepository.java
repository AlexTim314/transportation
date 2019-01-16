package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.RouteTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("routeTemplateRepository")
public interface RouteTemplateRepository extends JpaRepository<RouteTemplate, Long> {

}
