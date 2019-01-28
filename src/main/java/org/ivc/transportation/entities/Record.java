package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.utils.RecordDTO;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appointmentGroup"})
@ToString(exclude = {"appointmentGroup"})
@SqlResultSetMapping(name = "RecordDTOMapping",
        classes = {
            @ConstructorResult(targetClass = RecordDTO.class,
                    columns = {
                        @ColumnResult(name = "startDate"), @ColumnResult(name = "endDate")
                    }
            )}
)
@Entity
@Table(name = "record")
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "entrance_date", nullable = false)
    private ZonedDateTime entranceDate;

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id")
    private List<Appointment> appointments;

}
