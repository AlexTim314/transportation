package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.RouteTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("routeTemplateRepository")
public interface RouteTemplateRepository extends JpaRepository<RouteTemplate, Long> {
    
    List<RouteTemplate> findByDepartmentOrDepartmentIsNull(Department department);
    void deleteByIdIn(List<Long> routeTemplateIds);

}
