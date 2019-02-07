package org.ivc.transportation.controllers;

import java.security.Principal;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sokolov Slava
 */
@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        model.addAttribute("users", userRepository.findAll());

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }
        return "403Page";
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public String getDepartmentInfo(Model model, Principal principal, @PathVariable Long id) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            Department department = userRepository.findByUsername(loginedUser.getUsername()).getDepartment();
            if (Long.compare(department.getId(), id) != 0) {
                return accessDenied(model, principal);
            }
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("department", department);
            return "someDepartment";
        }
        return accessDenied(model, principal);
    }

//    @RequestMapping(value = "/departments", method = RequestMethod.GET)
//    public String getAllDepartments(Model model, Principal principal) {
//        if (principal != null) {
//            User loginedUser = (User) ((Authentication) principal).getPrincipal();
//            return "departmentPage";
//        }
//        return accessDenied(model, principal);
//    }

    @RequestMapping(value = "/transportDepsShow", method = RequestMethod.GET)
    public String getAllTransportDeps(Model model, Principal principal) {
        if (principal != null) {
            //TODO: выяснить, нужно ли тут это присваивание
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return "transportDepPage";
        }
        return accessDenied(model, principal);
    }

    @RequestMapping(value = "/addDepClaimShow", method = RequestMethod.GET)
    public String getDepClaims(Model model, Principal principal) {
        if (principal != null) {
            //TODO: выяснить, нужно ли тут это присваивание
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return "addingClaimsPage";
        }
        return accessDenied(model, principal);
    }

    @RequestMapping(value = "/addWaypointsShow", method = RequestMethod.GET)
    public String getWaypointsShow(Model model, Principal principal) {
        if (principal != null) {
            //TODO: выяснить, нужно ли тут это присваивание
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return "waypointsPage";
        }
        return accessDenied(model, principal);
    }

//    @RequestMapping(value = "/dispatcher", method = RequestMethod.GET)
//    public String getDispatcherPage(Model model, Principal principal) {
//        if (principal != null) {
//            User loginedUser = (User) ((Authentication) principal).getPrincipal();
//            return "dispatcherPage";
//        }
//        return accessDenied(model, principal);
//    }

    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    public String getPlanPage(Model model, Principal principal) {
        if (principal != null) {
            //TODO: выяснить, нужно ли тут это присваивание
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return "plan";
        }
        return accessDenied(model, principal);
    }
}
