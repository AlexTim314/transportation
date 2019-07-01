package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @author nodata
 */
@RestController
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping("/user/permit")
    public Boolean getPermit(Principal principal) {
        return claimService.getPermit(principal);
    }

    @GetMapping("/user/username")
    public String getUserName(Principal principal) {
        return claimService.getUserName(principal);
    }

    @GetMapping("/user/affirmedClaims/Tomorrow")
    public List<Claim> getAffirmedClaimsTomorrow(Principal principal) {
        LocalDateTime dStart = LocalDateTime.now();
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
        return claimService.findAffirmedClaimsByDepartmentTimeFilter(principal, dStart, dEnd);
    }

    @GetMapping("/user/affirmedClaims/Week")
    public List<Claim> getAffirmedClaimsWeek(Principal principal) {
        LocalDateTime dStart = LocalDateTime.now();
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
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
        Claim newClaim = claimService.saveClaim(principal, claim);
        messagingTemplate.convertAndSend("/topic/create_claim", newClaim);
        return newClaim;
    }

    @PutMapping("/user/claim_update")
    public Claim updateClaim(Principal principal, @RequestBody Claim claim) {
        Claim updClaim = claimService.saveClaim(principal, claim);
        messagingTemplate.convertAndSend("/topic/update_claim", updClaim);
        return updClaim;
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

    @PostMapping("/upload")
    public @ResponseBody ResponseEntity<?> upload(@RequestParam("files") MultipartFile[] uploadFiles) throws Exception {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
