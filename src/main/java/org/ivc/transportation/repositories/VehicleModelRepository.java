/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;


import java.util.List;
import org.ivc.transportation.entities.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author first
 */
@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel ,Long> {
    
    List<VehicleModel> findByVehicleTypeId(Long id);
    
}
