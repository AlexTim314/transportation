/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"transportDep"})
@EqualsAndHashCode(exclude = {"transportDep"})
public class Vechicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String number;

    @NonNull
    @Column(nullable = false)
    private Double fuelRemnant;

    @NonNull
    @Column(nullable = false)
    private Double odometr;

    @NonNull
    @Column(nullable = false)
    private String vacant;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private TransportDep transportDep;

    public Vechicle(String number, Double fuelRemnant, Double odometr, String vacant, TransportDep transportDep) {
        this.number = number;
        this.fuelRemnant = fuelRemnant;
        this.odometr = odometr;
        this.vacant = vacant;
        this.transportDep = transportDep;

    }
}
