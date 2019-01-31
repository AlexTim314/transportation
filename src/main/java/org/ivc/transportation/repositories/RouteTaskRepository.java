package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.RouteTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("routeTaskRepository")
public interface RouteTaskRepository extends JpaRepository<RouteTask, Long> {

    @Modifying
    @Query(value = "delete from route_task where claim_id = :claim_id", nativeQuery = true)
    void deleteByClaimId(@Param("claim_id") Long claimId);

    @Modifying
    @Query(value = "delete from route_task where template_id = :template_id", nativeQuery = true)
    void deleteByTemplateId(@Param("template_id") Long templateId);

}
