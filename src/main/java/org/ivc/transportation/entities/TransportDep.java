/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransportDep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private Integer numDep;  //number of department
    
    @NonNull
    private String addres;
    
    @NonNull
    private String phone;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transportDep")
    private Set<Driver> drivers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transportDep")
    private Set<Vechicle> vechicles;
    
}
