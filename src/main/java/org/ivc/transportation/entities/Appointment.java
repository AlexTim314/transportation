/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.config.trUtils.AppointmentStatus;

/**
 *
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appointmentGroup", "vehicle", "driver", "transportDep", "vehicleModel"})
@ToString(exclude = {"appointmentGroup", "vehicle", "driver", "transportDep", "vehicleModel"})
public class Appointment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime appDateTime;

    @NonNull
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column(nullable = true)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    @OneToOne(fetch = FetchType.EAGER)
    private Waybill waybill;
    
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private TransportDep transportDep;
    
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleModel vehicleModel;


    public Appointment(LocalDateTime appDateTime, TransportDep transportDep, VehicleModel vehicleModel) {
        this.appDateTime = appDateTime;
        this.status = AppointmentStatus.appointment_status_created;
        this.transportDep = transportDep;
        this.vehicleModel = vehicleModel;
    }
    
    

}
