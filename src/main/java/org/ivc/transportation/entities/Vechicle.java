/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@ToString(exclude = {"transportDep", "appointments", "criterionValues"})
@EqualsAndHashCode(exclude = {"transportDep", "appointments", "criterionValues"})
public class Vechicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String number;

    @NonNull
    @Column(nullable = false)
    private Float fuelRemnant;

    @NonNull
    @Column(nullable = false)
    private Float odometr;

    @NonNull
    @Column(nullable = false)
    private String vacant;

    @ManyToOne
    private TransportDep transportDep;

    @OneToMany(mappedBy = "vechicle")
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "vechicle")
    private Set<CriterionValue> criterionValues;

}
