package org.ivc.transportation.controllers;

/**
 *
 * @author user
 */
import java.security.Principal;
import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import org.ivc.transportation.config.trUtils;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.exceptions.NullPrincipalException;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.TransportDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransportDepController {

    @Autowired
    private TransportDepService transportDepService;

    /**
     * Метод возвращает список транспортных отделов. Предполагается , что доступ
     * к этому методу будет только у администратора. Пользователям
     * предоставляется метод getTransportDep(Principal principal).
     *
     * @return список транспортных отделов.
     */
    @GetMapping("/transportDeps")
    public Collection<TransportDep> getAllTransportDeps() {
        return transportDepService.getTransportDeps();
    }

    @GetMapping("/transportDeps/one")
    public TransportDep getTransportDep(@RequestBody TransportDep department) {
        Optional<TransportDep> dep = transportDepService.getTransportDepById(department.getId());
        return dep.get();
    }

    @DeleteMapping("/transportDeps/delete/")
    public Collection<TransportDep> delTransportDep(@RequestBody TransportDep department) {
        transportDepService.removeTransportDep(department.getId());
        return transportDepService.getTransportDeps();
    }

    @PostMapping("/transportDeps/create")
    public Collection<TransportDep> addTransportDep(@RequestBody TransportDep department) {
        transportDepService.addTransportDep(department);
        return transportDepService.getTransportDeps();
    }

    @PutMapping("/transportDeps/update")
    public Collection<TransportDep> updateTransportDep(@RequestBody TransportDep dep, @PathVariable Long id) {
        transportDepService.updateTransportDep(dep, id);
        return transportDepService.getTransportDeps();
    }

    @PostMapping("/transportDeps/drivers")
    public Collection<Driver> getDrivers(@RequestBody TransportDep department) {
        return transportDepService.getDriversByTransportDepId(department.getId());
    }

    @PostMapping("/transportDeps/drivers/create")
    public Collection<Driver> addDriver(@RequestBody Driver driver) {
        driver.setVacant(Boolean.TRUE);
        transportDepService.addDriver(driver);
        return transportDepService.getDriversByTransportDepId(driver.getTransportDep().getId());
    }

    @PutMapping("/transportDeps/drivers/update")
    public Collection<Driver> updateDriver(@RequestBody Driver d) {
        transportDepService.updateDriver(d, d.getTransportDep().getId());
        return transportDepService.getDriversByTransportDepId(d.getTransportDep().getId());
    }

    @DeleteMapping("/transportDeps/drivers/delete")
    public Collection<Driver> delDriver(@RequestBody Driver d) {
        transportDepService.removeDriver(d.getId());
        return transportDepService.getDriversByTransportDepId(d.getTransportDep().getId());
    }

    @PostMapping("/transportDeps/vechicles")
    public Collection<Vehicle> getVechicles(@RequestBody TransportDep department) {
        return transportDepService.getVechiclesByTransportDepId(department.getId());
    }

    @PostMapping("/transportDeps/vechicles/create")
    public Collection<Vehicle> addVechicle(@RequestBody Vehicle d) {
        d.setVacant(Boolean.TRUE);
        transportDepService.addVechicle(d);
        return transportDepService.getVechiclesByTransportDepId(d.getTransportDep().getId());
    }

    @PutMapping("/transportDeps/vechicles/update")
    public Collection<Vehicle> updateVechicle(@RequestBody Vehicle d) {
        transportDepService.updateVechicle(d, d.getId());
        return transportDepService.getVechiclesByTransportDepId(d.getTransportDep().getId());
    }

    @DeleteMapping("/transportDeps/vechicles/delete")
    public Collection<Vehicle> delVechicle(@RequestBody Vehicle d) {
        transportDepService.removeVechicle(d.getId());
        return transportDepService.getVechiclesByTransportDepId(d.getTransportDep().getId());
    }

    @GetMapping("/transportDeps/vechicles/typeVechicle")
    public Collection<VehicleType> getVehicleType() {
        return transportDepService.getVehicleTypes();
    }

    @PostMapping("/transportDeps/vechicles/typeVechicle/create")
    public Collection<VehicleType> addVehicleType(@RequestBody VehicleType t) {
        transportDepService.addVehicleType(t);
        return transportDepService.getVehicleTypes();
    }

    @PutMapping("/transportDeps/vechicles/typeVechicle/update")
    public Collection<VehicleType> updateVehicleType(@RequestBody VehicleType t) {
        transportDepService.updateVehicleType(t, t.getId());
        return transportDepService.getVehicleTypes();
    }

    @DeleteMapping("/transportDeps/vechicles/typeVechicle/delete")
    public Collection<VehicleType> delTypeVechicle(@RequestBody VehicleType t) {
        transportDepService.removeVehicleType(t.getId());
        return transportDepService.getVehicleTypes();
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transportDeps/appointments/{sD}/{eD}") //transportDeps/appointments/2018-01-01/2018-12-31
    public Collection<Appointment> findAppointmentsByDateRange(Principal principal, @PathVariable("sD") String sD, @PathVariable("eD") String eD) {
        if (principal == null) { 
            throw new NullPrincipalException("Неавторизованный доступ к данной странице закрыт. "
                    + "Пожалуйста <a href=\"/transportation/login\"> войдите в систему</a>.");
        }
        
        trUtils.DateRange dr = new trUtils.DateRange();
        dr.StartDate = Date.valueOf(sD);
        dr.EndDate = Date.valueOf(eD);
        
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        TransportDep transportDep = userRepository.findByUserName(loginedUser.getUsername()).getTransportDep();
        if (transportDep == null) {
            throw new IllegalArgumentException("У пользователя не указан транспортный отдел. Добавить обработку исключения."); //NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
        }
        return transportDepService.getAppointmentsByTransportDepAndDateRange(transportDep, dr);

    }

}
