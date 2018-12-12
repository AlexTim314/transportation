/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import java.util.List;
import org.ivc.transportation.entities.CriterionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nesterov Yuriy
 */
@Repository
public interface CriterionValueRepository extends JpaRepository<CriterionValue, Long> {

    List<CriterionValue> deleteByVehicleId(Long id);

    List<CriterionValue> findByCriterionId(Long id);

    List<CriterionValue> findByVehicleId(Long id);

    List<CriterionValue> findByRecordId(Long id);
    
    List<CriterionValue> deleteByCriterionIdIn(List <Long> id);
    
    List<CriterionValue> deleteByCriterionId(Long id);

}
