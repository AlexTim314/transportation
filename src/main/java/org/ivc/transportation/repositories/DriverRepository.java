package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.utils.tdDriverInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findByTransportDep(TransportDep transportDep);

    @Query(value = "select * from driver where status = 0 and transport_dep_id = :transport_dep_id and id not in ("
            + "select appointment.driver_id from appointment, record where "
            + "appointment.status = 1 and ("
            + "(record.start_date between :date_start and :date_end) or "
            + "(record.end_date between :date_start and :date_end) or "
            + "(record.start_date > :date_start and record.end_date < :date_end)"
            + ") and "
            + "record.id = appointment.record_id)", nativeQuery = true)
    List<Driver> findVacantByTransportDepId(@Param("transport_dep_id") Long transportDepId,
            @Param("date_start") LocalDateTime dateStart,
            @Param("date_end") LocalDateTime dateEnd);

    void deleteByIdIn(List<Long> ids);

//    public void findDriverInfo(Long id);

    @Query(value = "select (select count(id) as otgul from driver where transport_dep_id = :transportDepId and status = 3), "
            + "(select count(id) as bolnyak from driver where transport_dep_id = :transportDepId and status = 2), "
            + "(select count(id) as otpusk from driver where transport_dep_id = :transportDepId and status = 1), "
            + "(select count(id) as vsego from driver where transport_dep_id = :transportDepId)", nativeQuery = true)
    public List<tdDriverInfo> findTdDriverInfo(@Param("transportDepId") Long transportDepId);

}
