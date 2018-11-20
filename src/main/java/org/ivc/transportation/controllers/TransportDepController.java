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
    public Collection<Driver> addDriver(@RequestBody Driver d) throws Throwable {
        System.out.println("#####"+d);
       // System.out.println("#####"+transportDepService.getTransportDepById(d.getTransportDep().getId()).get());
        Optional<TransportDep> otd = transportDepService.getTransportDepById(d.getId());
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + d.getTransportDep().getId());
        }));
        d.setVacant(Boolean.TRUE);
        transportDepService.addDriver(d);
        return transportDepService.getDriversByTransportDepId(d.getTransportDep().getId());
    }


//    @PostMapping("/transportDeps/drivers/create")
//      public Collection<Driver> createUser(@RequestBody Driver d) {
//        transportDepService.addDriver(d);
//       return transportDepService.getDrivers();
//    }
    
    @PutMapping("/transportDeps/drivers/update")
    public Collection<Driver> updateDriver(@RequestBody Driver d, @PathVariable Long idDriver) throws Throwable {
      final Long idTransportDep = d.getTransportDep().getId();
       idDriver = d.getId();
        System.out.println(idTransportDep+ " " + idDriver + " " + d.getFirstname());
        Optional<TransportDep> otd = transportDepService.getTransportDepById(idTransportDep);
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + idTransportDep);
        }));
        transportDepService.updateDriver(d, idDriver);
        return transportDepService.getDriversByTransportDepId(idTransportDep);
    }

    @DeleteMapping("/transportDeps/drivers/delete")
    public Collection<Driver> delDriver(@RequestBody Driver d, @PathVariable Long idDrivers, @PathVariable Long idTransportDep) {
        idDrivers = d.getId();
        idTransportDep = d.getTransportDep().getId();
        transportDepService.removeDriver(idDrivers);
        return transportDepService.getDriversByTransportDepId(idTransportDep);
    }

    @PostMapping("/transportDeps/vechicles")
    public Collection<Vechicle> getVechicles(@RequestBody TransportDep department) {
        return transportDepService.getVechiclesByTransportDepId(department.getId());
    }

    
    @PostMapping("/transportDeps/vechicles/create")
    public Collection<Vechicle> addVechicle(@RequestBody Vechicle d) throws Throwable {   
        Optional<TransportDep> otd = transportDepService.getTransportDepById(d.getTransportDep().getId());
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + d.getTransportDep().getId());
        }));
        d.setVacant(Boolean.TRUE);
        transportDepService.addVechicle(d);
        return transportDepService.getVechiclesByTransportDepId(d.getTransportDep().getId());
    }

    @PutMapping("/transportDeps/vechicles/update")
    public Collection<Vechicle> updateVechicle(@PathVariable Long idVechicle, @RequestBody Vechicle d) throws Throwable {
       final Long idTransportDep =d.getTransportDep().getId();
       idVechicle = d.getId();
        Optional<TransportDep> otd = transportDepService.getTransportDepById(idTransportDep);
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + idTransportDep);
        }));
        transportDepService.updateVechicle(d, idVechicle);
        return transportDepService.getVechiclesByTransportDepId(idTransportDep);
    }

    @DeleteMapping("/transportDeps/vechicles/delete")
    public Collection<Vechicle> delVechicle(@PathVariable Long idVechicle, @PathVariable Long idTransportDep, @RequestBody Vechicle d) {
        idVechicle = d.getId();
        idTransportDep = d.getTransportDep().getId();
        transportDepService.removeVechicle(idVechicle);
        return transportDepService.getVechiclesByTransportDepId(idTransportDep);
    }
}
