package org.ivc.transportation.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@EqualsAndHashCode(exclude = {"transportDep"})
@ToString(exclude = {"transportDep"})
@Entity
@Table(name = "mechanic")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false, length = 64)
    private String firstname;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "surname", length = 64)
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday; //date of birthday driver

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone", length = 16)
    private String phone;

    @Column(name = "workmanship", length = 64)
    private String workmanship;

    @ManyToOne
    private TransportDep transportDep;
}
