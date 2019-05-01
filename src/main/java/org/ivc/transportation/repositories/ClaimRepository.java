package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.utils.AffirmedClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("claimRepository")
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    Claim findByRecords(Record record);

    @Query(value = "select claim.* from claim, record where record.id = :record_id and claim.id = record.claim_id", nativeQuery = true)
    Claim findByRecordId(@Param("record_id") Long recordId);

    @Query(value = "select claim.* from claim, record, appointment where appointment.id = :appointment_id and record.id = appointment.record_id and claim.id = record.claim_id", nativeQuery = true)
    public Claim findClaimByAppointmentId(@Param("appointment_id") Long appointmentId);

    List<Claim> findByDepartmentAndAffirmationDateIsNullAndTemplateNameIsNull(Department department);

    List<Claim> findByDepartmentAndTemplateNameIsNotNull(Department department);

    List<Claim> findByDepartmentAndAffirmationDateIsNotNullAndActualIsTrue(Department department);

    @Query(value = "select claim.* from claim, record "
            + "where record.start_date between :start_date and :end_date and "
            + "claim.id = record.claim_id and "
            + "claim.department_id = :department_id and "
            + "claim.affirmation_date is not null and "
            + "claim.actual = true "
            + "group by claim.id", nativeQuery = true)
    List<Claim> findAffirmedClaimsByDepartmentTimeFilter(
            @Param("department_id") Long departmentId,
            @Param("start_date") LocalDateTime startDate,
            @Param("end_date") LocalDateTime endDate
    );

    @Query(value = "select claim.id as claimid, "
            + "claim.department_id as departmentid, "
            + "department.fullname as departmentfullname, "
            + "record.id as recordid, "
            + "appointment.id as appointmentid, "
            + "appointment.status as appointmentstatus, "
            + "appointment.note as appointmentnote, "
            + "claim.vehicle_type_id as vehicletypeid, "
            + "claim.purpose as claimpurpose, "
            + "vehicle_type.type_name as vehicletypename, "
            + "claim.specialization as claimspecialization, "
            + "string_agg(route_task.work_name || '(' || place.name || ')', ', ' order by route_task.order_num) as route, "
            + "record.start_date as startdate, "
            + "record.end_date as enddate, "
            + "record.entrance_date as entrancedate, "
            + "appointment.transport_dep_id as transportdepid, "
            + "appointment.driver_id as driverid, "
            + "appointment.vehicle_id as vehicleid, "
            + "appointment.vehicle_model_id as modelid, "
            + "appointment.creator_id as creatorid, "
            + "(select full_name as creatorname from app_user where id = appointment.creator_id), "
            + "appointment.modificator_id as modificatorid, "
            + "(select full_name as modificatorname from app_user where id = appointment.modificator_id) "
            + "from department, "
            + "claim, "
            + "vehicle_type, "
            + "route_task, "
            + "place, "
            + "record left outer join appointment on appointment.record_id = record.id and appointment.id = (select max(id) from appointment where record_id = record.id) "
            + "where "
            + "department.id = claim.department_id and "
            + "claim.actual = true and "
            + "claim.affirmation_date is not null and "
            + "vehicle_type.id = claim.vehicle_type_id and "
            + "record.claim_id = claim.id and "
            + "route_task.claim_id = claim.id and "
            + "place.id = route_task.place_id "
            + "group by departmentid, claimid, departmentfullname, recordid, appointmentid, appointmentstatus, vehicletypeid, vehicletypename "
            + "order by claim.department_id",
             nativeQuery = true)
    List<AffirmedClaim> findAffirmedClaims();

    void deleteByIdAndAffirmationDateIsNull(Long id);

    void deleteByIdInAndAffirmationDateIsNull(List<Long> claimIds);
}
