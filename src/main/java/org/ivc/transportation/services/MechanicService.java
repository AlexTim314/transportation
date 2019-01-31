package org.ivc.transportation.services;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Mechanic;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.MechanicRepository;
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
public class MechanicService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MechanicRepository mechanicRepository;

    public List<Mechanic> findMechanicsByTransportDep(Principal principal) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null) {
            return mechanicRepository.findByTransportDep(transportDep);
        }
        return null;
    }

    public Mechanic saveMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public void deleteMechanic(Mechanic mechanic) {
        mechanicRepository.delete(mechanic);
    }

    private TransportDep getTransportDep(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }
}
