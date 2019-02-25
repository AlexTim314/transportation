package org.ivc.transportation.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RoleRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sokolov Slava
 */
@Service
@Transactional
public class UsersManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TransportDepRepository transportDepRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Collection<AppRole> readAllRoles() {
        return roleRepository.findAll();
    }

    public Collection<AppUser> readAllUsers() {
        return userRepository.findAll();
    }

    public AppUser createUser(AppUser user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        Set<AppRole> roles = new HashSet<>();
        for (AppRole role : user.getRoles()) {
            role = roleRepository.findByRoleName(role.getRoleName());
            roles.add(role);
        }
        Department department = user.getDepartment() == null ? null : departmentRepository.findById(user.getDepartment().getId()).get();
        TransportDep transportDep = user.getTransportDep() == null ? null : transportDepRepository.findById(user.getTransportDep().getId()).get();
        user.setDepartment(department);
        user.setTransportDep(transportDep);
        user.setRoles(roles);
        return userRepository.saveAndFlush(user);
    }

    public AppUser updateUser(AppUser user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        AppUser edittingUser = userRepository.findByUsername(user.getUsername());
        edittingUser.setPassword(encryptedPassword);
        edittingUser.setPost(user.getPost());
        Set<AppRole> roles = new HashSet<>();
        for (AppRole role : user.getRoles()) {
            role = roleRepository.findByRoleName(role.getRoleName());
            roles.add(role);
        }
        Department department = user.getDepartment() == null ? null : departmentRepository.findById(user.getDepartment().getId()).get();
        TransportDep transportDep = user.getTransportDep() == null ? null : transportDepRepository.findById(user.getTransportDep().getId()).get();
        edittingUser.setDepartment(department);
        edittingUser.setTransportDep(transportDep);
        edittingUser.setRoles(roles);
        return userRepository.saveAndFlush(edittingUser);
    }

    public List<Department> updateDepartmentsWithUser(List<Department> departments) {
        List<Department> result = new ArrayList<>();
        departments.forEach(dep -> result.add(departmentRepository.save(dep)));
        return result;
    }

    public void deleteUser(AppUser user) {
        user = userRepository.findByUsername(user.getUsername());
        user.setDepartment(null);
        user.setRoles(new HashSet<>());
        userRepository.deleteById(user.getId());
    }

}
