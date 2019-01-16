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
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 *
 * @author first
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"vehicleType"})
@EqualsAndHashCode(exclude = {"vehicleType"})
@Entity
public class VehicleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(length = 64)
    private String modelName;

    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleType vehicleType;

    public VehicleModel(String modelName, VehicleType vehicleType) {
        this.modelName = modelName;
        this.vehicleType = vehicleType;
    }

}
