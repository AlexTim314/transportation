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
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

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
    
        @NonNull
    @Column(nullable = false)
    private Double fuel;

    @NonNull
    @Column(nullable = false)
    private Double odometr;

    @NonNull
    @Column(nullable = false)
    private int motohours;

    public VehicleInfo(Double fuel, Double odometr, int motohours) {
        this.fuel = fuel;
        this.odometr = odometr;
        this.motohours = motohours;
    }

}
