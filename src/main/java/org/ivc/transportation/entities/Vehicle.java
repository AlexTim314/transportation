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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 *
 * @author alextim
 */
@Entity
@Data //геттеры, сеттеры и, похоже, конструктор с параметрами (проверить)
@NoArgsConstructor
@ToString(exclude = {"transportDep","vehicleType"})
@EqualsAndHashCode(exclude = {"transportDep","vehicleType"})
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String number;

    @NonNull
    @Column(nullable = false)
    private Double fuel;

    @NonNull
    @Column(nullable = false)
    private Double odometr;

    @NonNull
    @Column(nullable = false)
    private int motohours;

    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleModel vehicleModel;

    @ManyToOne(fetch = FetchType.EAGER)
    private TransportDep transportDep;   

}
