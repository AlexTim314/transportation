package org.ivc.transportation.controllers;

import java.util.Collection;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.services.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<AppUser> getAllUsers() {
        return usersManagementService.readAllUsers();
    }

    @GetMapping("/roles")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<AppRole> getAllRoles() {
        return usersManagementService.readAllRoles();
    }

    @PostMapping("/users/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public AppUser createUser(@RequestBody AppUser user) {
        return usersManagementService.createUser(user);
    }

    @PutMapping("/users/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public AppUser updateUser(@RequestBody AppUser user) {
        return usersManagementService.updateUser(user);
    }

    @DeleteMapping("/users/delete")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteUser(@RequestBody AppUser user) {
        usersManagementService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
