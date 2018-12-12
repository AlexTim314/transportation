package org.ivc.transportation.services;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.repositories.DriverRepository;
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
public class DispatcherServiceImpl implements DispatcherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Driver> getDrivers(Principal principal) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null) {
            return driverRepository.findByTransportDepId(transportDep.getId());
        }
        return null;
    }

    @Override
    public Driver saveDriver(Principal principal, Driver driver) {
        return checkTransportDep(principal, driver) ? driverRepository.save(driver) : null;
    }

    @Override
    public void deleteDriver(Principal principal, Driver driver) {
        if (checkTransportDep(principal, driver)) {
            driverRepository.delete(driver);
        }
    }

    @Override
    public List<Vehicle> getVehicles(Principal principal) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null) {
            return vehicleRepository.findByTransportDepId(transportDep.getId());
        }
        return null;
    }

    @Override
    public Vehicle saveVehicle(Principal principal, Vehicle vehicle) {
        return checkTransportDep(principal, vehicle) ? vehicleRepository.save(vehicle) : null;
    }

    @Override
    public void deleteVehicle(Principal principal, Vehicle vehicle) {
        if(checkTransportDep(principal, vehicle)){
            vehicleRepository.delete(vehicle);
        }
    }

    private TransportDep getTransportDep(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUserName(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }

    private boolean checkTransportDep(Principal principal, Driver driver) {
        TransportDep transportDep = getTransportDep(principal);
        return transportDep != null && transportDep.equals(driver.getTransportDep());
    }

    private boolean checkTransportDep(Principal principal, Vehicle vehicle) {
        TransportDep transportDep = getTransportDep(principal);
        return transportDep != null && transportDep.equals(vehicle.getTransportDep());
    }

}
