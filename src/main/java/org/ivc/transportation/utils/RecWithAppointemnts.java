package org.ivc.transportation.utils;

import java.util.List;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Record;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"transportDep"})
@ToString(exclude = {"transportDep"})
@SqlResultSetMapping(
    name="groupDetailsMapping",
    classes={
        @ConstructorResult(
            targetClass=RecWithAppointemnts.class,
            columns={
                @ColumnResult(name="GROUP_ID"),
                @ColumnResult(name="USER_ID")
            }
        )
    }
)
public class RecWithAppointemnts {

    private Record record;
    private List<Appointment> appointments;

}
