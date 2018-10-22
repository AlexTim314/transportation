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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransportDepController {

    private static final String url = "/transportdeps";

    @Autowired
    private TransportDepService localServ;

    @GetMapping(url)
    public Collection<TransportDep> getTransportDeps() {
        return localServ.listTransportDeps();
    }

    @GetMapping(url + "/{id}")
    public TransportDep getTransportDep(@PathVariable long id) {
        Optional<TransportDep> dep = localServ.getTransportDepById(id);
        return dep.get();
    }

    @DeleteMapping(url + "/{id}")
    public void delTransportDep(@PathVariable long id) {
        localServ.removeTransportDep(id);
    }

    @PostMapping(url)
    public void addTransportDep(@RequestBody TransportDep department) {
        localServ.addTransportDep(department);
    }

    @PutMapping(url + "/{id}")
    public void updateTransportDep(@RequestBody TransportDep dep, @PathVariable long id) {
        localServ.updateTransportDep(dep, id);
    }

    @GetMapping(url + "/{id}/drivers")
    public Collection<Driver> getDrivers(@PathVariable long id) {
        return localServ.listDrivers(id);
    }

}
