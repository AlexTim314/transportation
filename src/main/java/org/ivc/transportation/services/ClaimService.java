package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.RouteTemplate;
import org.ivc.transportation.repositories.CarBossRepository;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
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
    RouteTemplateRepository routeTemplateRepository;

    @Autowired
    CarBossRepository carBossRepository;

    private Department getDepartment(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getDepartment();
        }
        return null;
    }

    private AppUser getUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername());
        }
        return null;
    }

    public List<CarBoss> findCarBossesByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return carBossRepository.findByDepartment(department);
        }
        return null;
    }

    public List<RouteTemplate> findRouteTemplates(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return routeTemplateRepository.findByDepartmentOrDepartmentIsNull(department);
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
        claim.setCreationDate(LocalDateTime.now());
        claim.setCreator(getUser(principal));
        claim.setDepartment(getDepartment(principal));
        return claimRepository.save(claim);
    }

    public void deleteClaim(Claim claim) {
        if (claim.getAffirmationDate() == null) {
            claimRepository.delete(claim);
        }
    }

}