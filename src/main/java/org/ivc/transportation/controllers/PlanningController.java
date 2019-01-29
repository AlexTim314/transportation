/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.services.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author first
 */
@RestController
public class PlanningController {

    @Autowired
    private PlanningService planningService;
    
     @GetMapping("/planner/claims/Tomorrow")
    public List<Claim> getAffirmedClaimsTomorrow(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59), ZoneId.systemDefault());
//        System.out.println(dStart);
//        System.out.println(dEnd);
        return planningService.findAffirmedClaimsByDepartmentTimeFilter(principal, dStart, dEnd);
    }
    
    @GetMapping("/planner/claims/Week")
    public List<Claim> getAffirmedClaimsWeek(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59), ZoneId.systemDefault());
//        System.out.println(dStart);
//        System.out.println(dEnd);
        System.out.println("Недельные подтвержденные заявки");
        planningService.findAffirmedClaimsByDepartmentTimeFilter(principal, dStart, dEnd).forEach(System.out::println);
        return planningService.findAffirmedClaimsByDepartmentTimeFilter(principal, dStart, dEnd);
    }
    

    
    @GetMapping("/planner/claims")
    public List<Claim> getAffirmedClaimsAll(Principal principal) {
        return planningService.findAffirmedClaimsByDepartment(principal);
    }
    
    @GetMapping("/planner/transportDeps")
    public List<TransportDep> getAllTransportDeps() {
        return planningService.findAllTransportDeps();
    }
    
    @GetMapping("/planner/vehicleModels")
    public List<VehicleModel> getAllVehicleModels() {
        return planningService.findAllVehicleModels();
    }  

}
