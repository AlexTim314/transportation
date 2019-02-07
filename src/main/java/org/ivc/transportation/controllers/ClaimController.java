package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.services.ClaimService;
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
 *
 * @author nodata
 */
@RestController
public class ClaimController {
    
    @Autowired
    private ClaimService claimService;
    
    @GetMapping("/user/affirmedClaims/Tomorrow")
    public List<Claim> getAffirmedClaimsTomorrow(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59), ZoneId.systemDefault());
//        System.out.println(dStart);
//        System.out.println(dEnd);
        return claimService.findAffirmedClaimsByDepartmentTimeFilter(principal, dStart, dEnd);
    }
    
    @GetMapping("/user/affirmedClaims/Week")
    public List<Claim> getAffirmedClaimsWeek(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59), ZoneId.systemDefault());
//        System.out.println(dStart);
//        System.out.println(dEnd);
        System.out.println("Недельные подтвержденные заявки");
        claimService.findAffirmedClaimsByDepartmentTimeFilter(principal, dStart, dEnd).forEach(System.out::println);
        return claimService.findAffirmedClaimsByDepartmentTimeFilter(principal, dStart, dEnd);
    }
    
    @GetMapping("/user/affirmedClaims")
    public List<Claim> getAffirmedClaimsAll(Principal principal) {
        return claimService.findAffirmedClaimsByDepartment(principal);
    }
    
    @GetMapping("/user/newClaims")
    public List<Claim> getNewClaims(Principal principal) {
        return claimService.findNewClaimsByDepartment(principal);
    }
    
    @GetMapping("/user/claimTemplates")
    public List<Claim> getClaimTemplates(Principal principal) {
        return claimService.findClaimTemplatesByDepartment(principal);
    }
    
    @PostMapping("/user/claim_create")
    public Claim createClaim(Principal principal, @RequestBody Claim claim) {
        return claimService.saveClaim(principal, claim);
    }
    
    @PutMapping("/user/claim_update")
    public Claim updateClaim(Principal principal, @RequestBody Claim claim) {
        return claimService.saveClaim(principal, claim);
    }
    
    @PutMapping("/manager/claims_affirm")
    public ResponseEntity<String> affirmClaims(Principal principal, @RequestBody List<Long> claimIds) {
        claimService.affirmClaims(principal, claimIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/user/claims_delete")
    public ResponseEntity<String> deleteClaims(@RequestBody List<Long> claimIds) {
        claimService.deleteClaims(claimIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/manager/recordCancel")
    public Record recordCancel(Principal principal, @RequestBody CompositeRecordIdAppointment compositeRecordIdAppointment) {
        return claimService.recordCancel(principal, compositeRecordIdAppointment);
    }
    
    @DeleteMapping("/user/record_delete")
    public ResponseEntity<String> deleteRecord(@RequestBody List<Long> ids) {
        claimService.deleteRecord(ids.get(0), ids.get(1));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
