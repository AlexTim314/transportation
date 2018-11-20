package org.ivc.transportation.controllers;

import org.ivc.transportation.exceptions.NotSpecifiedDepartmentException;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.exceptions.NonExistingDepartmentException;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ClaimService claimService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/departments")
    public Collection<Department> getAllDepartments() {
        return departmentService.getDepartments();
    }

//TODO: сомнительная потребность в этом запросе, оценить
    @GetMapping("/departments/{id}")
    public Department getDepartment(@PathVariable long id) {
        Optional<Department> optDep = departmentService.getDepartmentById(id);
        return optDep.orElseThrow(() -> {

            String ruMessage = "Транспортный отдел с запрошенным номером не"
                    + " найден в базе. ID = " + id + ". ";
            String engMessage = "Error in " + DepartmentController.class
                    + " when try get Department by id = " + id + ".";

            return new NonExistingDepartmentException(ruMessage
                    + "<br>" + engMessage);
        });
    }

    @DeleteMapping("/departments/delete")
    public Collection<Department> deleteDepartment(@RequestBody Department department) {
        departmentService.removeDepartment(department.getId());
        return departmentService.getDepartments();
    }

    @PostMapping("/departments/create")
    public Collection<Department> addDepartment(@RequestBody Department department) {
        departmentService.saveDepartment(department);
        return departmentService.getDepartments();
    }

    @PutMapping("/departments/update")
    public Collection<Department> updateDepartment(@RequestBody Department dep, @PathVariable long id) {
        departmentService.updateDepartment(dep, id);
        return departmentService.getDepartments();
    }

    /**
     * Метод возвращает заявки поданные подразделением. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением.
     *
     * @param principal данные пользователя
     * @return При вызове без авторизации, возвращает null.
     */
    @GetMapping("/departments/claims")
    public Collection<Claim> getClaims(Principal principal) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            System.out.println("principal.getName()" + principal.getName());
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                String ruMessage = "В Вашем пользовательском профиле не указано"
                        + " подразделение. Поэтому неясно какие заявки должны"
                        + " быть отображены. Обратитесь к администатору.";
                String engMessage = "Department does not specified in your profile."
                        + " Can't decide what claims must be shown. Contact your administrator.";

                throw new NotSpecifiedDepartmentException(ruMessage
                        + "<br>" + engMessage);
            }
            return claimService.getClaimsByDepartment(department.getId());
        }
        return null;
    }

    /**
     * Метод для получения заявок заданного подразделения. Предполагается
     * , что доступ к этому методу будет только у администратора. Пользователям 
     * предоставляется метод getClaims(Principal principal).
     * @param department подразделение, заявки которого требуются
     * @return список заявок
     */
    @GetMapping("/departments/claimsByDepartment")
    public Collection<Claim> getClaims(@RequestBody Department department) {
        return claimService.getClaimsByDepartment(department.getId());
    }

    @GetMapping("/departments/{idDepartment}/claims/{idClaim}")
    public Collection<Record> getRecordsByClaim(@PathVariable Long idClaim, @PathVariable Long idDepartment) {
        return claimService.getRecordsByClaim(idClaim);
    }

    @PostMapping("/departments/{id}/claims/create")
    public Collection<Claim> addClaim(@RequestBody Claim claim, @PathVariable Long id) {
        claimService.addClaim(claim);
        return claimService.getClaimsByDepartment(id);
    }

}
