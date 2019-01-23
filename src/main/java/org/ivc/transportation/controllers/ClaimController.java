package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.RouteTemplate;
import org.ivc.transportation.services.ClaimService;
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
 * @author nodata
 */
@RestController
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/user/claims")
    public List<Claim> getAllClaims(Principal principal) {
        return claimService.findNewClaimsByDepartment(principal);
    }

    @GetMapping("/user/routeTemplates")
    public List<RouteTemplate> getRouteTemplates(Principal principal) {
        return claimService.findRouteTemplates(principal);
    }

    @GetMapping("/user/carBosses")
    public List<CarBoss> getCarBosses(Principal principal) {
        return claimService.findCarBossesByDepartment(principal);
    }

    @PostMapping("/user/claim_create")
    public Claim createClaim(Principal principal, @RequestBody Claim claim) {
        return claimService.saveClaim(principal, claim);
    }

    @PutMapping("/user/claim_update")
    public Claim updateClaim(Principal principal, @RequestBody Claim claim) {
        return claimService.saveClaim(principal, claim);
    }

    @DeleteMapping("/user/claim_delete")
    public ResponseEntity<String> deleteClaim(Principal principal, @RequestBody Claim claim) {
        claimService.deleteClaim(claim);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
