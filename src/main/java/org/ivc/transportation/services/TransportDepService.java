package org.ivc.transportation.services;

import java.util.List;
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
@Transactional
public class TransportDepService {
    
    @Autowired
    private TransportDepRepository transportDepRepository;
    
    public List<TransportDep> findAllTransportDeps() {
        return transportDepRepository.findAll();
    }
    
    public TransportDep saveTransportDep(TransportDep transportDep) {
        return transportDepRepository.save(transportDep);
    }
    
    public void deleteTransportDep(TransportDep transportDep) {
        transportDepRepository.delete(transportDep);
    }
    
}
