package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.EntitiesUtils.VehicleSpecialization;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"vehicleType", "creator", "affirmator", "records", "route"})
@ToString(exclude = {"vehicleType", "creator", "affirmator", "records", "route"})
@Entity
@Table(name = "claim")
public class Claim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "template_name", length = 64)
    private String templateName;   //название шаблона, если null, то заявка - от подразделения, в противном случае это шаблон 

    @Column(name = "specialization")
    private VehicleSpecialization specialization;

    @Column(name = "car_boss", nullable = false, length = 64)
    private String carBoss;

    @Column(name = "purpose", length = 255)
    private String purpose;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "affirmation_date")
    private LocalDateTime affirmationDate;

    @ManyToOne
    private Department department;

    @ManyToOne
    private VehicleType vehicleType;

    @ManyToOne
    private AppUser creator;

    @ManyToOne
    private AppUser affirmator;

    @OneToMany
    private List<Record> records;

    @OneToMany
    private List<RouteTask> route;

    public Claim(VehicleSpecialization specialization, String carBoss, String purpose, LocalDateTime creationDate, Department department, VehicleType vehicleType, AppUser creator) {
        this.specialization = specialization;
        this.carBoss = carBoss;
        this.purpose = purpose;
        this.creationDate = creationDate;
        this.department = department;
        this.vehicleType = vehicleType;
        this.creator = creator;
    }
    
    

}
