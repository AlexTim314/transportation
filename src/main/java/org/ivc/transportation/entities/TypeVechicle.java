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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 *
 * @author first
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
//@EqualsAndHashCode
public class TypeVechicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NonNull
    @Column(length = 1024)
    private String type;
    
    @NonNull
    @Column(length = 1024)
    private String specialization;
    
     public TypeVechicle (String type, String specialization) {
        this.type = type;
        this.specialization = specialization;
    }
    
}
