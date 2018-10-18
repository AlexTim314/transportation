/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.Collection;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nesterov Yuriy
 */
@RestController
public class TransportDepController {

    @Autowired
    private TransportDepRepository repository;

    @GetMapping("/transport-dep")
    public Collection<TransportDep> getTransportDeps() {
        return repository.findAll();

    }
}
