/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@ToString
@EqualsAndHashCode
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
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "waybill")
    private Set<Fueling> fuelings;
}
