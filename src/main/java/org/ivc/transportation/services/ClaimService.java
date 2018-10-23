/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.sql.Date;
import org.ivc.transportation.entities.Claim;

/**
 *
 * @author user
 */
public interface ClaimService {

    public void addClaim(Claim d);

    public Collection<Claim> getClaimsByDepartment(Long id);
    
    public Collection<Claim> getClaimsByDepartmentAndAffirmation(Long id, Boolean a);
    
    public Collection<Claim> getClaimsByClDate(Date d);
    
    public Collection<Claim> getClaimsByAffirmation(Boolean b);

    public Collection<Claim> getClaimsByTip(byte t);

    public void removeClaim(Long id);
}
