package org.ivc.transportation.controllers;

import java.security.Principal;
import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.services.CarBossService;
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.utils.CompositeClaimRecord;
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
 * @author Sokolov Slava
 */
@RestController
public class AppointmentController {

    @Autowired
    private DispatcherService dispatcherService;

    @GetMapping("/dispatcher/appointments")
    public List<CompositeClaimRecord> getAppointments(Principal principal) {
        return dispatcherService.getAppointments(principal);
    }

}
