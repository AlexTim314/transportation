/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.Collection;
import java.sql.Date;
import java.util.stream.Stream;
import org.ivc.transportation.entities.Claim;

/**
 *
 * @author Degtyarev Fedor & Nesterov Yuriy
 */
public interface ClaimService {

    public void addClaim(Claim d);

    public void removeClaim(Long id);

    public Collection<Claim> getAllClaimsSortByDate();

    public Collection<Claim> getAllClaimsSortByDateAsk();

    public Collection<Claim> getClaimsByDepartment(Long id);

    public Collection<Claim> getClaimsByclDate(Date d);

    public Collection<Claim> getClaimsByAffirmation(Boolean b);

    public Collection<Claim> getClaimsByAffirmationAsc(Boolean b);

    public Collection<Claim> getClaimsByTip(byte t);

    public Collection<Claim> getClaimsByTipAsc(byte t);

    public Collection<Claim> getClaimsByDepAndAffirmation(Long id, Boolean a);

    public Collection<Claim> getClaimsByDepAndAffirmationAsc(Long id, Boolean a);
}
