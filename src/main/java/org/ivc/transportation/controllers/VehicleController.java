package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.ivc.transportation.entities.Refueling;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.VehicleInfo;
import org.ivc.transportation.entities.Waybill;
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.services.VehicleService;
import org.ivc.transportation.utils.AddWaybill;
import org.ivc.transportation.utils.AppointmentClaim;
import org.ivc.transportation.utils.CompositeAppointmentClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sokolov Slava
 */
@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DispatcherService dispatcherService;

    @GetMapping("/dispatcher/vehicles")
    public List<Vehicle> getVehicles(Principal principal) {
        return vehicleService.findVehiclesByTransportDep(principal);
    }

    @GetMapping("/dispatcher/vehicle_history/{id}")
    public List<VehicleInfo> getVehicleHistory(@PathVariable("id") Long vehicleId) {
        return vehicleService.findVehicleHistoryById(vehicleId);
    }

    @PostMapping("/dispatcher/vehicle_create")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PutMapping("/dispatcher/vehicle_update")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PostMapping("/dispatcher/vehicle_updateState")
    public VehicleInfo updateVehicleStatus(Principal principal, @RequestBody VehicleInfo vehicleInfo) {
        return vehicleService.updateVehicleStatus(principal, vehicleInfo);
    }

    @PostMapping("/dispatcher/vehicle_refueling")
    public Refueling vehicleRefueling(@RequestBody Refueling refueling) {
        return vehicleService.vehicleRefueling(refueling);
    }

    @DeleteMapping("/dispatcher/vehicles_delete")
    public ResponseEntity<String> deleteVehicle(@RequestBody List<Long> ids) {
        vehicleService.deleteVehicles(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/dispatcher/vehicle_appointments/{id}")
    public List<CompositeAppointmentClaim> getAppointmentsByVehicle(@PathVariable("id") Long vehicleId) {
        //LocalDateTime dStart = LocalDateTime.now();
        LocalDateTime dStart = LocalDateTime.of(LocalDate.now().minusDays(30), LocalTime.of(00, 00));
        LocalDateTime dEnd = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
        return dispatcherService.getAppointmentsByVehicle(vehicleId, dStart, dEnd);
    }

    @GetMapping("/dispatcher/waybill_create/{id_v}/{id_a}")
    public Waybill newWaybill(@PathVariable("id_v") Long vehId, @PathVariable("id_a") Long appId) {
        LocalDateTime openDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        String number = LocalDate.now() + "";
        AddWaybill waybill = new AddWaybill(openDate, null, number, vehId, appId);
        return dispatcherService.createWaybill(waybill);
    }

}
