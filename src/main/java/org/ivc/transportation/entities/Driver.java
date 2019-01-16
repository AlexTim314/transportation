package org.ivc.transportation.entities;

import java.io.Serializable;
import java.sql.Date;
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
@EqualsAndHashCode(exclude = {})
@ToString(exclude = {})
@Entity
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String firstname;
    
    @Column(nullable = false)
    private String name;
    
    private String surname;
    
    private Date birthday; //date of birthday driver

    @Column(length = 512)
    private String address;

    private String phone;
    
    private String driverClass;
    
    private String driverLicense;

    @Column(nullable = false)
    private Boolean vacant;
    
    @Column(nullable = false)
    private DriverStatus status;
    
    @Column(length = 1024)
    private String note;    

    @ManyToOne(fetch = FetchType.EAGER)
    private TransportDep transportDep;

    public Driver(String firstname, String name, String surname, Date birthday, String address, String phone, String note,TransportDep transportDep) {
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
