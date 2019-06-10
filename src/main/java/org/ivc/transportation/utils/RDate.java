/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *
 * @author first
 */
@Data
public class RDate {

    private LocalDateTime startDate;
    private LocalDateTime entranceDate;
    private LocalDateTime endDate;

    RDate(LocalDateTime StartDate, LocalDateTime EntranceDate, LocalDateTime EndDate) {
        this.startDate = StartDate;
        this.entranceDate = EntranceDate;
        this.endDate = EndDate;
    }
}
