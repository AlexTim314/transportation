package org.ivc.transportation.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Fuel;
import org.ivc.transportation.entities.Place;
import org.ivc.transportation.entities.RouteTask;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.FuelRepository;
import org.ivc.transportation.repositories.PlaceRepository;
import org.ivc.transportation.repositories.RouteTaskRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.repositories.VehicleModelRepository;
import org.ivc.transportation.repositories.VehicleTypeRepository;
import org.ivc.transportation.utils.CompositeTransportDepVehicleModels;
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
public class CommonService {

    @Autowired
    private TransportDepRepository transportDepRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private PlaceRepository placeRepository;
    
    @Autowired
    private RouteTaskRepository routeTaskRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<TransportDep> findAllTransportDeps() {
        return transportDepRepository.findAll();
    }

    public List<VehicleType> findAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    public List<VehicleModel> findAllVehicleModels() {
        return vehicleModelRepository.findAll();
    }

    public List<Place> findAllPlaces() {
        return placeRepository.findAll();
    }
    
    public List<RouteTask> findAllRouteTasks() {
        return routeTaskRepository.findAll();
    }

    public List<Fuel> findAllFuels() {
        return fuelRepository.findAll();
    }

    public Department findDepartmentByName(String shortname) {
        return departmentRepository.findByShortname(shortname);
    }

    public Department findDepartmentByUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getDepartment();
        }
        return null;
    }

    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
    
    public Department findDepartmentByOrder(int num) {
        return departmentRepository.findByPlanOrder(num).orElse(null);
    }

    public TransportDep findTransportDepByUser(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            return userRepository.findByUsername(loginedUser.getUsername()).getTransportDep();
        }
        return null;
    }

    public List<CompositeTransportDepVehicleModels> findVehicleModelsByTransportDep() {
        List<CompositeTransportDepVehicleModels> list = new ArrayList<CompositeTransportDepVehicleModels>();
        transportDepRepository.findAll().forEach(u -> list.add(new CompositeTransportDepVehicleModels(u,
                vehicleModelRepository.findVehicleModelsByTransportDepId(u.getId()))));
        return list;
    }

}
