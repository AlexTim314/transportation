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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author first
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"vehicle", "appointment"})
@ToString(exclude = {"vehicle", "appointment"})
@Entity
@Table(name = "waybill")
public class Waybill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "opened_date", nullable = false)
    private LocalDateTime openedDate;

    @Column(name = "closed_date")
    private LocalDateTime closedDate;

    @Column(name = "number", nullable = false, unique = true, length = 16)
    private String number;

    @ManyToOne
    private Vehicle vehicle;

    @OneToMany
    private List<Appointment> appointments;

    public Waybill(LocalDateTime openedDate, LocalDateTime closedDate, String number, Vehicle vehicle, List<Appointment> appointments) {
        this.closedDate = closedDate;
        this.openedDate = openedDate;
        this.number = number;
        this.vehicle = vehicle;
        this.appointments = appointments;
    }
}
