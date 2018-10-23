/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Vechicle;

/**
 *
 * @author Nesterov Yuriy
 */
public interface VechicleService {

    public void addVechicle(Vechicle d);

    public void updateVechicle(Vechicle d, Long id);

    public void removeVechicle(Long id);

    public Collection<Vechicle> getVechicles();
    
    public Collection<Vechicle> getVechiclesByTransportDepId(Long id);

    public Optional getVechicleById(Long id);

}
