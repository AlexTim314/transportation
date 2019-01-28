package org.ivc.transportation.utils;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;
import org.ivc.transportation.entities.Claim;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@Entity
@NamedNativeQuery(name = "getRecords", query = "select start_date, end_date from record", resultSetMapping = "RecordDTOMapping")
@SqlResultSetMapping(name = "RecordDTOMapping",
        classes = {
            @ConstructorResult(targetClass = RecordDTO.class,
                    columns = {
                        @ColumnResult(name = "start_date", type = ZonedDateTime.class),
                        @ColumnResult(name = "end_date", type = ZonedDateTime.class),
                    }
            )}
)
public class RecordDTO implements Serializable {
    @Id
    private Long id;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    
    public RecordDTO(ZonedDateTime startDate, ZonedDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }    

}
