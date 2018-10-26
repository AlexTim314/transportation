/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import java.sql.Date;
import java.util.List;
import org.ivc.transportation.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nesterov Yuriy
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    
    List<Plan> findByDate(Date d);
    
}
