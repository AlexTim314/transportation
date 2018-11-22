package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.Collection;
import org.ivc.transportation.config.trUtils.DateRange;
import static org.ivc.transportation.config.trUtils.errNotSpecifiedDepartmentException;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.exceptions.NotSpecifiedDepartmentException;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//    @DeleteMapping("/claims/delete/{id}")
//    public Collection<Claim> delClaim(@PathVariable Long id) {
//        claimService.removeClaim(id);
//        return claimService.getAllClaimsSortByDate();
//    }

    /**
     * Метод возвращает заявки поданные подразделением. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением.
     *
     * @param principal данные пользователя
     * @param dr
     * @return При вызове без авторизации, возвращает null.
     */
    @GetMapping("/claims")
    public Collection<Claim> findClaimsByUser(Principal principal, @RequestBody DateRange dr) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            //return claimService.getClaimsByDepartment(department.getId());
            return claimService.getAllClaimsByDepartmentAndDate(department.getId(), dr);
        }
        return null;
    }
    
    /**
     * Метод для получения заявок заданного подразделения. Предполагается , что
     * доступ к этому методу будет только у администратора. Пользователям
     * предоставляется метод getClaims(Principal principal).
     *
     * @param department подразделение, заявки которого требуются
     * @param dr
     * @return список заявок
     */
    @PostMapping("/claims/byDepartment")
    public Collection<Claim> findClaimsByDepartment(@RequestBody Department department/*, @RequestBody DateRange dr*/) {
        //return claimService.getClaimsByDepartment(department.getId());
        //return claimService.getAllClaimsByDate(dr);
        return claimService.getAllClaimsSortByDate();
    }

}
