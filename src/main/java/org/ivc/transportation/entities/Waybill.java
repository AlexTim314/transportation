/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"appointment", "taskList"})
@EqualsAndHashCode(exclude = {"appointment", "taskList"})
public class Waybill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String number;

    @NonNull
    @Column(nullable = false)
    private Float departureOdometer;

    @NonNull
    private Float returnOdometer;

    @NonNull
    @Column(nullable = false)
    private Date factDepartureTime;

    @NonNull
    private Date factReturnTime;

    @NonNull
    @Column(nullable = false)
    private Float departureFuelRemnant;

    @NonNull
    private Float returnFuelRemnant;

    @NonNull
    private Date startLunch;

    @NonNull
    private Date EndLunch;

    @NonNull
    @Column(nullable = false)
    private String mechConclusion;

    @NonNull
    @Column(nullable = false)
    private String medConclusion;

    @NonNull
    @Column(length = 1024)
    private String note;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Appointment appointment;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private TaskList taskList;

    public Waybill(String number, Float departureOdometer, Float returnOdometer, Date factDepartureTime,
            Date factReturnTime, Float departureFuelRemnant, Float returnFuelRemnant, Date startLunch,
            Date EndLunch, String mechConclusion, String medConclusion, String note, Appointment appointment,
            TaskList taskList) {
        this.number = number;
        this.EndLunch = EndLunch;
        this.appointment = appointment;
        this.departureFuelRemnant = departureFuelRemnant;
        this.departureOdometer = departureOdometer;
        this.factDepartureTime = factDepartureTime;
        this.mechConclusion = mechConclusion;
        this.medConclusion = medConclusion;
        this.note = note;
        this.returnFuelRemnant = returnFuelRemnant;
        this.returnOdometer = returnOdometer;
        this.startLunch = startLunch;
        this.taskList = taskList;

    }

}
