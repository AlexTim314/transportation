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
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.EntitiesUtils;

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
public class DriverInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private EntitiesUtils.DriverStatus status;
    
    @Column(length = 1024)
    private String note;

}
