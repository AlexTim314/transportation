package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleModel;
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.utils.CompositeClaimRecord;
import org.ivc.transportation.utils.CompositeRecordIdAppointment;
import org.ivc.transportation.utils.VehicleForPlan;
import org.ivc.transportation.utils.VehicleLastDep;
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

    @GetMapping("/dispatcher/appointments/Tomorrow")
    public List<CompositeClaimRecord> getAppointmentsTomorrow(Principal principal) {
        LocalDateTime dStart = LocalDateTime.now();
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(23, 59));
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }

    @GetMapping("/dispatcher/appointments/Week")
    public List<CompositeClaimRecord> getAppointmentsWeek(Principal principal) {
        LocalDateTime dStart = LocalDateTime.now();
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }

    @GetMapping("/dispatcher/appointments/Month")
    public List<CompositeClaimRecord> getAppointmentsMonth(Principal principal) {
        LocalDateTime dStart = LocalDateTime.now();
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusMonths(1), LocalTime.of(23, 59));
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }

    @GetMapping("/dispatcher/appointments/monthBefore")
    public List<CompositeClaimRecord> getAppointmentsMonthBefore(Principal principal) {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.of(00, 00));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }

    @PostMapping("/dispatcher/appointments/Date")
    public List<CompositeClaimRecord> getAppointmentsDate(Principal principal, @RequestBody LocalDateTime date) {
        LocalDateTime dStart = LocalDateTime.of(LocalDate.from(date), LocalTime.of(0, 0));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.from(date), LocalTime.of(23, 59));
        return dispatcherService.getAppointmentsTimeFilter(principal, dStart, dEnd);
    }

    @PutMapping("/dispatcher/appointments_update")
    public List<Appointment> updateAppointments(Principal principal, @RequestBody List<Appointment> appointments) {
        return dispatcherService.updateAppointments(principal, appointments);
    }

    @PutMapping("/dispatcher/appointment_update")
    public Appointment updateAppointment(Principal principal, @RequestBody Appointment appointment) {
        return dispatcherService.updateAppointment(principal, appointment);
    }

    @PutMapping("/dispatcher/recordCancel")
    public Record recordCancel(Principal principal, @RequestBody CompositeRecordIdAppointment compositeRecordIdAppointment) {
        return dispatcherService.recordCancel(principal, compositeRecordIdAppointment);
    }

    @PostMapping("/dispatcher/vacantDrivers")
    public List<Driver> getVacantDrivers(Principal principal, @RequestBody Appointment appointment) {
        return dispatcherService.getVacantDrivers(principal, appointment);
    }

    @PostMapping("/dispatcher/vacantVehicles")
    public List<Vehicle> getVacantVehicles(Principal principal, @RequestBody Appointment appointment) {
        return dispatcherService.getVacantVehicles(principal, appointment);
    }

    @GetMapping("/dispatcher/permit")
    public Boolean getPermit(Principal principal) {
        return dispatcherService.getPermit(principal);
    }

    @GetMapping("/dispatcher/username")
    public String getUserName(Principal principal) {
        return dispatcherService.getUserName(principal);
    }

    @GetMapping("/dispatcher/vehicleModels")
    public List<VehicleModel> getVehicleModels(Principal principal) {
        return dispatcherService.findVehicleModelsByTransportDep(principal);
    }

    @GetMapping("/dispatcher/carBosses")
    public List<CarBoss> getCarBosses(Principal principal) {
        return dispatcherService.findCarBossesByDepartment(principal);
    }

    @PostMapping("/dispatcher/carBoss_create")
    public CarBoss createCarBoss(Principal principal, @RequestBody CarBoss carBoss) {
        return dispatcherService.saveCarBoss(principal, carBoss);
    }
    
    @PutMapping("/dispatcher/carBoss_update")
    public CarBoss updateCarBoss(Principal principal, @RequestBody CarBoss carBoss) {
        return dispatcherService.saveCarBoss(principal, carBoss);
    }

    @DeleteMapping("/dispatcher/carBoss_delete")
    public ResponseEntity<String> deleteCarBoss(@RequestBody CarBoss carBoss) {
        dispatcherService.deleteCarBoss(carBoss);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
