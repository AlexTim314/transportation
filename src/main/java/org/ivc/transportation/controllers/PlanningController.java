package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.services.PlanningService;
import org.ivc.transportation.services.VehicleService;
import org.ivc.transportation.utils.AffirmedClaim;
import org.ivc.transportation.utils.ClaimAppointment;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
import org.ivc.transportation.utils.CompositeModelTransportDep;
import org.ivc.transportation.utils.CompositeOtsInfo;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.ivc.transportation.utils.ClaimRecord;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author first
 */
@RestController
public class PlanningController {

    @Autowired
    private PlanningService planningService;

    private VehicleService vehicleService;

    @GetMapping("/planner/permit")
    public Boolean getPermit(Principal principal) {
        return planningService.getPermit(principal);
    }

    @GetMapping("/planner/username")
    public String getUserName(Principal principal) {
        return planningService.getUserName(principal);
    }

    @GetMapping("/planner/affirmedClaims")
    public List<AffirmedClaim> getAffirmedClaimsAll() {
        return planningService.getAffirmedClaimsAll1();
    }

//    @GetMapping("/planner/affirmedClaims1/monthBefore")
//    public List<AffirmedClaim> getAffirmedClaimsAll1() {
//        return planningService.getAffirmedClaimsAll1();
//    }
    @GetMapping("/planner/ots_info")
    public List<CompositeOtsInfo> getOtsInfo() {
        return planningService.getOtsInfo();
    }

    @GetMapping("/planner/transport_dep_models")
    public List<CompositeModelTransportDep> getTransportDepModels() {
        return planningService.getTransportDepModels();
    }

//    @GetMapping("/planner/affirmedClaims/Tomorrow")
//    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsTomorrow() {
//        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
//        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
//        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
//    }
    @GetMapping("/planner/affirmedClaims/Tomorrow")
    public List<AffirmedClaim> getAffirmedClaimsTomorrow() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
        return planningService.getAffirmedClaimsByTimeFilter(dStart, dEnd);
    }

//    @GetMapping("/planner/affirmedClaims/Week")
//    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsWeek() {
//        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
//        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
//        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
//    }
    @GetMapping("/planner/affirmedClaims/Week")
    public List<AffirmedClaim> getAffirmedClaimsWeek() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
        return planningService.getAffirmedClaimsByTimeFilter(dStart, dEnd);
    }
// not used
//    @GetMapping("/planner/affirmedClaims/Month")
//    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsMonth() {
//        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
//        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusMonths(1), LocalTime.of(23, 59));
//        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
//    }

//    @GetMapping("/planner/affirmedClaims/monthBefore")
//    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsMonthBefore() {
//        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.of(00, 00));
//        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
//        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
//    }
    @GetMapping("/planner/affirmedClaims/monthBefore")
    public List<AffirmedClaim> getAffirmedClaimsMonthBefore() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.of(00, 00));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        return planningService.getAffirmedClaimsByTimeFilter(dStart, dEnd);
    }

