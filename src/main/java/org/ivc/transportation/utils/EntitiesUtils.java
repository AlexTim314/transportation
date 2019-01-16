package org.ivc.transportation.utils;

/**
 *
 * @author alextim
 */
public class EntitiesUtils {

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

    public static enum RecordStatus {
        CREATED,
        IN_PROGRESS,
        READY,
        COMPLETED,
        CANCELED;
    }

    public static enum AppointmentStatus {
        создано,
        выполняется,
        завершено,
        отменено;
    }

}
