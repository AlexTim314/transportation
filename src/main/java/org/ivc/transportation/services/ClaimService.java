/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import org.ivc.transportation.entities.Claim;

/**
 *
 * @author user
 */
public interface ClaimService {

    public void addClaim(Claim d);

    public Collection<Claim> listClaimsByDepartment(Long id);

    public void removeClaim(long id);
}
