/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

/**
 *
 * @author user
 */
import java.util.Collection;
import java.util.Optional;

import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.services.TransportDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/transportDeps")
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
    public Collection<VehicleType> getTypeVechicle() {
        return transportDepService.getTypeVechicles();
    }

    @PostMapping("/transportDeps/vechicles/typeVechicle/create")
    public Collection<VehicleType> addTypeVechicle(@RequestBody VehicleType t) {
        transportDepService.addTypeVechicle(t);
        return transportDepService.getTypeVechicles();
    }

    @PutMapping("/transportDeps/vechicles/typeVechicle/update")
    public Collection<VehicleType> updateTypeVechicle(@RequestBody VehicleType t) {
        transportDepService.updateTypeVechicle(t, t.getId());
        return transportDepService.getTypeVechicles();
    }

    @DeleteMapping("/transportDeps/vechicles/typeVechicle/delete")
    public Collection<VehicleType> delTypeVechicle(@RequestBody VehicleType t) {
        transportDepService.removeTypeVechicle(t.getId());
        return transportDepService.getTypeVechicles();
    }
}
