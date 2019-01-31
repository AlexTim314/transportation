package org.ivc.transportation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@Table(name = "vehicle_model")
public class VehicleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "model_name", length = 64, nullable = false)
    private String modelName;

    @ManyToOne
    private VehicleType vehicleType;

    public VehicleModel(String modelName, VehicleType vehicleType) {
        this.modelName = modelName;
        this.vehicleType = vehicleType;
    }

}
