package org.ivc.transportation.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.ivc.transportation.entities.Criterion;
import org.ivc.transportation.entities.CriterionType;
import org.ivc.transportation.entities.CriterionValue;
import org.ivc.transportation.repositories.CriterionRepository;
import org.ivc.transportation.repositories.CriterionTypeRepository;
import org.ivc.transportation.repositories.CriterionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author first
 */
@Service
@Transactional
public class CriterionServiceImpl implements CriterionService {

    @Autowired
    private CriterionTypeRepository ctr;
    @Autowired
    private CriterionRepository cr;
    @Autowired
    private CriterionValueRepository cvr;

    @Override
    @Transactional
    public void addCriterionType(CriterionType d) {
        this.ctr.save(d);
    }

    @Override
    @Transactional
    public void updateCriterionType(CriterionType d, Long id) {
        d.setId(id);
        ctr.save(d);
    }

    @Override
    @Transactional
    public void removeCriterionType(Long id) {
        List<Criterion> crit = (List<Criterion>) cr.findByCriterionTypeId(id);
        List<Long> ids = crit.stream().map(u -> u.getId()).collect(Collectors.toList());
        cvr.deleteByCriterionIdIn(ids);
        cr.deleteByCriterionTypeId(id);
        ctr.deleteById(id);
    }

    @Override
    @Transactional
    public void addCriterion(Criterion d) {
        this.cr.save(d);
    }

    @Override
    @Transactional
    public void updateCriterion(Criterion d, Long id) {
        d.setId(id);
        cr.save(d);
    }

    @Override
    @Transactional
    public void removeCriterion(Long id) {
        cvr.deleteByCriterionId(id);
        cr.deleteById(id);
    }

    @Override
    @Transactional
    public void addCriterionValue(CriterionValue d) {
        this.cvr.save(d);
    }

    @Override
    @Transactional
    public void updateCriterionValue(CriterionValue d, Long id) {
        d.setId(id);
        cvr.save(d);
    }

    @Override
    @Transactional
    public void removeCriterionValue(Long id) {
        cvr.deleteById(id);
    }

    @Override
    @Transactional
    public Collection<CriterionType> getCriterionTypes() {
        return ctr.findAll();
    }

    @Override
    @Transactional
    public Collection<Criterion> getCriterionsByCriterionType(Long id) {
        return cr.findByCriterionTypeId(id);
    }

    @Override
    @Transactional
    public Collection<Criterion> getCriterionsByName(String s) {
        return cr.findByName(s);
    }

    @Override
    @Transactional
    public Collection<CriterionValue> getCriterionValuesByCriterionId(Long id) {
        return cvr.findByCriterionId(id);
    }

    @Override
    @Transactional
    public Collection<CriterionValue> getCriterionValuesByVechicleId(Long id) {
        return cvr.deleteByVehicleId(id);
    }

    @Override
    @Transactional
    public Collection<CriterionValue> getCriterionValuesByRecordId(Long id) {
        return cvr.findByRecordId(id);
    }

}
