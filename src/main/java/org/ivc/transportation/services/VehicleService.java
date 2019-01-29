package org.ivc.transportation.services;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.ivc.transportation.entities.Fuel;
import org.ivc.transportation.entities.Refueling;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleInfo;
import org.ivc.transportation.repositories.FuelRepository;
import org.ivc.transportation.repositories.RefuelingRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.VehicleInfoRepository;
import org.ivc.transportation.repositories.VehicleRepository;
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
public class VehicleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleInfoRepository vehicleInfoRepository;
    
    @Autowired
    RefuelingRepository refuelingRepository;
    
    @Autowired
    FuelRepository fuelRepository;

    public List<Vehicle> findVehiclesByTransportDep(Principal principal) {
        TransportDep transportDep = getTransportDep(principal);
        if (transportDep != null) {
            return vehicleRepository.findByTransportDep(transportDep);
        }
        return null;
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            List<Fuel> fl = new ArrayList<Fuel>();
            vehicle.getFuels().forEach(u -> fl.add(fuelRepository.findById(u.getId()).get()));
            vehicle.setFuels(fl);
            vehicle = vehicleRepository.save(vehicle);
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.setVehicle(vehicle);
            vehicleInfo.setModificationDate(LocalDateTime.now());
            vehicleInfo.setFuel(vehicle.getFuel());
            vehicleInfo.setMotohours(vehicle.getMotohours());
            vehicleInfo.setOdometr(vehicle.getOdometr());
            vehicleInfo.setNote("При создании");
            vehicleInfo.setStatus(vehicle.getStatus());
            vehicleInfoRepository.save(vehicleInfo);
            return vehicle;
        }
        return vehicleRepository.save(vehicle);
    }

    public VehicleInfo updateVehicleStatus(VehicleInfo vehicleInfo) {
        Vehicle vehicle = vehicleInfo.getVehicle();
        vehicle.setFuel(vehicleInfo.getFuel());
        vehicle.setMotohours(vehicleInfo.getMotohours());
        vehicle.setNote(vehicleInfo.getNote());
        vehicle.setOdometr(vehicleInfo.getOdometr());
        vehicle.setStatus(vehicleInfo.getStatus());
        vehicleInfo.setModificationDate(LocalDateTime.now());
        return vehicleInfoRepository.save(vehicleInfo);
    }

    public Refueling vehicleRefueling(Refueling refueling) {
        Vehicle vehicle = refueling.getVehicle();
        vehicle.setFuel(vehicle.getFuel()+refueling.getVolume());
        vehicleRepository.save(vehicle);
        return refuelingRepository.save(refueling);
    }

    public void deleteVehicles(List<Long> ids) {
        ids.forEach(u -> vehicleInfoRepository.deleteByVehicleId(u));
        vehicleRepository.deleteByIdIn(ids);
    }

    private TransportDep getTransportDep(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }
}
