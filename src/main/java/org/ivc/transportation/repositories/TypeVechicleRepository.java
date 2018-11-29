/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;


import java.util.List;
import org.ivc.transportation.entities.TypeVechicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author first
 */
@Repository
public interface TypeVechicleRepository extends JpaRepository<TypeVechicle ,Long> {
    
    List<TypeVechicle> findBySpecialization(String s);
    
}
