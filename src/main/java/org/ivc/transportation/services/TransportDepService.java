/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.ivc.transportation.config.trUtils.DateRange;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.AppointmentGroup;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleModel;

/**
 *
 * @author user
 */
public interface TransportDepService {

    public void addTransportDep(TransportDep d);

    public void updateTransportDep(TransportDep d, Long id);

    public void removeTransportDep(Long id);

    public Collection<TransportDep> getTransportDeps();

    public Optional getTransportDepById(Long id);

    public void addDriver(Driver d);

    public void updateDriver(Driver d, Long id);

    public void removeDriver(Long id);

    public Optional getDriverById(Long id);

    public Collection<Driver> getDrivers();

    public Collection<Driver> findDriversByVacant(Boolean d);

    public Collection<Driver> getDriversByTransportDepId(Long id);

    public void addVechicle(Vehicle d);

    public void updateVechicle(Vehicle d, Long id);

    public void removeVechicle(Long id);

    public Collection<Vehicle> getVechicles();

    public Collection<Vehicle> findVechiclesByVacant(Boolean d);

    public Collection<Vehicle> getVechiclesByTransportDepId(Long id);

    public Optional getVechicleById(Long id);

    public void addVehicleType(VehicleType d);

    public void updateVehicleType(VehicleType d, Long id);

    public void removeVehicleType(Long id);

    public Collection<VehicleType> getVehicleTypes();

    public Collection<VehicleType> getVehicleTypesBySpecialization(String s);
    
    public void addVehicleModel(VehicleModel m);

    public void updateVehicleModel(VehicleModel m, Long id);

    public void removeVehicleModel(Long id);

    public Collection<VehicleModel> getVehicleModels();

    public Collection<Vehicle> getVehiclesByVehicleModelId(Long id);
    
    public void addAppointment(Appointment appointment, Record record);
    
    public List<AppointmentGroup> getAppointmentGroups(Appointment appointment);
    
    public List<AppointmentGroup> getAppointmentGroups(Record record);
    
    public List<Appointment> getAppointmentsByTransportDepAndDateRange(TransportDep transportDep, DateRange dateRange);
    
}
