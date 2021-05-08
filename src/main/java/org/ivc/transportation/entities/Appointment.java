/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@EqualsAndHashCode(exclude = {"record", "vechicle", "driver"})
@ToString(exclude = {"record", "vechicle", "driver"})
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private Date dateTimeOrder;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Record record;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Vechicle vechicle;
    

    public Appointment (Date dateTimeOrder,Record record,Driver driver, Vechicle vechicle){
        this.dateTimeOrder = dateTimeOrder;
        this.driver = driver;
        this.record = record;
        this.vechicle = vechicle;
    }
}
