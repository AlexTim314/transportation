package org.ivc.transportation.entities;

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
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author first
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"vehicleType"})
@EqualsAndHashCode(exclude = {"vehicleType"})
public class VehicleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(length = 1024)
    private String ModelName;

    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleType vehicleType;

    public VehicleModel(String ModelName, VehicleType vehicleType) {
        this.ModelName = ModelName;
        this.vehicleType = vehicleType;
    }

}
