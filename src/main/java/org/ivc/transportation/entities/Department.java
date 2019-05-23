package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Sokolov Slava & Nesterov Yuriy
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "order", nullable = false, unique = true)
    private int order;

    @Column(name = "shortname", nullable = false, unique = true, length = 64)
    private String shortname;

    @Column(name = "fullname", unique = true, length = 255)
    private String fullname;

    @Column(name = "address", unique = true, length = 255)
    private String address;

    @Column(name = "phone", unique = true, length = 16)
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "super_manager_id")
    private AppUser superManager;

    public Department(int order, String shortname) {
        this.order = order;
        this.shortname = shortname;
    }

    public Department(int order, String shortname, String fullname) {
        this.order = order;
        this.shortname = shortname;
        this.fullname = fullname;
    }

    public Department(int order, String shortname, String fullname, String address) {
        this.order = order;
        this.shortname = shortname;
        this.fullname = fullname;
        this.address = address;
    }

    public Department(int order, String shortname, String fullname, String address, String phone) {
        this.order = order;
        this.shortname = shortname;
        this.fullname = fullname;
        this.address = address;
        this.phone = phone;
    }
    
}
