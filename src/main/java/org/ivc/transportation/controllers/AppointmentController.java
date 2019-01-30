package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/dispatcher/appointments/Tomorrow")
    public List<CompositeClaimRecord> getAppointmentsTomorrow(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59), ZoneId.systemDefault());
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }

    @GetMapping("/dispatcher/appointments/Week")
    public List<CompositeClaimRecord> getAppointmentsWeek(Principal principal) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59), ZoneId.systemDefault());
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }

    @PostMapping("/dispatcher/appointments/Date")
    public List<CompositeClaimRecord> getAppointmentsDate(Principal principal, @RequestBody ZonedDateTime date) {
        ZonedDateTime dStart = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(0, 0), ZoneId.systemDefault());
        ZonedDateTime dEnd = ZonedDateTime.of(LocalDate.from(date), LocalTime.of(23, 59), ZoneId.systemDefault());
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }
    
    @PutMapping("/dispatcher/appointments_update")
    public List<Appointment> createAppointment(Principal principal, @RequestBody List<Appointment> appointments) {
        return dispatcherService.updateAppointments(principal, appointments);
    }
    
}
