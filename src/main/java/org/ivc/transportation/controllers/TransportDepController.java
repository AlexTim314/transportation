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
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.services.TransportDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/transportDeps")
public class TransportDepController {

    @Autowired
    private TransportDepService transportDepService;

    @GetMapping("/transportDeps")
    public Collection<TransportDep> getTransportDeps() {
        return transportDepService.getTransportDeps();
    }

    @GetMapping("/transportDeps/{id}")
    public TransportDep getTransportDep(@PathVariable Long id) {
        Optional<TransportDep> dep = transportDepService.getTransportDepById(id);
        return dep.get();
    }

    @DeleteMapping("/transportDeps/delete/{id}")
    public Collection<TransportDep> delTransportDep(@PathVariable Long id) {
        transportDepService.removeTransportDep(id);
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

    @GetMapping("/transportDeps/{id}/drivers")
    public Collection<Driver> getDrivers(@PathVariable Long id) {
        return transportDepService.getDriversByTransportDepId(id);
    }

    @PostMapping("/transportDeps/{id}/drivers/create")
    public Collection<Driver> addDriver(@PathVariable Long id, @RequestBody Driver d) throws Throwable {
        Optional<TransportDep> otd = transportDepService.getTransportDepById(id);
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + id);
        }));
        d.setVacant(Boolean.TRUE);
        transportDepService.addDriver(d);
        return transportDepService.getDriversByTransportDepId(id);
    }

    @PutMapping("/transportDeps/{idTransportDep}/drivers/{idDriver}/update")
    public Collection<Driver> updateDriver(@PathVariable Long idTransportDep, @PathVariable Long idDriver, @RequestBody Driver d) throws Throwable {
        System.out.println(idTransportDep + " " + idDriver + " " + d.getFirstname());
        Optional<TransportDep> otd = transportDepService.getTransportDepById(idTransportDep);
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + idTransportDep);
        }));
        transportDepService.updateDriver(d, idDriver);
        return transportDepService.getDriversByTransportDepId(idTransportDep);
    }

    @DeleteMapping("/transportDeps/{idTransportDep}/drivers/delete/{idDrivers}")
    public Collection<Driver> delDriver(@PathVariable Long idDrivers, @PathVariable Long idTransportDep) {
        transportDepService.removeDriver(idDrivers);
        return transportDepService.getDriversByTransportDepId(idTransportDep);
    }

    @GetMapping("/transportDeps/{id}/vechicles")
    public Collection<Vechicle> getVechicles(@PathVariable Long id) {
        return transportDepService.getVechiclesByTransportDepId(id);
    }

    
    @PostMapping("/transportDeps/{id}/vechicles/create")
    public Collection<Vechicle> addVechicle(@PathVariable Long id, @RequestBody Vechicle d) throws Throwable {
        Optional<TransportDep> otd = transportDepService.getTransportDepById(id);
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + id);
        }));
        d.setVacant(Boolean.TRUE);
        transportDepService.addVechicle(d);
        return transportDepService.getVechiclesByTransportDepId(id);
    }

    @PutMapping("/transportDeps/{idTransportDep}/vechicles/{idVechicle}/update")
    public Collection<Vechicle> updateVechicle(@PathVariable Long idTransportDep, @PathVariable Long idVechicle, @RequestBody Vechicle d) throws Throwable {
        Optional<TransportDep> otd = transportDepService.getTransportDepById(idTransportDep);
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + idTransportDep);
        }));
        transportDepService.updateVechicle(d, idVechicle);
        return transportDepService.getVechiclesByTransportDepId(idTransportDep);
    }

    @DeleteMapping("/transportDeps/{idTransportDep}/vechicles/delete/{idVechicle}")
    public Collection<Vechicle> delVechicle(@PathVariable Long idVechicle, @PathVariable Long idTransportDep) {
        transportDepService.removeVechicle(idVechicle);
        return transportDepService.getVechiclesByTransportDepId(idTransportDep);
    }
}
