package org.ivc.transportation.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@EqualsAndHashCode(exclude = {"model", "transportDep", "fuels", "lastDep"})
@ToString(exclude = {"model", "transportDep", "fuels", "lastDep"})
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "status", nullable = false)
    private VehicleStatus status;

    @Column(name = "note", length = 255)
    private String note;

    @OneToOne
    private Department lastDep;
    
    @ManyToOne
    private VehicleModel model;

    @ManyToOne
    private TransportDep transportDep;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle_fuel", joinColumns = @JoinColumn(name = "vehicle_id"), inverseJoinColumns = @JoinColumn(name = "fuel_id"))
    private List<Fuel> fuels;

    public Vehicle(String number, Double fuel, Double odometr, int motohours, VehicleStatus status, Boolean vacant, VehicleModel model, TransportDep transportDep, String note) {
        this.number = number;
        this.fuel = fuel;
        this.odometr = odometr;
        this.motohours = motohours;
        this.status = status;
        this.model = model;
        this.transportDep = transportDep;
        this.note = note;
    }

}
