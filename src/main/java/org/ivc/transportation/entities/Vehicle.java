package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author alextim
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"model", "transportDep"})
@ToString(exclude = {"model", "transportDep"})
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "number", nullable = false, unique = true, length = 16)
    private String number;

    @Column(name = "fuel", nullable = false)
    private double fuel;

    @Column(name = "odometr", nullable = false)
    private double odometr;

    @Column(name = "motohours", nullable = false)
    private int motohours;

    @Column(name = "note")
    private String note;

    @ManyToOne
    private VehicleModel model;

    @ManyToOne
    private TransportDep transportDep;

    public Vehicle(String number, Double fuel, Double odometr, int motohours, VehicleModel model, TransportDep transportDep) {
        this.number = number;
        this.fuel = fuel;
        this.odometr = odometr;
        this.motohours = motohours;
        this.model = model;
        this.transportDep = transportDep;
    }

}
