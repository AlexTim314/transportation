package org.ivc.transportation.services;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.RouteTemplate;
import org.ivc.transportation.repositories.DepartmentRepository;
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
 * @author Sokolov Slava
 */
@Service
@Transactional
public class RouteTemplateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RouteTemplateRepository routeTemplateRepository;

    @Autowired
    RouteTaskRepository routeTaskRepository;

    public List<RouteTemplate> findRouteTemplates(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return routeTemplateRepository.findByDepartmentOrDepartmentIsNull(department);
        }
        return null;
    }

    public RouteTemplate saveRouteTemplate(Principal principal, RouteTemplate routeTemplate) {
        if (routeTemplate.getId() != null) {
            routeTaskRepository.deleteByTemplateId(routeTemplate.getId());
        }
        return routeTemplateRepository.save(routeTemplate);
    }

    public void deleteRouteTemplates(List<Long> routeTemplateIds) {
        routeTemplateRepository.deleteByIdIn(routeTemplateIds);
    }

    private Department getDepartment(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getDepartment();
        }
        return null;
    }

}
