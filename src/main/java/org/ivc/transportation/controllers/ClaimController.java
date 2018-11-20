package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.Collection;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class ClaimController {

    @Autowired
    private ClaimService claimService ;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/find-claims")
    public Collection<Claim> findClaims() {
        return claimService.getAllClaimsSortByDate();
    }
    
    @DeleteMapping("/claims/delete/{id}")
    public Collection<Claim> delClaim(@PathVariable Long id) {
        claimService.removeClaim(id);
        return claimService.getAllClaimsSortByDate();
    }

    @GetMapping("/claims")
    public Collection<Claim> findClaimsByUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            return claimService.getClaimsByDepartment(department.getId());
        }
        return null;
    }
    
    
    @GetMapping("/find-claims-by-department")
    public Collection<Claim> findClaimsByDepAndAffirmation() {
        return claimService.getClaimsByDepAndAffirmation(Long.MIN_VALUE, Boolean.TRUE);
    }

}
