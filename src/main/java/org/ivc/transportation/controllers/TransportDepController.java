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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transportdeps")
public class TransportDepController {

    private static final String URL_T = "/transportdeps";
    private static final String URL_D = "/drivers";
    private static final String URL_V = "/vechicles";

    @Autowired
    private TransportDepService tdServ;

    @GetMapping(URL_T)
    public Collection<TransportDep> getTransportDeps() {
        return tdServ.getTransportDeps();
    }

    @GetMapping(URL_T + "/{id}")
    public TransportDep getTransportDep(@PathVariable Long id) {
        Optional<TransportDep> dep = tdServ.getTransportDepById(id);
        return dep.get();
    }

    @DeleteMapping(URL_T + "/{id}")
    public void delTransportDep(@PathVariable Long id) {
        tdServ.removeTransportDep(id);
    }

    @PostMapping(URL_T)
    public void addTransportDep(@RequestBody TransportDep department) {
        tdServ.addTransportDep(department);
    }

    @PutMapping(URL_T + "/{id}")
    public void updateTransportDep(@RequestBody TransportDep dep, @PathVariable Long id) {
        tdServ.updateTransportDep(dep, id);
    }

    @GetMapping(URL_T + "/{id}/drivers")
    public Collection<Driver> getDrivers(@PathVariable Long id) {
        return tdServ.getDriversByTransportDepId(id);
    }

    @GetMapping(URL_T + "/{id}/vechicles")
    public Collection<Vechicle> getVechicles(@PathVariable Long id) {
        return tdServ.getVechiclesByTransportDepId(id);
    }

    @GetMapping(URL_D)
    public Collection<Driver> getDrivers() {
        return tdServ.getDrivers();
    }

    @GetMapping(URL_D + "/{id}")
    public Driver getDriver(@PathVariable Long id) {
        Optional<Driver> dep = tdServ.getDriverById(id);
        return dep.get();
    }

    @DeleteMapping(URL_D + "/{id}")
    public void delDriver(@PathVariable Long id) {
        tdServ.removeDriver(id);
    }

    @PostMapping(URL_D)
    public void addDriver(@RequestBody Driver department) {
        tdServ.addDriver(department);
    }

    @PutMapping(URL_D + "/{id}")
    public void updateDriver(@RequestBody Driver dep, @PathVariable Long id) {
        tdServ.updateDriver(dep, id);
    }

    @GetMapping(URL_V)
    public Collection<Vechicle> getVechicles() {
        return tdServ.getVechicles();
    }

    @GetMapping(URL_V + "/{id}")
    public Vechicle getVechicle(@PathVariable Long id) {
        Optional<Vechicle> dep = tdServ.getVechicleById(id);
        return dep.get();
    }

    @DeleteMapping(URL_V + "/{id}")
    public void delVechicle(@PathVariable Long id) {
        tdServ.removeVechicle(id);
    }

    @PostMapping(URL_V)
    public void addVechicle(@RequestBody Vechicle department) {
        tdServ.addVechicle(department);
    }

    @PutMapping(URL_V + "/{id}")
    public void updateVechicle(@RequestBody Vechicle dep, @PathVariable Long id) {
        tdServ.updateVechicle(dep, id);
    }
}
