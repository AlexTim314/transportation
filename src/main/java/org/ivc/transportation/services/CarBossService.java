package org.ivc.transportation.services;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.CarBossRepository;
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
public class CarBossService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarBossRepository carBossRepository;

    public List<CarBoss> findCarBossesByDepartment(Principal principal) {
        Department department = getDepartment(principal);
        if (department != null) {
            return carBossRepository.findByDepartment(department);
        }
        return null;
    }

    public CarBoss saveCarBoss(Principal principal, CarBoss carBoss) {
        return carBossRepository.save(carBoss);
    }

    public void deleteCarBoss(CarBoss carBoss) {
        carBossRepository.delete(carBoss);
    }

    private Department getDepartment(Principal principal) {
        if (principal == null) { return null; }
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        return userRepository.findByUsername(loginedUser.getUsername()).getDepartment();

    }

}
