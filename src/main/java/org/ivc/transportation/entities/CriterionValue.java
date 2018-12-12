/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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

/**
 *
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"criterion", "record", "vehicle"})
@EqualsAndHashCode(exclude = {"criterion", "record", "vehicle"})
public class CriterionValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String crValue;

    @ManyToOne(fetch = FetchType.EAGER)
    private Record record;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER)
    private Criterion criterion;

    public CriterionValue(String value, Record record, Vehicle vehicle, Criterion criterion) {
        this.crValue = value;
        this.record = record;
        this.vehicle = vehicle;
        this.criterion = criterion;

    }

}
