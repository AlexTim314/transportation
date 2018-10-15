/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    @Column(nullable = false)
    private String name;
    
    @NonNull
    @Column(nullable = false)
    private String surname;
    
    @NonNull
    private String lastname;
    
    @NonNull
    @Column(length = 1024)
    private String addres;
    
    @NonNull
    private String telephone;
    
    @ManyToOne
    private Department department;
    
}
