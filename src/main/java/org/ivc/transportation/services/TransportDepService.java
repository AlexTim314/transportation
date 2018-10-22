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

    public void updateTransportDep(TransportDep d, long id);

    public Collection<TransportDep> listTransportDeps();
    
    public Collection<Driver> listDrivers(long id);
    
    public Collection<Vechicle> listVechicles(long id);

    public Optional getTransportDepById(long id);

    public void removeTransportDep(long id);
}
