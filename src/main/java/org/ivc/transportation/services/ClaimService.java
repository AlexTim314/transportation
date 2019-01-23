package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
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

    public CarBoss saveCarBoss(Principal principal, CarBoss carBoss) {
        return carBossRepository.save(carBoss);
    }

    public void deleteCarBoss(CarBoss carBoss) {
        carBossRepository.delete(carBoss);
    }

    public List<RouteTemplate> findRouteTemplates(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return routeTemplateRepository.findByDepartmentOrDepartmentIsNull(department);
        }
        return null;
    }

    public RouteTemplate saveRouteTemplate(Principal principal, RouteTemplate routeTemplate) {
        return routeTemplateRepository.save(routeTemplate);
    }

    public void deleteRouteTemplate(RouteTemplate routeTemplate) {
        routeTemplateRepository.delete(routeTemplate);
    }

    public List<Claim> findNewClaimsByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndAffirmationDateIsNull(department);
        }
        return null;
    }

    public Claim saveClaim(Principal principal, Claim claim) {
        if (claim.getId() == null) {
            claim.setCreationDate(LocalDateTime.now());
            claim.setCreator(getUser(principal));
            claim.setDepartment(getDepartment(principal));
        } else {
            Claim clm = claimRepository.findById(claim.getId()).get();
            Set<Record> rds = clm.getRecords();
            rds.forEach(recordRepository::delete);
        }
        return claimRepository.save(claim);
    }

    public void deleteClaim(Claim claim) {
        if (claim.getAffirmationDate() == null) {
            claimRepository.delete(claim);
        }
    }

}
