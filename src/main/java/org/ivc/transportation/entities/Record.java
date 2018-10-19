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
@ToString(exclude = {"claim", "plan"})
@EqualsAndHashCode(exclude = {"claim", "plan"})
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 32)
    private String weekHash;

    @NonNull
    @Column(nullable = false)
    private Date datetime;

    @NonNull
    @Column(nullable = false)
    private String usageTime;

    @NonNull
    @Column(nullable = false)
    private Date arrivalTime;

    @NonNull
    @Column(nullable = false, length = 1024)
    private String purpose;

    @NonNull
    @Column(nullable = false)
    private String status;

    @Column(length = 1024)
    private String serviceField;

    @Column(length = 512)
    private String templateName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Claim claim;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;

    public Record(String weekHash, Date datetime, String usageTime, Date arrivalTime,
            String purpose, String status, String serviceField, String templateName, Claim claim, Plan plan) {
        this.arrivalTime = arrivalTime;
        this.claim = claim;
        this.datetime = datetime;
        this.plan = plan;
        this.purpose = purpose;
        this.serviceField = serviceField;
        this.status = status;
        this.templateName = templateName;
        this.usageTime = usageTime;
        this.weekHash = weekHash;

    }

}
