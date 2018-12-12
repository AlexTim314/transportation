/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.config;

import java.sql.Date;

/**
 *
 * @author first
 */
public class trUtils {

    public static enum ClaimType {
        claim_type_weekly,
        claim_type_additional
    }

    public static enum RecordStatus {
        record_status_created,
        record_status_inprogress,
        record_status_completed,
        record_status_canceled
    }

    public static enum AppointmentStatus {
        appointment_status_created,
        appointment_status_inprogress,
        appointment_status_completed,
        appointment_status_canceled
    }
    
    
    public static enum VehicleSpecialization {
        Пассажирский,
        Легковой,
        Грузовой,
        Спецтехника;
    }
    
    public static class DateRange {
        public Date StartDate;
        public Date EndDate;
        
        public DateRange() {            
        }
    }
    
    public static enum NamedCell {
        серия,
        номер,        
        число,
        месяц,
        год,
        организация,
        адрес_телефон,
        марка,
        госномер,
        водитель,
        удостоверение,
        класс,
        диспетчер,
        механик,
        время_выезда_по_графику,
        время_возвращения_по_графику,
        показание_спидометра_при_выезде,    //одометра
        остаток_горючего_при_выезде,
        принял,
        сдал,
        заказчик
        ;
        
    }

    public static String errNotSpecifiedDepartmentException = "В Вашем пользовательском профиле не указано"
            + " подразделение. Поэтому неясно какие заявки должны"
            + " быть отображены. Обратитесь к администатору."
            + "<br> Department does not specified in your profile."
            + " Can't decide what claims must be shown. Contact your administrator.";

}
