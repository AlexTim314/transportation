/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.security.Principal;
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
    
    @PostMapping("/planner/appointment_create")
    public Record createAppointment(Principal principal, @RequestBody CompositeRecordIdAppointment cria) {
        return planningService.createAppointment(principal, cria);
    }
    
}
