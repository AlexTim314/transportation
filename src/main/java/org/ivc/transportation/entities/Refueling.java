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

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"fuel", "vehicle"})
@ToString(exclude = {"fuel", "vehicle"})
@Entity
@Table(name = "refueling")
public class Refueling implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "refueling_date", nullable = false)
    private LocalDateTime refuelingDate;

    @Column(name = "volume")
    private double volume;

    @ManyToOne
    private Fuel fuel;

    @ManyToOne
    private Vehicle vehicle;
}
