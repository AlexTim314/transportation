/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.List;
import javax.persistence.EntityManager;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class TransportDepService {
    
//    @Autowired
//    private EntityManager em;
//    
//    @Transactional
//    public List<TransportDep> findAll(){
//        return em.createNamedQuery("TransportDep.findAll", TransportDep.class).getResultList();
//    }
}
