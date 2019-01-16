package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.EntitiesUtils.VehicleStatus;

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
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private Double fuel;

    @Column(nullable = false)
    private Double odometr;

    @Column(nullable = false)
    private int motohours;
    
    @Column(nullable = false)
    private VehicleStatus status;
    
    @Column(nullable = false)
    private Boolean vacant;

    @ManyToOne
    private VehicleModel model;

    @ManyToOne
    private TransportDep transportDep;

    @Column(length = 1024)
    private String note;

    public Vehicle(String number, Double fuel, Double odometr, int motohours, VehicleStatus status, Boolean vacant, VehicleModel model, TransportDep transportDep, String note) {
        this.number = number;
        this.fuel = fuel;
        this.odometr = odometr;
        this.motohours = motohours;
        this.status = status;
        this.vacant = vacant;
        this.model = model;
        this.transportDep = transportDep;
        this.note = note;
    }
    
}
