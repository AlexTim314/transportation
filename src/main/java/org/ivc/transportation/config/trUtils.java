/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.config;

/**
 *
 * @author first
 */
public class trUtils {

    public static enum ClaimType {
        claim_type_weekly,
        claim_type_spec,
        claim_type_car,
        claim_type_urgent
    }

    public static enum RecordStatus {
        record_status_created,
        record_status_inprogress,
        record_status_completed,
        record_status_canceled
    }

}
