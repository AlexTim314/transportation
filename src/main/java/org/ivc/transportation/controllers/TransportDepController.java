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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transportdeps")
public class TransportDepController {

    @Autowired
    private TransportDepService localServ;

    @Autowired
    private DriverService drvServ;

    @Autowired
    private VechicleService vechServ;

    @GetMapping()
    public Collection<TransportDep> getTransportDeps() {
        return localServ.getTransportDeps();
    }

    @GetMapping("/{id}")
    public TransportDep getTransportDep(@PathVariable Long id) {
        Optional<TransportDep> dep = localServ.getTransportDepById(id);
        return dep.get();
    }

    @DeleteMapping("/{id}")
    public void delTransportDep(@PathVariable Long id) {
        localServ.removeTransportDep(id);
    }

    @PostMapping()
    public void addTransportDep(@RequestBody TransportDep department) {
        localServ.addTransportDep(department);
    }

    @PutMapping("/{id}")
    public void updateTransportDep(@RequestBody TransportDep dep, @PathVariable Long id) {
        localServ.updateTransportDep(dep, id);
    }

    @GetMapping("/{id}/drivers")
    public Collection<Driver> getDrivers(@PathVariable Long id) {
        return drvServ.getDriversByTransportDepId(id);
    }

    @GetMapping("/{id}/vechicles")
    public Collection<Vechicle> getVechicles(@PathVariable Long id) {
        return vechServ.getVechiclesByTransportDepId(id);
    }

}
