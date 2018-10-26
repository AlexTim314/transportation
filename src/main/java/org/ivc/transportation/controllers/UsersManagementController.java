package org.ivc.transportation.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RoleRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class UsersManagementController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/roles")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<AppRole> getAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping("/users/create")
    @CrossOrigin(origins = "http://localhost:4200")
    public AppUser createUser(@RequestBody AppUser user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        Set<AppRole> roles = new HashSet<>();
        for (AppRole role : user.getRoles()) {
            role = roleRepository.findByRoleName(role.getRoleName());
            roles.add(role);
        }
        Department department = departmentRepository.findByName(user.getDepartment().getName());
        user.setDepartment(department);
        user.setRoles(roles);
        return userRepository.saveAndFlush(user);
    }

    @DeleteMapping("/users/delete")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteUser(@RequestBody AppUser user) {
        user = userRepository.findByUserName(user.getUserName());
        user.setDepartment(null);
        user.setRoles(new HashSet<>());
        userRepository.deleteById(user.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
