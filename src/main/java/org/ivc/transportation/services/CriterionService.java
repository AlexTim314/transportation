package org.ivc.transportation.services;

import java.util.Collection;
import org.ivc.transportation.entities.Criterion;
import org.ivc.transportation.entities.CriterionType;
import org.ivc.transportation.entities.CriterionValue;


/**
 *
 * @author first
 */
public interface CriterionService {
    
    public void addCriterionType(CriterionType d);
    
    public void updateCriterionType(CriterionType d, Long id);

    public void removeCriterionType(Long id);
    
    public void addCriterion(Criterion d);
    
    public void updateCriterion(Criterion d, Long id);

    public void removeCriterion(Long id);
    
    public void addCriterionValue(CriterionValue d);
    
    public void updateCriterionValue(CriterionValue d, Long id);

    public void removeCriterionValue(Long id);
    
    public Collection<CriterionType> getCriterionTypes();
    
    public Collection<Criterion> getCriterionsByCriterionType(Long id);
    
    public Collection<Criterion> getCriterionsByName(String s);
    
    public Collection<CriterionValue> getCriterionValuesByCriterionId(Long id);
    
    public Collection<CriterionValue> getCriterionValuesByVehicleId(Long id);
    
    public Collection<CriterionValue> getCriterionValuesByRecordId(Long id);
    
}
