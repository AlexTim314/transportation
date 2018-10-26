/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.entities.Plan;
import org.ivc.transportation.entities.Record;

/**
 *
 * @author first
 */
public interface PlanService {

    public void addPlan(Plan d);

    public void updatePlan(Plan d, Long id);

    public void removePlan(Long id);

    public Optional getPlanById(Long id);
    
    public Collection<Plan> getPlans();
    
    public Collection<Plan> getByDate(Date d);
    
    public Collection<Record> getRecords();
    
    public Collection<Record> getRecordsByPlan(Long id);
    
    public Collection<Record> getRecordsByState(byte t);
    
    public Collection<Record> getRecordsByDate(Date d);
    
    public Collection<Record> getRecordsByStateAndDate(byte t,Date d);

}
