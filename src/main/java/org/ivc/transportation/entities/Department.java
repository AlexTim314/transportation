package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@EqualsAndHashCode
@ToString
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shortname", nullable = false, unique = true)
    private String shortname;

    @Column(name = "fullname", unique = true, length = 1024)
    private String fullname;

    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "phone", unique = true)
    private String phone;

    public Department(String shortname) {
        this.shortname = shortname;
    }

    public Department(String shortname, String fullname) {
        this.shortname = shortname;
        this.fullname = fullname;
    }

    public Department(String shortname, String fullname, String address) {
        this.shortname = shortname;
        this.fullname = fullname;
        this.address = address;
    }

    public Department(String shortname, String fullname, String address, String phone) {
        this.shortname = shortname;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
    }

}
