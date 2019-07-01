package org.ivc.transportation.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.VehicleModelRepository;
import org.ivc.transportation.utils.CompositeTDInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private UserRepository userRepository;

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
