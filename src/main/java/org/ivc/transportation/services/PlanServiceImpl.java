/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.entities.Plan;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.repositories.PlanRepository;
import org.ivc.transportation.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author first
 */
@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRep;
    @Autowired
    private RecordRepository recordRep;

    @Override
    @Transactional
    public void addPlan(Plan d) {
        this.planRep.save(d);
    }

    @Override
    @Transactional
    public void updatePlan(Plan d, Long id) {
        d.setId(id);
        planRep.save(d);
    }

    @Override
    @Transactional
    public void removePlan(Long id) {
        planRep.deleteById(id);
    }

    @Override
    @Transactional
    public Optional getPlanById(Long id) {
        return planRep.findById(id);
    }

    @Override
    @Transactional
    public Collection<Plan> getPlans() {
        return planRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Plan> getByDate(Date d) {
        return planRep.findByDate(d);
    }

    @Override
    @Transactional
    public Collection<Record> getRecords() {
        return recordRep.findAll();
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByPlan(Long id) {
        return recordRep.findByPlanId(id);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByState(RecordStatus t) {
        return recordRep.findByStatus(t);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByDate(Date d) {
        return recordRep.findByDatetime(d);
    }

    @Override
    @Transactional
    public Collection<Record> getRecordsByStateAndDate(RecordStatus t, Date d) {
        return recordRep.findByStatusAndDatetime(t, d);
    }

}
