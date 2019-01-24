package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
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
import org.ivc.transportation.repositories.RouteTaskRepository;
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
    
    @Autowired
    RouteTaskRepository routeTaskRepository;

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
            return claimRepository.findByDepartmentAndAffirmationDateIsNullAndTemplateNameIsNull(department);
        }
        return null;
    }

    public List<Claim> findClaimTemplatesByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndTemplateNameIsNotNull(department);
        }
        return null;
    }

    public List<Claim> findAffirmedClaimsByDepartmentTimeFilter(Principal principal, ZonedDateTime dStart, ZonedDateTime dEnd) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndAffirmationDateIsNotNullAndActualIsTrueAndRecordsStartDateBetween(department, dStart, dEnd);
        }
        return null;
    }

    public List<Claim> findAffirmedClaimsByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return claimRepository.findByDepartmentAndAffirmationDateIsNotNullAndActualIsTrue(department);
        }
        return null;
    }

    public Claim saveClaim(Principal principal, Claim claim) {
        if (claim.getId() == null) {
            claim.setCreationDate(LocalDateTime.now());              
        } else {
            claimRepository.findById(claim.getId())
                    .get()
                    .getRecords()
                    .forEach(recordRepository::delete);
            claimRepository.findById(claim.getId())
                    .get()
                    .getRouteTasks()
                    .forEach(routeTaskRepository::delete);
        }
        claim.setCreator(getUser(principal)); 
        claim.setDepartment(getDepartment(principal));
        return claimRepository.save(claim);
    }

    public void deleteClaim(Claim claim) {
        if (claim.getAffirmationDate() == null) {
            claimRepository.delete(claim);
        }
    }

    public void deleteClaims(List<Long> cl) {
        for (int i = 0; i < cl.size(); i++) {
            claimRepository.deleteById(cl.get(i));
        }
    }

    public void deleteRecord(Long clmId, Long recId) {
        Claim claim = claimRepository.findById(clmId).get();
        Record record = recordRepository.findById(recId).get();
        claim.getRecords().remove(record);
        claimRepository.save(claim);
        recordRepository.delete(record);
    }

}
