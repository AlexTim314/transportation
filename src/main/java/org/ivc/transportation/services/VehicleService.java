package org.ivc.transportation.services;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.VehicleRepository;
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
public class VehicleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> findVehiclesByTransportDep(Principal principal) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null) {
            return vehicleRepository.findByTransportDep(transportDep);
        }
        return null;
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicles(List<Long> ids) {
        vehicleRepository.deleteByIdIn(ids);
    }

    private TransportDep getTransportDep(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }
}
