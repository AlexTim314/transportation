package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.Collection;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class ClaimController {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/find-claims")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Claim> findClaims() {
        return claimRepository.findAll();
    }

    @GetMapping("/claims")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Claim> findClaimsByUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            return claimRepository.findByDepartmentId(department.getId());
        }
        return null;
    }

}
