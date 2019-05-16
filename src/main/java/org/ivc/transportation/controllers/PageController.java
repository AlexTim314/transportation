package org.ivc.transportation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sokolov Slava
 */
@Controller
public class PageController {

    @RequestMapping(value = "/usersManagement", method = RequestMethod.GET)
    public String usersManagementPage() {
        return "admin/usersManagementPage";
    }

    @RequestMapping(value = "/departmentsManagement", method = RequestMethod.GET)
    public String departmentsManagementPage() {
        return "admin/departmentsManagementPage";
    }

    @RequestMapping(value = "/transportDepsManagement", method = RequestMethod.GET)
    public String transportDepsManagementPage() {
        return "admin/transportDepsManagementPage";
    }

    @RequestMapping(value = "/placesManagement", method = RequestMethod.GET)
    public String placesManagementPage() {
        return "admin/placesManagementPage";
    }

    @RequestMapping(value = "/vehicleTypesManagement", method = RequestMethod.GET)
    public String vehicleTypesManagementPage() {
        return "admin/vehicleTypesManagementPage";
    }

    @RequestMapping(value = "/fuelsManagement", method = RequestMethod.GET)
    public String fuelsManagementPage() {
        return "admin/fuelsManagementPage";
    }

    @RequestMapping(value = "/claims", method = RequestMethod.GET)
    public String managerPage() {
        return "user/mainPage";
    }

    @RequestMapping(value = "/planner", method = RequestMethod.GET)
    public String plannerPage() {
        return "planner/mainPage";
    }

    @RequestMapping(value = "/planner1", method = RequestMethod.GET)
    public String planner1Page() {
        return "planner1/mainPage";
    }

    @RequestMapping(value = "/dispatcher", method = RequestMethod.GET)
    public String dispatcherPage() {
        return "dispatcher/mainPage";
    }

    @RequestMapping(value = "/supermanager", method = RequestMethod.GET)
    public String superManagerPage() {
        return "supermanager/signature_form";
    }

}
