/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import org.ivc.transportation.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Nesterov Yuriy
 */
@RepositoryRestResource
public interface TripRepository extends JpaRepository<Trip, Long> {
    
}