//    @PostMapping("/planner/affirmedClaims/Date")
//    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsDate(@RequestBody LocalDateTime date) {
//        LocalDateTime dStart = LocalDateTime.of(LocalDate.from(date), LocalTime.of(0, 0));
//        LocalDateTime dEnd = LocalDateTime.of(LocalDate.from(date), LocalTime.of(23, 59));
//        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
//    }
    @PostMapping("/planner/affirmedClaims/Date")
    public List<AffirmedClaim> getAffirmedClaimsDate(@RequestBody LocalDateTime date) {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.from(date), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.from(date), LocalTime.of(23, 59));
        return planningService.getAffirmedClaimsByTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/plannedClaims")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsAll() {
        return planningService.getPlannedClaimsAll();
    }

    @GetMapping("/planner/plannedClaims/Tomorrow")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsTomorrow() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
        List<CompositeDepartmentClaimRecords> result = planningService.getPlannedClaimsTimeFilter(dStart, dEnd);
        return result;
    }

    @GetMapping("/planner/plannedClaims/Week")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsWeek() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
        return planningService.getPlannedClaimsTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/plannedClaims/Month")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsMonth() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusMonths(1), LocalTime.of(23, 59));
        return planningService.getPlannedClaimsTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/plannedClaims/monthBefore")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsMonthBefore() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.of(00, 00));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        return planningService.getPlannedClaimsTimeFilter(dStart, dEnd);
    }

    @PostMapping("/planner/plannedClaims/Date")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsDate(@RequestBody LocalDateTime date) {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.from(date), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.from(date), LocalTime.of(23, 59));
        return planningService.getPlannedClaimsTimeFilter(dStart, dEnd);
    }

    @PostMapping("/planner/appointments_create")
    public List<Record> createAppointments(Principal principal, @RequestBody List<CompositeRecordIdAppointment> compositeRecordIdAppointment) {
        return planningService.createAppointments(principal, compositeRecordIdAppointment);
    }

    @PutMapping("/planner/recordCancel")
    public Record recordCancel(Principal principal, @RequestBody CompositeRecordIdAppointment compositeRecordIdAppointment) {
        return planningService.recordCancel(principal, compositeRecordIdAppointment);
    }

    @PutMapping("/planner/route_update")
    public Record updateRoute(@RequestBody Record record) {
        return planningService.updateRoute(record);
    }

    @PutMapping("/planner/time_update")
    public Record updateTime(@RequestBody Record record) {
        return planningService.updateTime(record);
    }

    @GetMapping("/planner/carBosses")
    public List<CarBoss> getCarBosses(Principal principal) {
        return planningService.findCarBossesByDepartment(principal);
    }

    @GetMapping("/planner/vehicles")
    public List<Vehicle> getAllVehicles(Principal principal) {
        return planningService.getAllVehicles(principal);
    }

    @GetMapping("/planner/drivers")
    public List<Driver> getAllDrivers(Principal principal) {
        return planningService.getAllDrivers(principal);
    }

    @PostMapping("/planner/carBoss_create")
    public CarBoss createCarBoss(Principal principal, @RequestBody CarBoss carBoss) {
        return planningService.saveCarBoss(principal, carBoss);
    }

    @PutMapping("/planner/carBoss_update")
    public CarBoss updateCarBoss(Principal principal, @RequestBody CarBoss carBoss) {
        return planningService.saveCarBoss(principal, carBoss);
    }

    @DeleteMapping("/planner/carBoss_delete")
    public ResponseEntity<String> deleteCarBoss(@RequestBody CarBoss carBoss) {
        planningService.deleteCarBoss(carBoss);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/planner/claim_create")
    public Claim createClaim(Principal principal, @RequestBody Claim claim) {
        return planningService.saveClaim(principal, claim);
    }

    @GetMapping("/planner/claims/day")
    public List<ClaimRecord> getClaimsByDay(@RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.uuuu")), LocalTime.MIN);
        LocalDateTime dStart = LocalDateTime.of(LocalDate.from(dateTime), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.from(dateTime), LocalTime.of(23, 59));
        return planningService.findClaimsByTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/claims/tomorrow")
    public List<ClaimRecord> getTomorrowClaims() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
        return planningService.findClaimsByTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/claims/week")
    public List<ClaimRecord> getWeekClaims() {
        int dt = LocalDate.now().getDayOfWeek().getValue() - 1;
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().minusDays(dt), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7 - dt), LocalTime.of(23, 59));
        return planningService.findClaimsByTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/claims/archive")
    public List<ClaimRecord> getArchiveClaims() {
        int dt = LocalDate.now().getDayOfMonth() - 1;
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().minusDays(dt).minusMonths(1), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        return planningService.findClaimsByTimeFilter(dStart, dEnd);
    }

    @PostMapping("/planner/claims/archive/from")
    public List<ClaimRecord> getArchiveClaimsFromDay(@RequestBody LocalDateTime date) {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.from(date), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        return planningService.findClaimsByTimeFilter(dStart, dEnd);
    }

    @PostMapping("/planner/info")
    public ClaimAppointment getAppointmentInfo(@RequestBody List<Long> ids) {
        return planningService.getAppointmentInfo(ids.get(0), ids.get(1));
    }

}
