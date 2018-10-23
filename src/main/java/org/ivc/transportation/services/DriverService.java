/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Driver;

/**
 *
 * @author Nesterov Yuriy
 */
public interface DriverService {

    public void addDriver(Driver d);

    public void updateDriver(Driver d, Long id);

    public void removeDriver(Long id);

    public Optional getDriverById(Long id);

    public Collection<Driver> getDrivers();

    public Collection<Driver> getDriversByTransportDepId(Long id);
}
