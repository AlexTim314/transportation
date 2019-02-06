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
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;

/**
 *
 * @author Nesterov Yuriy
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"driver", "vehicle", "mechanic", "transportDep", "vehicleModel", "creator"})
@ToString(exclude = {"driver", "vehicle", "mechanic", "transportDep", "vehicleModel", "creator"})
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Column(name = "note", length = 255)
    private String note;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Mechanic mechanic;

    @ManyToOne
    private TransportDep transportDep;

    @ManyToOne
    private VehicleModel vehicleModel;

    @ManyToOne
    private AppUser creator;
    
    @ManyToOne
    private AppUser modificator;

    public Appointment(LocalDateTime creationDate, AppointmentStatus status, String note, AppUser creator) {
        this.creationDate = creationDate;
        this.status = status;
        this.note = note;
        this.creator = creator;
    }

}
