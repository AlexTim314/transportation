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
import org.ivc.transportation.services.SuperManagerService;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeDepartmentClaimRecords;
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
public class SuperManagerController {

    @Autowired
    private SuperManagerService superManagerService;

    @GetMapping("/supermanager/records")
    public List<CompositeClaimRecord> getRecords(Principal principal) {
        return superManagerService.getCompositeClaimRecordsAll(principal);
    }
    
    @GetMapping("/supermanager/records/Tomorrow")
    public List<CompositeClaimRecord> getPlannedClaimsTomorrow(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59), ZoneId.systemDefault());
        return superManagerService.getCompositeClaimRecordsTimeFilter(principal, dStart, dEnd);
    }

    @GetMapping("/supermanager/records/Week")
    public List<CompositeClaimRecord> getPlannedClaimsWeek(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59), ZoneId.systemDefault());
        return superManagerService.getCompositeClaimRecordsTimeFilter(principal, dStart, dEnd);
    }

    @PostMapping("/supermanager/records/Date")
    public List<CompositeClaimRecord> getPlannedClaimsDate(Principal principal,@RequestBody ZonedDateTime date) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(0, 0), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(23, 59), ZoneId.systemDefault());
        return superManagerService.getCompositeClaimRecordsTimeFilter(principal, dStart, dEnd);
    }

}
