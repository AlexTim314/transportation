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
import lombok.NonNull;
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
@EqualsAndHashCode(exclude = {"driver", "vehicle", "transportDep", "vehicleModel"})
@ToString(exclude = {"driver", "vehicle", "transportDep", "vehicleModel"})
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;   

    @Column(name = "date_time", nullable = false)
    private LocalDateTime appDateTime;

    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Column(name = "note", length = 255)    
    private String note;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Vehicle vehicle;

    @NonNull
    @ManyToOne
    private TransportDep transportDep;
    
    @NonNull
    @ManyToOne
    private VehicleModel vehicleModel;

    /*
    @OneToOne(fetch = FetchType.EAGER)
    private Waybill waybill;
    */    
}
