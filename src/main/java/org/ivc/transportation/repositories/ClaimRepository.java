package org.ivc.transportation.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.utils.AffirmedClaim;
import org.ivc.transportation.utils.AppointmentClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ivc.transportation.utils.ClaimRecord;

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
            + "claim.affirmator_id as affirmid, "
            + "(select full_name as affirmname from app_user where id = claim.affirmator_id), "
            + "appointment.transport_dep_id as transportdepid, "
            + "appointment.creation_date as crdate, "
            + "appointment.driver_id as driverid, "
            + "appointment.vehicle_id as vehicleid, "
            + "appointment.vehicle_model_id as modelid, "
            + "appointment.creator_id as creatorid, "
            + "(select full_name as creatorname from app_user where id = appointment.creator_id), "
            + "(select post as creatorpost from app_user where id = appointment.creator_id), "
            + "appointment.modificator_id as modifid, "
            + "(select full_name as modifname from app_user where id = appointment.modificator_id), "
            + "(select post as modifpost from app_user where id = appointment.modificator_id) "
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
//            + "route_task.claim_id = claim.id and "
            + "route_task.record_id = record.id and "
            + "place.id = route_task.place_id "
            + "group by departmentid, claimid, departmentfullname, recordid, appointmentid, appointmentstatus, vehicletypeid, vehicletypename "
            + "order by claim.department_id",
            nativeQuery = true)
    List<AffirmedClaim> findAffirmedClaims();

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
            + "claim.affirmator_id as affirmid, "
            + "(select full_name as affirmname from app_user where id = claim.affirmator_id), "
            + "appointment.transport_dep_id as transportdepid, "
            + "appointment.creation_date as crdate, "
            + "appointment.driver_id as driverid, "
            + "appointment.vehicle_id as vehicleid, "
            + "appointment.vehicle_model_id as modelid, "
            + "appointment.creator_id as creatorid, "
            + "(select full_name as creatorname from app_user where id = appointment.creator_id), "
            + "(select post as creatorpost from app_user where id = appointment.creator_id), "
            + "appointment.modificator_id as modifid, "
            + "(select full_name as modifname from app_user where id = appointment.modificator_id), "
            + "(select post as modifpost from app_user where id = appointment.modificator_id) "
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
            + "record.start_date between :date_start and :date_end and "
//            + "route_task.claim_id = claim.id and "
            + "route_task.record_id = record.id and "
            + "place.id = route_task.place_id "
            + "group by departmentid, claimid, departmentfullname, recordid, appointmentid, appointmentstatus, vehicletypeid, vehicletypename "
            + "order by claim.department_id",
            nativeQuery = true)
    List<AffirmedClaim> findAffirmedClaimsTimeFilter(
            @Param("date_start") LocalDateTime dateStart,
            @Param("date_end") LocalDateTime dateEnd);

    @Query(value = "select vehicle_type.id as vehicletypeid, "
            + "vehicle_type.specialization as vehicletypespecialization, "
            + "record.start_date as startdate, "
            + "record.entrance_date as entrancedate, "
            + "record.end_date as enddate, "
            //+ "string_agg(route_task.work_name || '(' || place.name || ')', ', ' order by route_task.order_num) as route, "
            + "string_agg(place.name, ', ' order by route_task.order_num) as route, "
            + "vehicle_model.id as vehiclemodelid, "
            + "vehicle.id as vehicleid, "
            + "claim.purpose as purpose, "
            + "appointment.driver_id as driverid, "
            + "department.shortname as depshortname, "
            + "record.car_boss_firstname as carbossfirstname, "
            + "record.car_boss_name as carbossname, "
            + "record.car_boss_surname as carbosssurname, "
            + "record.car_boss_contacts as carbossphone, "
//            + "car_boss.firstname as carbossfirstname, "
//            + "car_boss.name as carbossname, "
//            + "car_boss.surname as carbosssurname, "
//            + "car_boss.phone as carbossphone, "
            + "appointment.id as appointmentid, "
            + "appointment.status as appstatus, "
            + "appointment.note as appnote, "
            + "record.id as recordid, "
            + "claim.id as claimid "
            + "from "
            + "record, appointment, vehicle, vehicle_model, vehicle_type, claim, route_task, place, department "
            + "where "
            + "appointment.transport_dep_id = :transportDepId and record.affirmation_date is not null and record.id = appointment.record_id and "
            + "vehicle.id = appointment.vehicle_id and vehicle_model.id = vehicle.model_id and vehicle_type.id = vehicle_model.vehicle_type_id and "
            + "claim.id = record.claim_id and route_task.record_id = record.id and place.id = route_task.place_id and "
            + "appointment.id = (select max(id) from appointment where record_id = record.id) and department.id = claim.department_id "
            + "group by record.id, vehicle_type.id, vehicle_model.id, vehicle.id, "
            + "claim.purpose, appointment.driver_id, department.shortname, "
            + "record.car_boss_firstname, record.car_boss_name, record.car_boss_surname, record.car_boss_contacts, appointment.id, record.id, claim.id, appointment.status, appointment.note",
            nativeQuery = true)
    List<AppointmentClaim> findAppointmentClaims(
            @Param("transportDepId") Long transportDepId
    );

