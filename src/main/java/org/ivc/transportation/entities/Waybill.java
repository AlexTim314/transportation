package org.ivc.transportation.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@EqualsAndHashCode(exclude = {"vehicle", "appointments", "routeTasks", "refuelings"})
@ToString(exclude = {"vehicle", "appointments", "routeTasks", "refuelings"})
@Entity
@Table(name = "waybill")
public class Waybill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "number", nullable = false, length = 64)
    private String number;
    
    @ManyToOne
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "waybill_id")
    private List<Appointment> appointments;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "waybill_id")
    private List<RouteTask> routeTasks;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "waybill_id")
    private List<Refueling> refuelings; 
}
