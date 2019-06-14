package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.RouteTemplate;
import org.ivc.transportation.services.RouteTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class RouteTemplateController {

    @Autowired
    private RouteTemplateService routeTemplateService;

    @GetMapping("/user/routeTemplates")
    public List<RouteTemplate> getRouteTemplates(Principal principal) {
        return routeTemplateService.findRouteTemplates(principal);
    }
    
    @GetMapping("/dispatcher/routeTemplates")
    public List<RouteTemplate> getDispatcherRouteTemplates(Principal principal) {
        return routeTemplateService.findRouteTemplates(principal);
    }
    
    @GetMapping("/planner/routeTemplates")
    public List<RouteTemplate> getPlannerRouteTemplates(Principal principal) {
        return routeTemplateService.findRouteTemplates(principal);
    }

    @PostMapping("/user/routeTemplate_create")
    public RouteTemplate createRouteTemplate(Principal principal, @RequestBody RouteTemplate routeTemplate) {
        return routeTemplateService.saveRouteTemplate(principal, routeTemplate);
    }

    @PutMapping("/user/routeTemplate_update")
    public RouteTemplate updateRouteTemplate(Principal principal, @RequestBody RouteTemplate routeTemplate) {
        return routeTemplateService.saveRouteTemplate(principal, routeTemplate);
    }

    @DeleteMapping("/user/routeTemplates_delete")
    public ResponseEntity<String> deleteRouteTemplates(@RequestBody List<Long> ids) {
        routeTemplateService.deleteRouteTemplates(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
