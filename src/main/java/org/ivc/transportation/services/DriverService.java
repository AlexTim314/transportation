package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.DriverInfo;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.DriverInfoRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.utils.EntitiesUtils.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sokolov Slava
 */
@Service
@Transactional
public class DriverService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverInfoRepository driverInfoRepository;

    public List<Driver> findDriversByTransportDep(Principal principal) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null) {
            return driverRepository.findByTransportDep(transportDep);
        }
        return null;
    }

    public Driver saveDriver(Driver driver) {
        if (driver.getId() == null) {
            driver.setStatus(DriverStatus.работоспособен);
            driver = driverRepository.save(driver);
            DriverInfo driverInfo = new DriverInfo();
            driverInfo.setDriver(driver);
            driverInfo.setModificationDate(LocalDateTime.now());
            driverInfo.setNote("При создании");
            driverInfo.setStatus(driver.getStatus());
            driverInfoRepository.save(driverInfo);
            return driver;
        }
        return driverRepository.save(driver);
    }

    public DriverInfo updateDriverStatus(DriverInfo driverInfo) {
        Driver driver = driverInfo.getDriver();
        driver.setNote(driverInfo.getNote());
        driver.setStatus(driverInfo.getStatus());
        driverInfo.setModificationDate(LocalDateTime.now());
        driverRepository.save(driver);
        return driverInfoRepository.save(driverInfo);
    }

    public void deleteDriver(Driver driver) {
        driverInfoRepository.deleteByDriverId(driver.getId());
        driverRepository.delete(driver);
    }

    private TransportDep getTransportDep(Principal principal) {
        if (principal == null) {
            return null;
        }
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
    }

}
