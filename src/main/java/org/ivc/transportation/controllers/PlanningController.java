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
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.services.PlanningService;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/planner/affirmedClaims")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsAll() {
        return planningService.getAffirmedClaimsAll();
    }

    @GetMapping("/planner/affirmedClaims/Tomorrow")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsTomorrow() {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59), ZoneId.systemDefault());
        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
    }

    @GetMapping("/planner/affirmedClaims/Week")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsWeek() {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59), ZoneId.systemDefault());
        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
    }

    @PostMapping("/planner/affirmedClaims/Date")
    public List<CompositeDepartmentClaimRecords> getAffirmedClaimsWeek(@RequestBody ZonedDateTime date) {
        System.out.println(date);
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(0, 0), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(23, 59), ZoneId.systemDefault());
        return planningService.getAffirmedClaimsTimeFilter(dStart, dEnd);
    }

    @PostMapping("/planner/appointments_create")
    public List<Record> createAppointment(Principal principal, @RequestBody List<CompositeRecordIdAppointment> compositeRecordIdAppointment) {
        return planningService.createAppointment(principal, compositeRecordIdAppointment);
    }

}
