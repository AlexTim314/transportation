/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.List;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.services.TransportDepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */

@RestController
public class TransportDepManagementController {
    
    @Autowired
    private TransportDepService transportDepService;
    
    @GetMapping("/management/transportDeps")
    public List<TransportDep> getAllTransportDeps() {
        return transportDepService.findAllTransportDeps();
    }

    @PostMapping("/management/transportDep_create")
    public TransportDep createTransportDep(@RequestBody TransportDep transportDep) {
        return transportDepService.saveTransportDep(transportDep);
    }

    @PutMapping("/management/transportDep_update")
    public TransportDep updateTransportDep(@RequestBody TransportDep transportDep) {
        return transportDepService.saveTransportDep(transportDep);
    }

    @DeleteMapping("/management/transportDep_delete")
    public ResponseEntity<String> deleteTransportDep(@RequestBody TransportDep transportDep) {
        transportDepService.deleteTransportDep(transportDep);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
