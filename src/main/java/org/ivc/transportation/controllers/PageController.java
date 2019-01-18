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

    @RequestMapping(value = "/claims", method = RequestMethod.GET)
    public String managerPage() {
        return "user/claimsPage";
    }

}
