package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import org.ivc.transportation.utils.EntitiesUtils.VehicleStatus;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"vehicle"})
@ToString(exclude = {"vehicle"})
@Entity
@Table(name = "vehicle_info")
public class VehicleInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;

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

    @ManyToOne
    private Vehicle vehicle;
    
    @ManyToOne
    private AppUser modificator;

    public VehicleInfo(LocalDateTime modificationDate, double fuel, double odometr, int motohours, VehicleStatus status, String note, Vehicle vehicle, AppUser modificator) {
        this.modificationDate = modificationDate;
        this.fuel = fuel;
        this.odometr = odometr;
        this.motohours = motohours;
        this.status = status;
        this.note = note;
        this.vehicle = vehicle;
        this.modificator = modificator;
    }

}
