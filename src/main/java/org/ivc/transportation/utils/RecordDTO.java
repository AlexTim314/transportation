package org.ivc.transportation.utils;

import java.time.ZonedDateTime;
import lombok.Data;

/**
 *
 * @author Sokolov Slava
 */
@Data
public class RecordDTO {

    private ZonedDateTime endDate;
    private ZonedDateTime startDate;

    public RecordDTO(ZonedDateTime startDate, ZonedDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RecordDTO() {
    }

}
