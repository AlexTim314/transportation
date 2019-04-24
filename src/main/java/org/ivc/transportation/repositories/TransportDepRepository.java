package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.utils.CompositeModelTransportDep;
import org.ivc.transportation.utils.OtsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava
 */
@Repository("transportDepRepository")
public interface TransportDepRepository extends JpaRepository<TransportDep, Long> {

    @Query(value = "select "
            + "transport_dep.id as id, "
            + "transport_dep.shortname as name, "
            + "(select count(vehicle.id) from vehicle, vehicle_model, vehicle_type where vehicle.transport_dep_id = transport_dep.id and vehicle.status = 0 and vehicle.model_id = vehicle_model.id and vehicle_model.vehicle_type_id = vehicle_type.id and vehicle_type.specialization = 0) as type1count, "
            + "(select count(vehicle.id) from vehicle, vehicle_model, vehicle_type where vehicle.transport_dep_id = transport_dep.id and vehicle.status = 0 and vehicle.model_id = vehicle_model.id and vehicle_model.vehicle_type_id = vehicle_type.id and vehicle_type.specialization = 1) as type2count, "
            + "(select count(vehicle.id) from vehicle, vehicle_model, vehicle_type where vehicle.transport_dep_id = transport_dep.id and vehicle.status = 0 and vehicle.model_id = vehicle_model.id and vehicle_model.vehicle_type_id = vehicle_type.id and vehicle_type.specialization = 2) as type3count, "
            + "(select count(vehicle.id) from vehicle, vehicle_model, vehicle_type where vehicle.transport_dep_id = transport_dep.id and vehicle.status = 0 and vehicle.model_id = vehicle_model.id and vehicle_model.vehicle_type_id = vehicle_type.id and vehicle_type.specialization = 3) as type4count, "
            + "(select count(driver.id) from driver where driver.transport_dep_id = transport_dep.id) as drivercount "
            + "from "
            + "vehicle, "
            + "transport_dep "
            + "group by transport_dep.id order by transport_dep.shortname", nativeQuery = true)
    public List<OtsInfo> findOtsInfo();

    @Query(value = "select vehicle_model.id as vehiclemodelid, "
            + "vehicle_model.model_name as modelname, "
            + "transport_dep.id as transportdepid, "
            + "transport_dep.shortname as shortname, "
            + "vehicle_type.specialization as vehiclespecialization "
            + "from vehicle_model, transport_dep, vehicle_type, vehicle "
            + "where vehicle.transport_dep_id = transport_dep.id and "
            + "vehicle.model_id = vehicle_model.id and "
            + "vehicle_model.vehicle_type_id = vehicle_type.id "
            + "group by vehicle_model.id, transport_dep.id, vehicle_type.specialization "
            + "order by vehicle_model.id", nativeQuery = true)
    public List<CompositeModelTransportDep> findModels();
    
}


