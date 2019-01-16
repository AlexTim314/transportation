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
import lombok.NonNull;
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

    @ManyToOne
    private VehicleModel model;

    @ManyToOne
    private TransportDep transportDep;

}
