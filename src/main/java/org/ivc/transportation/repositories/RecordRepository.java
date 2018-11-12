/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import java.sql.Date;
import java.util.List;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nesterov Yuriy
 */
@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{
   
   List<Record> findByDatetime(Date d);
   
   List<Record> findByPlanId(Long id);
   
   List<Record> findByClaimId(Long id);
   
   List<Record> findByStatus(RecordStatus t);
   
   List<Record> findByWeekHash(String d);
   
   List<Record> findByStatusAndDatetime(RecordStatus t,Date d);
   
   List<Record> deleteByClaimId(Long id);
    
}
