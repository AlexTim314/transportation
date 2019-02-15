package org.ivc.transportation.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByTransportDep(TransportDep transportDep);

    void deleteByIdIn(List<Long> ids);

    @Query(value = "select * from vehicle where status = 0 and model_id = :model_id and transport_dep_id = :transport_dep_id and id not in ("
            + "select appointment.vehicle_id from appointment, record where "
            + "appointment.status = 1 and ("
            + "(record.start_date between :date_start and :date_end) or "
            + "(record.end_date between :date_start and :date_end) or "
            + "(record.start_date > :date_start and record.end_date < :date_end)) and "
            + "record.id = appointment.record_id)", nativeQuery = true)
    List<Vehicle> findVacantByTransportDepIdWithModel(@Param("transport_dep_id") Long transportDepId,
            @Param("model_id") Long modelId,
            @Param("date_start") ZonedDateTime dateStart,
            @Param("date_end") ZonedDateTime dateEnd);

    @Query(value = "select * from vehicle where status = 0 and transport_dep_id = :transport_dep_id and id not in ("
            + "select appointment.vehicle_id from appointment, record where "
            + "appointment.status = 1 and ("
            + "(record.start_date between :date_start and :date_end) or "
            + "(record.end_date between :date_start and :date_end) or "
            + "(record.start_date > :date_start and record.end_date < :date_end)) and "
            + "record.id = appointment.record_id)", nativeQuery = true)
    List<Vehicle> findVacantByTransportDepIdWithoutModel(@Param("transport_dep_id") Long transportDepId,
            @Param("date_start") ZonedDateTime dateStart,
            @Param("date_end") ZonedDateTime dateEnd);

    @Query(value = "select * from vehicle where status = :status order by note", nativeQuery = true)
    public List<Vehicle> findVehiclesForPlan(@Param("status") int status);
}
