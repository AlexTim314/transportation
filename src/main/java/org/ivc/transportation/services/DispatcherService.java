package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Vehicle;

/**
 *
 * @author Sokolov Slava
 */
public interface DispatcherService {

    List<Driver> getDrivers(Principal principal);

    Driver saveDriver(Principal principal, Driver driver);

    void deleteDriver(Principal principal, Driver driver);

    List<Vehicle> getVehicles(Principal principal);

    Vehicle saveVehicle(Principal principal, Vehicle vehicle);

    void deleteVehicle(Principal principal, Vehicle vehicle);

    List<Appointment> getAppointments(Principal principal, LocalDateTime startDate, LocalDateTime endDate);

    Appointment editAppointment(Principal principal, Appointment appointment);

}
