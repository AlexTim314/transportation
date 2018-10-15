package org.ivc.transportation.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Sokolov Slava & Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Claim implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false,length = 1024)
    private String purpose;

    @NonNull
    @Column(nullable = false)
    private Date outDate;

    @NonNull
    private Date returnDate;

    @NonNull
    @Column(length = 1024)
    private String description;

    @NonNull
    @Column(nullable = false)
    private Date clDate;  // clame date

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "claim")
    private Set<Record> records;

    @OneToMany(mappedBy = "claim")
    private Set<CriterionValue> criterionValues;
    
    @OneToMany(mappedBy = "claim")
    private Set<Waypoint> waypoints;
}
