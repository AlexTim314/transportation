/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.ivc.transportation.repositories.AppointmentGroupRepository;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.ivc.transportation.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ivc.transportation.repositories.VehicleTypeRepository;
import org.ivc.transportation.repositories.VehicleRepository;

/**
 *
 * @author user
 */
@Service
@Transactional
public class TransportDepServiceImpl implements TransportDepService {

    @Autowired
    private TransportDepRepository transportDepRep;
    @Autowired
    private DriverRepository driverRep;
    @Autowired
    private VehicleRepository vehicleRep;
    @Autowired
    private VehicleTypeRepository vehicleTypeRep;
    @Autowired
    private VehicleModelRepository vehicleModelRep;
    @Autowired
    private AppointmentRepository appointmentRep;
    @Autowired
    private AppointmentGroupRepository appointmentGroupRep;

    @Override
    @Transactional
    public void addTransportDep(TransportDep d) {
        this.transportDepRep.save(d);
    }

    @Override
    @Transactional
    public void updateTransportDep(TransportDep d, Long id) {
        d.setId(id);
        transportDepRep.save(d);
    }

    @Override
    @Transactional
    public void removeTransportDep(Long id) {
        transportDepRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<TransportDep> getTransportDeps() {
        return transportDepRep.findAll();
    }

    @Override
    @Transactional
    public Optional<TransportDep> getTransportDepById(Long id) {
        return transportDepRep.findById(id);
    }

    @Override
    @Transactional
    public void addDriver(Driver d) {
        this.driverRep.save(d);
    }

    @Override
    @Transactional
    public void updateDriver(Driver d, Long id) {
        d.setId(id);
        driverRep.save(d);
    }

    @Override
    @Transactional
    public void removeDriver(Long id) {
        driverRep.deleteById(id);
    }

    @Override
    @Transactional
    public Optional getDriverById(Long id) {
        return driverRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Driver> getDrivers() {
        return driverRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Driver> findDriversByVacant(Boolean d) {
        return driverRep.findByVacant(d);
    }

    @Override
    @Transactional
    public Collection<Driver> getDriversByTransportDepId(Long id) {
        return driverRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public void addVechicle(Vehicle d) {
        this.vehicleRep.save(d);
    }

    @Override
    @Transactional
    public void updateVechicle(Vehicle d, Long id) {
        d.setId(id);
        vehicleRep.save(d);
    }

    @Override
    @Transactional
    public void removeVechicle(Long id) {
        vehicleRep.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<Vehicle> getVechicles() {
        return vehicleRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Vehicle> findVechiclesByVacant(Boolean d) {
        return vehicleRep.findByVacant(d);
    }

    @Override
    @Transactional
    public Collection<Vehicle> getVechiclesByTransportDepId(Long id) {
        return vehicleRep.findByTransportDepId(id);
    }

    @Override
    @Transactional
    public Optional getVechicleById(Long id) {
        return vehicleRep.findById(id);
    }

    @Override
    public void addVehicleType(VehicleType d) {
        this.vehicleTypeRep.save(d);
    }
    
    @Override
    public void updateVehicleType(VehicleType d, Long id) {
         d.setId(id);
        vehicleTypeRep.save(d);
    }

    @Override
    public void removeVehicleType(Long id) {
        vehicleTypeRep.deleteById(id);
    }

    @Override
    public Collection<VehicleType> getVehicleTypes() {
        return vehicleTypeRep.findAll();
    }


    @Override
    public Collection<VehicleType> getVehicleTypesBySpecialization(String s) {
       return vehicleTypeRep.findBySpecialization(s);
    }

    @Override
    public void addVehicleModel(VehicleModel m) {
        vehicleModelRep.save(m);
    }

    @Override
    public void updateVehicleModel(VehicleModel m, Long id) {
        m.setId(id);
        vehicleModelRep.save(m);
    }

    @Override
    public void removeVehicleModel(Long id) {
        vehicleModelRep.deleteById(id);
    }

    @Override
    public Collection<VehicleModel> getVehicleModels() {
        return vehicleModelRep.findAll();
    }

    @Override
    public Collection<Vehicle> getVehiclesByVehicleModelId(Long id) {
        return vehicleRep.findByVehicleModelId(id);
    }

    @Override
    public void addAppointment(Appointment appointment, Record record) {
        AppointmentGroup apg = new AppointmentGroup(record, appointment);
        appointmentGroupRep.save(apg);
        appointmentRep.save(appointment);
    }

    @Override
    public List<AppointmentGroup> getAppointmentGroups(Appointment appointment) {
        return appointmentGroupRep.findByAppointmentId(appointment.getId());
    }

    @Override
    public List<AppointmentGroup> getAppointmentGroups(Record record) {
        return appointmentGroupRep.findByRecordId(record.getId());
    }

    @Override
    public List<Appointment> getAppointmentsByTransportDepAndDateRange(TransportDep transportDep, DateRange dateRange) {
        LocalDateTime dStart = LocalDateTime.parse(dateRange.StartDate.toString()+"T00:00:00");
        LocalDateTime dEnd = LocalDateTime.parse(dateRange.EndDate.toString()+"T00:00:00");
        return appointmentRep.findAllByTransportDepIdAndAppDateTimeBetweenOrderByAppDateTimeDesc(transportDep.getId(), dStart, dEnd);
    }

}
