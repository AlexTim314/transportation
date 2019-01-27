package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.ivc.transportation.repositories.RouteTaskRepository;
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
    RouteTaskRepository routeTaskRepository;

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
//            claim.getRecords().forEach(r -> recordRepository.delete(r));
            recordRepository.deleteByIdIn(
                    claimRepository.findById(claim.getId()).get().getRecords()
                            .stream().map(u -> u.getId()).collect(Collectors.toList())
            );
//            recordRepository.deleteByClaimId(claim.getId());
            routeTaskRepository.deleteByIdIn(
                    claimRepository.findById(claim.getId()).get().getRouteTasks()
                            .stream().map(u -> u.getId()).collect(Collectors.toList())
            );
//            claim.getRouteTasks().forEach(tsk -> routeTaskRepository.delete(tsk));
        }
        claim.setCreator(getUser(principal));
        claim.setDepartment(getDepartment(principal));
        return claimRepository.save(claim);
    }

    public void affirmClaims(Principal principal, List<Long> claimIds) {
        AppUser affirmator = getUser(principal);
        claimIds.forEach(id -> {
            Claim claim = claimRepository.findById(id).get();
            claim.setAffirmator(affirmator);
            claim.setAffirmationDate(LocalDateTime.now());
            claimRepository.save(claim);
        });
    }

    public void deleteClaim(Claim claim) {
        claimRepository.deleteByIdAndAffirmationDateIsNull(claim.getId());
    }

    public void deleteClaims(List<Long> claimIds) {
        claimRepository.deleteByIdInAndAffirmationDateIsNull(claimIds);
    }

    public void deleteRecord(Long clmId, Long recId) {
        Claim claim = claimRepository.findById(clmId).get();
        Record record = recordRepository.findById(recId).get();
        claim.getRecords().remove(record);
        claimRepository.save(claim);
        recordRepository.delete(record);
    }

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

}
