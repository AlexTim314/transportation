package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.EntitiesUtils.VehicleSpecialization;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {})
@ToString(exclude = {})
@Entity
public class VehicleType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(length = 64)
    private String type;

    @NonNull
    private VehicleSpecialization specialization;

    public VehicleType(String type, VehicleSpecialization specialization) {
        this.type = type;
        this.specialization = specialization;
    }
    
}
