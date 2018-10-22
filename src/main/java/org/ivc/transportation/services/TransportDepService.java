/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

/**
 *
 * @author user
 */
import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.TransportDep;

/**
 *
 * @author user
 */
public interface TransportDepService {

    public void addTransportDep(TransportDep d);

    public void updateTransportDep(TransportDep d, Long id);

    public Collection<TransportDep> listTransportDeps();

    public Optional getTransportDepById(long id);

    public void removeTransportDep(long id);
}
