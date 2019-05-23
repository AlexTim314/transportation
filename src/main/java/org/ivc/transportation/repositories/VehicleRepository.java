package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.utils.VehicleForPlan;
import org.ivc.transportation.utils.VehicleLastDep;
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
            @Param("date_start") LocalDateTime dateStart,
            @Param("date_end") LocalDateTime dateEnd);

    @Query(value = "select * from vehicle where status = 0 and transport_dep_id = :transport_dep_id and id not in ("
            + "select appointment.vehicle_id from appointment, record where "
            + "appointment.status = 1 and ("
            + "(record.start_date between :date_start and :date_end) or "
            + "(record.end_date between :date_start and :date_end) or "
            + "(record.start_date > :date_start and record.end_date < :date_end)) and "
            + "record.id = appointment.record_id)", nativeQuery = true)
    List<Vehicle> findVacantByTransportDepIdWithoutModel(@Param("transport_dep_id") Long transportDepId,
            @Param("date_start") LocalDateTime dateStart,
            @Param("date_end") LocalDateTime dateEnd);

    @Query(value = "select * from vehicle where status = :status order by note", nativeQuery = true)
    public List<Vehicle> findVehiclesForPlan(@Param("status") int status);

    @Query(value = "select vehicle.id as vehicleid, "
            + "vehicle.number as number, "
            + "transport_dep.shortname as otsname, "
            + "vehicle_model.model_name as modelname, "
            + "department.id as departmentid "
            + "from vehicle, department, appointment, record, claim, transport_dep, vehicle_model "
            + "where claim.department_id = department.id and "
            + "record.claim_id = claim.id and "
            + "appointment.id = (select max(id) from appointment where record_id = record.id) and "
            + "vehicle.id = appointment.vehicle_id and "
            + "transport_dep.id = vehicle.transport_dep_id and "
            + "vehicle_model.id = vehicle.model_id "
            + "group by departmentid, vehicleid, transport_dep.id, vehicle_model.id "
            + "order by departmentid", nativeQuery = true)
    List<VehicleLastDep> findVehicleLastDep();

    @Query(value = "select department.id as departmentid, "
            + "vehicle.id as vehicleid, "
            + "vehicle_model.model_name as modelname, "
            + "vehicle.number as number, "
            + "transport_dep.shortname as otsname, "
            + "claim.purpose as purpose, "
            + "car_boss.firstname as carrbossfirstname, car_boss.name as carrbossname, car_boss.surname as carrbosssurname, "
            + "string_agg(place.name, ', ' order by route_task.order_num) as route, "
            + "record.entrance_date as entrancedate, record.start_date as startdate, record.end_date as enddate, "
            + "driver.firstname as driverfirstname, driver.name as drivername, driver.surname as driversurname "
            + "from vehicle "
            + "left outer join appointment on appointment.vehicle_id = vehicle.id "
            + "left outer join record on record.id = appointment.record_id and record.start_date between :date_start and :date_end "
            + "left outer join claim on claim.id = record.claim_id "
            + "left outer join car_boss on car_boss.id = claim.car_boss_id "
            + "left outer join route_task on route_task.claim_id = claim.id "
            + "left outer join place on place.id = route_task.place_id "
            + "left outer join driver on driver.id = appointment.driver_id "
            + "left outer join department on department.id = claim.department_id, vehicle_model, transport_dep "
            + "where vehicle_model.id = vehicle.model_id and transport_dep.id = vehicle.transport_dep_id "
            + "group by departmentid, vehicleid, modelname, otsname, purpose, carrbossfirstname, carrbossname, carrbosssurname, entrancedate, startdate, enddate, driverfirstname, drivername, driversurname "
            + "order by departmentid, vehicleid", nativeQuery = true)
    public List<VehicleForPlan> findVehiclesForPlan(@Param("date_start") LocalDateTime dateStart, @Param("date_end") LocalDateTime dateEnd);

}
