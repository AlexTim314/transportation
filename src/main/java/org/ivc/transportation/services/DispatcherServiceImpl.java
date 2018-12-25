package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.repositories.AppointmentRepository;
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

    @Autowired
    private AppointmentRepository appointmentRepository;

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
        driver.setTransportDep(getTransportDep(principal));
        return driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(Principal principal, Driver driver) {
        driverRepository.delete(driver);
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
        vehicle.setTransportDep(getTransportDep(principal));
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(Principal principal, Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

    @Override
    public List<Appointment> getAppointments(Principal principal, LocalDateTime startDate, LocalDateTime endDate) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null) {
            appointmentRepository.findAllByTransportDepIdAndAppDateTimeBetweenOrderByAppDateTimeDesc(transportDep.getId(), startDate, endDate);
        }
        return null;
    }

    @Override
    public Appointment editAppointment(Principal principal, Appointment appointment) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null && transportDep.equals(appointment.getTransportDep())) {
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    private TransportDep getTransportDep(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUserName(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }

}
