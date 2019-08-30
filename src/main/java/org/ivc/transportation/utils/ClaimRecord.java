package org.ivc.transportation.utils;

import java.time.LocalDateTime;

/**
 *
 * @author Sokolov Slava
 */
public interface ClaimRecord {

    Long getClaim_id();
    
    Integer getSpecialization();
    
    String getVeh_type();

    String getPurpose();

    String getRoute();

    String getAffirmator();

    Long getRecord_id();

    LocalDateTime getStart_date();

    LocalDateTime getEntrance_date();

    LocalDateTime getEnd_date();

    String getCarbossfirstname();

    String getCarbossname();

    String getCarbosssurname();

    String getCarbossphone();

    String getCarbosspost();
    
    Long getDep_id();

    Long getAppointment_id();

    String getAppointment_note();
    
    Long getVeh_model_id();

    Long getOts_id();

    Long getVeh_id();

    Long getDriver_id();

    Integer getStatus();

}
