package org.ivc.transportation.controllers;

import java.security.Principal;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String welcomePage(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            AppUser user = userRepository.findByUsername(loginedUser.getUsername());
            for (AppRole role : user.getRoles()) {
                String roleName = role.getRoleName();
                if (roleName.equals("ROLE_ADMIN")) {
                    model.addAttribute("title", "Welcome");
                    model.addAttribute("message", "This is welcome page!");
                    return "welcomePage";
                }
                if (roleName.equals("ROLE_USER") || roleName.equals("ROLE_MANAGER")) {
                    return "user/mainPage";
                }
                if (roleName.equals("ROLE_SUPERMANAGER")) {
                    return "supermanager/signature_form";
                }
                if (roleName.equals("ROLE_PLANNER")) {
                    return "planner/mainPage";
                }
                if (roleName.equals("ROLE_DISPATCHER")) {
                    return "dispatcher/mainPage";
                }
            }
        }
        return loginPage(model);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

}
