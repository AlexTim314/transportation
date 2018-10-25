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
import org.ivc.transportation.services.TransportDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transportdeps")
public class TransportDepController {

    @Autowired
    private TransportDepService tdServ;

    @GetMapping()
    public Collection<TransportDep> getTransportDeps() {
        return tdServ.getTransportDeps();
    }

    @GetMapping("/{id}")
    public TransportDep getTransportDep(@PathVariable Long id) {
        Optional<TransportDep> dep = tdServ.getTransportDepById(id);
        return dep.get();
    }

    @DeleteMapping("/{id}")
    public Collection<TransportDep> delTransportDep(@PathVariable Long id) {
        tdServ.removeTransportDep(id);
        return tdServ.getTransportDeps();
    }

    @PostMapping()
    public Collection<TransportDep> addTransportDep(@RequestBody TransportDep department) {
        tdServ.addTransportDep(department);
        return tdServ.getTransportDeps();
    }

    @PutMapping("/{id}")
    public Collection<TransportDep> updateTransportDep(@RequestBody TransportDep dep, @PathVariable Long id) {
        tdServ.updateTransportDep(dep, id);
        return tdServ.getTransportDeps();
    }

    @GetMapping("/{id}/drivers")
    public Collection<Driver> getDrivers(@PathVariable Long id) {
        return tdServ.getDriversByTransportDepId(id);
    }

    @PostMapping("/{id}/drivers")
    public Collection<Driver> addDriver(@PathVariable Long id, @RequestBody Driver d) throws Throwable {
        Optional<TransportDep> otd = tdServ.getTransportDepById(id);
        d.setTransportDep(otd.orElseThrow(() -> {
            return new IllegalArgumentException("В базе нет транспортного отдела с id=" + id); 
        }));
        tdServ.addDriver(d);
        return tdServ.getDriversByTransportDepId(id);
    }

    }
