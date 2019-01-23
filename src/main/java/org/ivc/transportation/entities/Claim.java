package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
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
@EqualsAndHashCode(exclude = {"vehicleType", "carBoss", "creator", "affirmator", "records", "route"})
@ToString(exclude = {"vehicleType", "carBoss", "creator", "affirmator", "records", "route"})
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

    @Column(name = "purpose", length = 255)
    private String purpose;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "affirmation_date")
    private LocalDateTime affirmationDate;

    @Column(name = "actual", nullable = false)
    private boolean actual;

    @ManyToOne
    private Department department;

    @ManyToOne
    private VehicleType vehicleType;

    @ManyToOne
    private CarBoss carBoss;

    @ManyToOne
    private AppUser creator;

    @ManyToOne
    private AppUser affirmator;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Record> records;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<RouteTask> routeTasks;

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
