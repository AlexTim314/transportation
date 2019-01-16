package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;

/**
 *
 * @author Nesterov Yuriy
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {})
@ToString(exclude = {})
@Entity
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    @NonNull
    @Column(nullable = false)
    private LocalDateTime appDateTime;

    @NonNull
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column(nullable = true)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    /*
    @OneToOne(fetch = FetchType.EAGER)
    private Waybill waybill;
    */
    
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private TransportDep transportDep;
    
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleModel vehicleModel;
}
