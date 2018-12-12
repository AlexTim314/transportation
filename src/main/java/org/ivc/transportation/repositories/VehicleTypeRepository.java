/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;


import java.util.List;
import org.ivc.transportation.entities.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author first
 */
@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType ,Long> {
    
    List<VehicleType> findBySpecialization(String s);
    
}
