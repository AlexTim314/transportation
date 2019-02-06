package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appointment"})
@ToString(exclude = {"appointment"})
@Entity
@Table(name = "appointment_info")
public class AppointmentInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "modification_date", nullable = false)
    private LocalDateTime modificationDate;

    @Column(name = "status")
    private AppointmentStatus status;

    @Column(name = "note", length = 255)
    private String note;

    @ManyToOne
    private Appointment appointment;
    
    @ManyToOne
    private AppUser modificator;
    
    public AppointmentInfo(LocalDateTime modificationDate, AppointmentStatus status, String note, Appointment appointment, AppUser modificator) {
        this.modificationDate = modificationDate;
        this.status = status;
        this.note = note;
        this.appointment = appointment;
        this.modificator = modificator;
    }
}
