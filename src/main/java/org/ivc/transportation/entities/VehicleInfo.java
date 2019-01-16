package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.EntitiesUtils;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {})
@ToString(exclude = {})
@Entity
public class VehicleInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double fuel;

    @Column(nullable = false)
    private Double odometr;

    @Column(nullable = false)
    private int motohours;

    @Column(nullable = false)
    private EntitiesUtils.VehicleStatus status;

    @Column(length = 1024)
    private String note;

    public VehicleInfo(Double fuel, Double odometr, int motohours, EntitiesUtils.VehicleStatus status, String note) {
        this.fuel = fuel;
        this.odometr = odometr;
        this.motohours = motohours;
        this.status = status;
        this.note = note;
    }

}
