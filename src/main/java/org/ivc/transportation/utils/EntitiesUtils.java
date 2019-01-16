/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

/**
 *
 * @author alextim
 */
public class EntitiesUtils {

    public static enum VehicleSpecialization {
        Пассажирский,
        Легковой,
        Грузовой,
        Спецтехника;
    }

    public static enum VehicleStatus {
        Исправно,
        Неисправно,
        Неработоспособно,
        Ремонт,
        Списано,
        Другое;
    }

    public static enum DriverStatus {
        Работоспособен,
        Отпуск,
        Больничный,
        Отгул,
        Уволен,
        Другое;
    }

    public static enum AppointmentStatus {
        appointment_status_created,
        appointment_status_inprogress,
        appointment_status_completed,
        appointment_status_canceled
    }

    /**
     * Перечисление используемое при заполнении путевого листа. Именованные
     * диапазоны в файле Excel.
     */
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
        показание_спидометра_при_выезде, //одометра
        остаток_горючего_при_выезде,
        принял,
        сдал,
        заказчик;

    }

}
