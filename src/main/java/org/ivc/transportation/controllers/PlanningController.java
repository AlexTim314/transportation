package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.services.PlanningService;
import org.ivc.transportation.services.VehicleService;
import org.ivc.transportation.utils.AffirmedClaim;
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

    @GetMapping("/planner/affirmedClaims")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsAll() {
        return planningService.getAffirmedClaimsAll();
    }

    @GetMapping("/planner/affirmedClaims1")
    public List<AffirmedClaim> getAffirmedClaimsAll1() {
        return planningService.getAffirmedClaimsAll1();
    }

    @GetMapping("/planner/ots_info")
    public List<CompositeOtsInfo> getOtsInfo() {
        return planningService.getOtsInfo();
    }
    
    @GetMapping("/planner/transport_dep_models")
    public List<CompositeModelTransportDep> getTransportDepModels() {
        return planningService.getTransportDepModels();
    }

    @GetMapping("/planner/affirmedClaims/Tomorrow")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsTomorrow() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/affirmedClaims/Week")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsWeek() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
    }

    @PostMapping("/planner/affirmedClaims/Date")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsDate(@RequestBody LocalDateTime date) {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.from(date), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.from(date), LocalTime.of(23, 59));
        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/plannedClaims")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsAll() {
        return planningService.getPlannedClaimsAll();
    }

    @GetMapping("/planner/plannedClaims/Tomorrow")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsTomorrow() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
        return planningService.getPlannedClaimsTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/plannedClaims/Week")
    public List<CompositeDepartmentClaimRecords> getPlannedClaimsWeek() {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
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
    public Claim updateRoute(@RequestBody Claim claim) {
        return planningService.updateRoute(claim);
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

}
