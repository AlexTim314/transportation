/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.repositories;

import java.util.List;
import java.sql.Date;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sokolov Slava & Degtyarev Fedor & Nesterov Yuriy
 */
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByDepartmentId(Long id);

    List<Claim> findByClDate(Date d);
    
    List<Claim> findAllByClDateBetweenOrderByClDateDesc(Date clDateStart, Date clDateEnd);

    List<Claim> findAllByDepartmentIdAndClDateBetweenOrderByClDateDesc(Long id, Date clDateStart, Date clDateEnd);
    
    List<Claim> findByAffirmationOrderByClDateDesc(Boolean a);

    List<Claim> findByAffirmationOrderByClDateAsc(Boolean a);

    List<Claim> findByClTypeOrderByClDateDesc(ClaimType t);

    List<Claim> findByClTypeOrderByClDateAsc(ClaimType t);
    
    List<Claim> findByClTypeAndDepartmentIdOrderByClDateDesc(Long id, ClaimType t);

    List<Claim> findByDepartmentIdAndAffirmationOrderByClDateDesc(Long id, Boolean a);

    List<Claim> findByDepartmentIdAndAffirmationOrderByClDateAsc(Long id, Boolean a);

}
