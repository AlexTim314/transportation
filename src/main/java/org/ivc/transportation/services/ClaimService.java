/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.RouteTemplate;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.PlaceRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.RouteTemplateRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nodata
 */
@Service
@Transactional
public class ClaimService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    RouteTemplateRepository routeTemplateRepository;

    private Department getDepartment(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getDepartment();
        }
        return null;
    }

    public List<Claim> findNewClaimsByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndAffirmationDateIsNull(department);
        }
        return null;
    }

    public Claim saveClaim(Principal principal, Claim claim) {
        claim.setDepartment(getDepartment(principal));
        return claimRepository.save(claim);
    }

    public void deleteClaim(Claim claim) {
        if (claim.getAffirmationDate() == null) {
            claimRepository.delete(claim);
        }
    }

    public List<Place> findAllPlaces(Principal principal) {
            return placeRepository.findAll();
    }

    public List<RouteTemplate> findRouteTemplates(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return routeTemplateRepository.findByDepartment(department);
        }
        return null;
    }

}
