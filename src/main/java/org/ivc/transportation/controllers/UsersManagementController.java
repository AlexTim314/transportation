package org.ivc.transportation.controllers;

import java.util.Collection;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.services.UsersManagementService;
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
public class UsersManagementController {

    @Autowired
    private UsersManagementService usersManagementService;

    @GetMapping("/management/users")
    public Collection<AppUser> getAllUsers() {
        return usersManagementService.readAllUsers();
    }

    @GetMapping("/management/roles")
    public Collection<AppRole> getAllRoles() {
        return usersManagementService.readAllRoles();
    }

    @PostMapping("/management/user_create")
    public AppUser createUser(@RequestBody AppUser user) {
        return usersManagementService.createUser(user);
    }

    @PutMapping("/management/user_update")
    public AppUser updateUser(@RequestBody AppUser user) {
        return usersManagementService.updateUser(user);
    }

    @DeleteMapping("/management/user_delete")
    public ResponseEntity<String> deleteUser(@RequestBody AppUser user) {
        usersManagementService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
