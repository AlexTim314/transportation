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
 * @author Sokolov Slava & Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"department"})
@EqualsAndHashCode(exclude = {"department"})
public class Claim implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private Date clDate;  // clame date

    @Column(nullable = false)
    private Boolean affrimation;

    @Column(nullable = false)
    private byte tip;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    public Claim(Date clDate, Byte tip, Department dep) {
        this.clDate = clDate;
        this.affrimation = false;
        this.tip = tip;
        this.department = dep;

    }
}
