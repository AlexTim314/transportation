package org.ivc.transportation.utils;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;

/**
 *
 * @author Sokolov Slava
 */
@Getter
@Setter
@Entity
//@NamedNativeQuery(name = "getRecords", query = "select start_date, end_date from record", resultSetMapping = "RecordDTOMapping")
@NamedNativeQuery(name = "getRecords", query = "select id, record from record", resultSetMapping = "RecordDTOMapping")
@SqlResultSetMapping(name = "RecordDTOMapping",
        classes = {
            @ConstructorResult(targetClass = RecordDTO.class,
                    columns = {
//                        @ColumnResult(name = "start_date", type = ZonedDateTime.class),
//                        @ColumnResult(name = "end_date", type = ZonedDateTime.class),
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "record", type = Record.class)
                    }
            )}
)
public class RecordDTO implements Serializable {
    @Id
    private Long id;
//    private ZonedDateTime startDate;
//    private ZonedDateTime endDate;
//    
//    public RecordDTO(ZonedDateTime startDate, ZonedDateTime endDate) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }    
    private Record record;
    
    public RecordDTO(Long id, Record record) {
        this.id = id;
        this.record = record;        
    }

}
