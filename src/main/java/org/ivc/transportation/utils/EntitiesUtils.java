package org.ivc.transportation.utils;

/**
 *
 * @author alextim
 */
public class EntitiesUtils {
    
    public static final String USER_CANCEL_STR = "Отменено пользователем. ";
    public static final String PLANNER_CANCEL_STR = "Отменено планировщиком. ";
    public static final String DISPATCHER_CANCEL_STR = "Отменено диспетчером. ";

    public static enum VehicleSpecialization {
        пассажирский,
        легковой,
        грузовой,
        спецтехника;
    }

    public static enum VehicleStatus {
        исправно,
        неисправно,
        неработоспособно,
        ремонт,
        списано,
        другое;
    }

    public static enum DriverStatus {
        работоспособен,
        отпуск,
        больничный,
        отгул,
        уволен,
        другое;
    }

    public static enum AppointmentStatus {
        IN_PROGRESS,
        READY,
        COMPLETED,
        CANCELED_BY_USER,
        CANCELED_BY_PLANNER,
        CANCELED_BY_DISPATCHER;
    }
    
    public static enum FuelType {
        Бензин,
        Дизель,
        Газ,
        Керосин;
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
