package org.ivc.transportation.repositories;

import java.util.List;
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

    @Query(value = "SELECT \n"
            + "  route_task.*\n"
            + "FROM \n"
            + "  public.route_task, \n"
            + "  public.claim, \n"
            + "  public.appointment, \n"
            + "  public.place, \n"
            + "  public.record\n"
            + "WHERE \n"
            + "  route_task.place_id = place.id AND\n"
            + "  claim.id = route_task.claim_id AND\n"
            + "  claim.id = record.claim_id AND\n"
            + "  record.id = appointment.record_id AND\n"
            + "  appointment.id = :app_id", nativeQuery = true)
    public List<RouteTask> findRouteTasksForAppointment(@Param("app_id") Long appointmentId);

}
