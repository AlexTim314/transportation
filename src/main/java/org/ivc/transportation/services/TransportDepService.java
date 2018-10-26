/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vechicle;

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

    public void addVechicle(Vechicle d);

    public void updateVechicle(Vechicle d, Long id);

    public void removeVechicle(Long id);

    public Collection<Vechicle> getVechicles();

    public Collection<Vechicle> findVechiclesByVacant(Boolean d);

    public Collection<Vechicle> getVechiclesByTransportDepId(Long id);

    public Optional getVechicleById(Long id);

}
