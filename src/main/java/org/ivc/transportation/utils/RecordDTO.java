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

}
