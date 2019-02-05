package org.ivc.transportation.entities;

import java.io.Serializable;
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
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.EntitiesUtils.DriverStatus;

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
@Table(name = "driver")
public class Driver implements Serializable {

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

    @Column(name = "driver_class", length = 64)
    private String driverClass;

    @Column(name = "driver_license", length = 64)
    private String driverLicense;

    @Column(name = "status", nullable = false)
    private DriverStatus status;

    @Column(name = "note", length = 255)
    private String note;

    //@Transient
    @Column(name = "vacant", nullable = false)
    private boolean vacant;

    @ManyToOne
    private TransportDep transportDep;

    public Driver(String firstname, String name, String surname, Date birthday, String address, String phone, String note, TransportDep transportDep) {
        this.firstname = firstname;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.vacant = Boolean.TRUE;
        this.name = name;
        this.note = note;
        this.transportDep = transportDep;
    }

}
