/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author first
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"record", "appointment"})
@EqualsAndHashCode(exclude = {"record", "appointment"})
public class AppointmentGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 1024)
    private String note;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Record record;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Appointment appointment;

    public AppointmentGroup(Record record, Appointment appointment) {
        this.record = record;
        this.appointment = appointment;
    }

}
