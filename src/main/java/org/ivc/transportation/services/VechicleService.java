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

    public Collection<Vechicle> listVechicles();

    public Optional getVechicleById(long id);

    public void removeVechicle(long id);

}