//    @Query(value = "select vehicle_type.id as vehicletypeid, "
//            + "vehicle_type.specialization as vehicletypespecialization, "
//            + "record.start_date as startdate, "
//            + "record.entrance_date as entrancedate, "
//            + "record.end_date as enddate, "
//            //+ "string_agg(route_task.work_name || '(' || place.name || ')', ', ' order by route_task.order_num) as route, "
//            + "string_agg(place.name, ', ' order by route_task.order_num) as route, "
//            + "vehicle_model.id as vehiclemodelid, "
//            + "vehicle.id as vehicleid, "
//            + "claim.purpose as purpose, "
//            + "appointment.driver_id as driverid, "
//            + "department.shortname as depshortname, "
//            + "car_boss.firstname as carbossfirstname, "
//            + "car_boss.name as carbossname, "
//            + "car_boss.surname as carbosssurname, "
//            + "car_boss.phone as carbossphone, "
//            + "appointment.id as appointmentid, "
//            + "appointment.status as appstatus, "
//            + "appointment.note as appnote, "
//            + "record.id as recordid, "
//            + "claim.id as claimid "
//            + "from "
//            + "record, appointment, vehicle, vehicle_model, vehicle_type, claim, route_task, place, department, car_boss "
//            + "where "
//            + "record.start_date between :date_start and :date_end and "
//            + "appointment.transport_dep_id = :transportDepId and record.affirmation_date is not null and record.id = appointment.record_id and "
//            + "vehicle.id = appointment.vehicle_id and vehicle_model.id = vehicle.model_id and vehicle_type.id = vehicle_model.vehicle_type_id and "
//            + "claim.id = record.claim_id and route_task.claim_id = claim.id and place.id = route_task.place_id and "
//            + "appointment.id = (select max(id) from appointment where record_id = record.id) and department.id = claim.department_id and car_boss.id = claim.car_boss_id "
//            + "group by record.id, vehicle_type.id, vehicle_model.id, vehicle.id, "
//            + "claim.purpose, appointment.driver_id, department.shortname, "
//            + "car_boss.firstname, car_boss.name, car_boss.surname, car_boss.phone, appointment.id, record.id, claim.id, appointment.status, appointment.note",
//            nativeQuery = true)
//    List<AppointmentClaim> findAppointmentClaimsTimeFilter(
//            @Param("transportDepId") Long transportDepId,
//            @Param("date_start") LocalDateTime dateStart,
//            @Param("date_end") LocalDateTime dateEnd
//    );
    void deleteByIdAndAffirmationDateIsNull(Long id);

    void deleteByIdInAndAffirmationDateIsNull(List<Long> claimIds);

    @Query(value = "SELECT claim.id AS claim_id, \n"
            + "claim.purpose AS purpose, \n"
            + "claim.specialization AS specialization, \n"
            + "vehicle_type.type_name AS veh_type, \n"
            + "string_agg(place.name, ', ' ORDER BY route_task.order_num) AS route, \n"
            + "app_user.full_name AS affirmator, \n"
            + "record.id AS record_id, \n"
            + "record.start_date AS start_date, \n"
            + "record.entrance_date AS entrance_date, \n"
            + "record.end_date AS end_date, \n"
            + "record.car_boss_firstname AS carbossfirstname, \n"
            + "record.car_boss_name AS carbossname, \n"
            + "record.car_boss_surname AS carbosssurname, \n"
            + "record.car_boss_contacts AS carbossphone, \n"
            + "record.car_boss_post AS carbosspost, \n"
          //  + "car_boss.id AS boss_id, \n"
            + "department.id AS dep_id, \n"
            + "appointment.id AS appointment_id, \n"
            + "appointment.note AS appointment_note, \n"
            + "vehicle_model.id AS veh_model_id, \n"
            + "transport_dep.id AS ots_id, \n"
            + "vehicle.id AS veh_id, \n"
            + "driver.id AS driver_id, \n"
            + "appointment.status AS status\n"
            + "FROM public.claim \n"
            + "LEFT OUTER JOIN record ON record.claim_id = claim.id \n"
            + "LEFT OUTER JOIN route_task ON route_task.record_id = record.id \n"
            + "LEFT JOIN vehicle_type ON claim.vehicle_type_id = vehicle_type.id \n"
            + "LEFT JOIN place ON route_task.place_id = place.id \n"
            + "LEFT JOIN app_user ON record.affirmator_id = app_user.id \n"
//            + "LEFT JOIN car_boss ON claim.car_boss_id = car_boss.id \n"
            + "LEFT JOIN department ON claim.department_id = department.id \n"
            + "LEFT OUTER JOIN appointment ON appointment.record_id = record.id AND appointment.id = (SELECT max(id) from appointment WHERE record_id = record.id) \n"
            + "LEFT JOIN vehicle_model ON appointment.vehicle_model_id = vehicle_model.id \n"
            + "LEFT JOIN vehicle ON appointment.vehicle_id = vehicle.id \n"
            + "LEFT JOIN driver ON appointment.driver_id = driver.id \n"
            + "LEFT JOIN transport_dep ON appointment.transport_dep_id = transport_dep.id \n"
            + "WHERE start_date BETWEEN :date_start AND :date_end AND claim.actual IS TRUE AND claim.affirmator_id IS NOT NULL \n"
            + "GROUP BY department.id, claim.id, record.id, vehicle_type.id, vehicle_model.id, record.car_boss_firstname, record.car_boss_name, record.car_boss_surname, "
            + "record.car_boss_contacts, record.car_boss_post, app_user.id, appointment.id, transport_dep.id, vehicle.id, driver.id \n", nativeQuery = true)
    List<ClaimRecord> findClaimsByTimeFilter(
            @Param("date_start") LocalDateTime dateStart,
            @Param("date_end") LocalDateTime dateEnd);

    @Query(value = "SELECT vehicle_type.id as vehicletypeid, "
            + "vehicle_type.specialization as vehicletypespecialization, "
            + "record.start_date as startdate, "
            + "record.entrance_date as entrancedate, "
            + "record.end_date as enddate, "
            + "string_agg(place.name, ', ' order by route_task.order_num) as route, "
            + "vehicle_model.id as vehiclemodelid, "
            + "vehicle.id as vehicleid, "
            + "claim.purpose as purpose, "
            + "appointment.driver_id as driverid, "
            + "department.shortname as depshortname, "
            + "record.car_boss_firstname as carbossfirstname, "
            + "record.car_boss_name as carbossname, "
            + "record.car_boss_surname as carbosssurname, "
            + "record.car_boss_contacts as carbossphone, "
            + "appointment.id as appointmentid, "
            + "appointment.status as appstatus, "
            + "appointment.note as appnote, "
            + "appointment.creation_date as appcrdate, "
            + "appointment.creator_id as creatorid, "
            + "record.id as recordid, "
            + "claim.id as claimid "
            + "FROM public.claim \n"
            + "LEFT OUTER JOIN record ON record.claim_id = claim.id \n"
            + "LEFT OUTER JOIN route_task ON route_task.record_id = record.id \n"
            + "LEFT JOIN vehicle_type ON claim.vehicle_type_id = vehicle_type.id \n"
            + "LEFT JOIN place ON route_task.place_id = place.id \n"
            + "LEFT JOIN app_user ON record.affirmator_id = app_user.id \n"
          //  + "LEFT JOIN car_boss ON claim.car_boss_id = car_boss.id \n"
            + "LEFT JOIN department ON claim.department_id = department.id \n"
            + "LEFT OUTER JOIN appointment ON appointment.record_id = record.id AND appointment.id = (SELECT max(id) from appointment WHERE record_id = record.id) \n"
            + "LEFT JOIN vehicle_model ON appointment.vehicle_model_id = vehicle_model.id \n"
            + "LEFT JOIN vehicle ON appointment.vehicle_id = vehicle.id \n"
            + "LEFT JOIN driver ON appointment.driver_id = driver.id \n"
            + "LEFT JOIN transport_dep ON appointment.transport_dep_id = transport_dep.id \n"
            + "WHERE transport_dep.id = :tr_dep_id AND start_date BETWEEN :date_start AND :date_end AND claim.actual IS TRUE AND claim.affirmator_id IS NOT NULL \n"
            + "GROUP BY record.id, vehicle_type.id, vehicle_model.id, vehicle.id, "
            + "claim.purpose, appointment.driver_id, department.shortname, "
            + "record.car_boss_firstname, record.car_boss_name, record.car_boss_surname, record.car_boss_contacts, appointment.id, record.id, claim.id, appointment.status, appointment.note", nativeQuery = true)
    List<AppointmentClaim> findAppointmentClaimsTimeFilter(
            @Param("tr_dep_id") Long trDepId,
            @Param("date_start") LocalDateTime dateStart,
            @Param("date_end") LocalDateTime dateEnd
    );

    @Query(value = "SELECT vehicle_type.id as vehicletypeid, "
            + "vehicle_type.specialization as vehicletypespecialization, "
            + "record.start_date as startdate, "
            + "record.entrance_date as entrancedate, "
            + "record.end_date as enddate, "
            + "string_agg(place.name, ', ' order by route_task.order_num) as route, "
            + "vehicle_model.id as vehiclemodelid, "
            + "vehicle.id as vehicleid, "
            + "claim.purpose as purpose, "
            + "appointment.driver_id as driverid, "
            + "department.shortname as depshortname, "
            + "record.car_boss_firstname as carbossfirstname, "
            + "record.car_boss_name as carbossname, "
            + "record.car_boss_surname as carbosssurname, "
            + "record.car_boss_contacts as carbossphone, "
            + "appointment.id as appointmentid, "
            + "appointment.status as appstatus, "
            + "appointment.note as appnote, "
            + "appointment.creation_date as appcrdate, "
            + "appointment.creator_id as creatorid, "
            + "record.id as recordid, "
            + "claim.id as claimid "
            + "FROM public.claim \n"
            + "LEFT OUTER JOIN record ON record.claim_id = claim.id \n"
            + "LEFT OUTER JOIN route_task ON route_task.record_id = record.id \n"
            + "LEFT JOIN vehicle_type ON claim.vehicle_type_id = vehicle_type.id \n"
            + "LEFT JOIN place ON route_task.place_id = place.id \n"
            + "LEFT JOIN app_user ON record.affirmator_id = app_user.id \n"
          //  + "LEFT JOIN car_boss ON claim.car_boss_id = car_boss.id \n"
            + "LEFT JOIN department ON claim.department_id = department.id \n"
            + "LEFT OUTER JOIN appointment ON appointment.record_id = record.id AND appointment.id = (SELECT max(id) from appointment WHERE record_id = record.id) \n"
            + "LEFT JOIN vehicle_model ON appointment.vehicle_model_id = vehicle_model.id \n"
            + "LEFT JOIN vehicle ON appointment.vehicle_id = vehicle.id \n"
            + "LEFT JOIN driver ON appointment.driver_id = driver.id \n"
            + "LEFT JOIN transport_dep ON appointment.transport_dep_id = transport_dep.id \n"
            + "WHERE vehicle.id = :veh_id AND start_date BETWEEN :date_start AND :date_end AND claim.actual IS TRUE AND claim.affirmator_id IS NOT NULL \n"
            + "GROUP BY record.id, vehicle_type.id, vehicle_model.id, vehicle.id, "
            + "claim.purpose, appointment.driver_id, department.shortname, "
            + "record.car_boss_firstname, record.car_boss_name, record.car_boss_surname, record.car_boss_contacts, appointment.id, record.id, claim.id, appointment.status, appointment.note", nativeQuery = true)
    List<AppointmentClaim> findAppointmentClaimsByVehicle(
            @Param("veh_id") Long vehId,
            @Param("date_start") LocalDateTime dateStart,
            @Param("date_end") LocalDateTime dateEnd
    );
}
