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
import org.ivc.transportation.services.DriverService;
import org.ivc.transportation.services.TransportDepService;
import org.ivc.transportation.services.VechicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransportDepController {

    private static final String url = "/transportdeps";

    @Autowired
    private TransportDepService transportDepService;

    @Autowired
    private DriverService drvServ;

    @Autowired
    private VechicleService vechServ;

    @GetMapping("/transportDeps")
    public Collection<TransportDep> getAllTransportDeps() {
        return transportDepService.getTransportDeps();
    }

    @GetMapping(url)
    public Collection<TransportDep> getTransportDeps() {
        return transportDepService.getTransportDeps();
    }

    @GetMapping(url + "/{id}")
    public TransportDep getTransportDep(@PathVariable Long id) {
        Optional<TransportDep> dep = transportDepService.getTransportDepById(id);
        return dep.get();
    }

    @DeleteMapping(url + "/{id}")
    public void delTransportDep(@PathVariable Long id) {
        transportDepService.removeTransportDep(id);
    }

    @PostMapping(url)
    public void addTransportDep(@RequestBody TransportDep department) {
        transportDepService.addTransportDep(department);
    }

    @PutMapping(url + "/{id}")
    public void updateTransportDep(@RequestBody TransportDep dep, @PathVariable Long id) {
        transportDepService.updateTransportDep(dep, id);
    }

    @GetMapping(url + "/{id}/drivers")
    public Collection<Driver> getDrivers(@PathVariable Long id) {
        return drvServ.getDriversByTransportDepId(id);
    }

    @GetMapping(url + "/{id}/vechicles")
    public Collection<Vechicle> getVechicles(@PathVariable Long id) {
        return vechServ.getVechiclesByTransportDepId(id);
    }

}
