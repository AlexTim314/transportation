/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(exclude = {"criterion", "record", "vechicle"})
@EqualsAndHashCode(exclude = {"criterion", "record", "vechicle"})
public class CriterionValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String value;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Claim claim;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Vechicle vechicle;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Criterion criterion;

    public CriterionValue(String value, Claim claim, Vechicle vechicle, Criterion criterion) {
        this.value = value;
        this.claim = claim;
        this.vechicle = vechicle;
        this.criterion = criterion;

    }

}
