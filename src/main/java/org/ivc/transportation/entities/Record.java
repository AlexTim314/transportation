/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.CascadeType;
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
import org.ivc.transportation.config.trUtils;
import org.ivc.transportation.config.trUtils.RecordStatus;

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
    private Date departureDate;

    @NonNull
    @Column(nullable = false)
    private Date returnDate;

    @NonNull
    @Column(nullable = false)
    private Time departureTime;

    @NonNull
    @Column(nullable = false)
    private Time returnTime;

    @NonNull
    @Column(nullable = false)
    private Time timeDelivery;

    @Column(length = 1024)
    private String carBoss;

    @NonNull
    @Column(nullable = false, length = 1024)
    private String purpose;
    
    @NonNull
    @Column(nullable = false, length = 512)
    private String type;

    @Column(length = 1024)
    private String serviceField;

    @Column(length = 512)
    private String templateName;

    @NonNull
    @Column(nullable = false)
    private RecordStatus status;

    @Column(length = 1024)
    private String description;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Claim claim;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;

    public Record(String weekHash, Date datetime, Date departureDate, Date returnDate, Time departureTime, String description,
            Time returnTime, Time timeDelivery, String type, String purpose, String serviceField, String templateName, String carBoss, Claim claim) {

        this.weekHash = weekHash;
        this.type = type;
        this.datetime = datetime;
        this.purpose = purpose;
        this.serviceField = serviceField;
        this.departureDate = departureDate;
        this.templateName = templateName;
        this.carBoss = carBoss;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.departureTime = departureTime;
        this.timeDelivery = timeDelivery;
        this.status = RecordStatus.record_status_created;
        this.description = description;
        this.claim = claim;

    }

}
