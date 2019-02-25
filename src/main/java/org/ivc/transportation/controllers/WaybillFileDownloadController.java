package org.ivc.transportation.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.CarBoss;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.exceptions.NullPrincipalException;
import org.ivc.transportation.exceptions.UnexpectedNullException;
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.utils.EntitiesUtils.*;
import org.ivc.transportation.utils.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author alextim
 */
@Controller
public class WaybillFileDownloadController {

    @Autowired
    private DispatcherService dispatcherService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/dispatcher/waybilldownload/{apptId}")
    @ResponseBody
    public FileSystemResource download(HttpServletResponse response, Principal principal, @PathVariable("apptId") Long apptId) throws FileNotFoundException, IOException {

        if (principal == null) {
            throw new NullPrincipalException("Неавторизованный доступ к данной странице закрыт. "
                    + "Пожалуйста <a href=\"/transportation/login\"> войдите в систему</a>.");
        }

        Appointment appointment = dispatcherService.getAppointmentById(apptId);

        if (appointment == null) {
            unexpectedNull("Назначение");
        }
        if (appointment.getStatus() != AppointmentStatus.READY) {

            String message = "Error2. Распоряжение " + appointment.getId() + " не утверждено. Для печати "
                    + "путевого листа, прежде требуется согласовать и "
                    + "утвердить распоряжение." + appointment.getStatus();
            System.out.println(message);
        }

        String excelFilePath;
        VehicleSpecialization specialization = appointment.getVehicleModel().getVehicleType().getSpecialization();
        switch (specialization) {
            case пассажирский:
                excelFilePath = "form6spec.xls";
                break;
            case легковой:
                excelFilePath = "form3.xls";
                break;
            case грузовой:
                excelFilePath = "form4p.xls";
                break;
            case спецтехника:
            default:
                excelFilePath = "form3spec.xls";
                break;
        };

        /*   На данный момент рассматривается возможность исключить сущность Waybill         
        Waybill waybill = appointment.getWaybill();
        if (waybill == null) { unexpectedNull("Путевой лист"); }
         */
        Vehicle vehicle = appointment.getVehicle();
        if (vehicle == null) {
            unexpectedNull("Транспортное средство");
        }
        Driver driver = appointment.getDriver();
        if (driver == null) {
            unexpectedNull("Водитель");
        }

        Record record = dispatcherService.findRecordByAppointment(appointment);
        Claim claim = dispatcherService.findClaimByRecord(record);
        LocalDateTime dateTime = appointment.getCreationDate();

        try {
            Workbook workbook;
            try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
                workbook = WorkbookFactory.create(inputStream);

                List<? extends Name> allNames = workbook.getAllNames();
                List<NamedCell> expectedNamedCells = Arrays.stream(NamedCell.values()).collect(Collectors.toList());

                for (Name name : allNames) {
                    String cellName = name.getNameName().trim().toLowerCase().replace(" ", "_");
                    try {
                        NamedCell namedCell = NamedCell.valueOf(cellName);
                        Cell c = getCell(workbook, name);
                        try {
                            switch (namedCell) {
                                case серия:
                                    //TODO: узнать как формировать номер серии
                                    //c.setCellValue(waybill.getSeries());
                                    c.setCellValue("1 - ");
                                    break;
                                case номер:
                                    //c.setCellValue(waybill.getNumber());
                                    c.setCellValue(appointment.getId());
                                    break;
                                case число:
                                    c.setCellValue(dateTime.getDayOfMonth());
                                    break;
                                case месяц:
                                    c.setCellValue(dateTime.getMonth().getDisplayName(TextStyle.FULL, new Locale("ru")));
                                    break;
                                case год:
                                    c.setCellValue(dateTime.getYear());
                                    break;
                                case организация:
                                    c.setCellValue(claim.getDepartment().getFullname()
                                            + " " + claim.getDepartment().getAddress());
                                    break;
                                /* 
                                case адрес_телефон:
                                c.setCellValue();
                                break;*/
                                case марка:
                                    c.setCellValue(vehicle.getModel().getModelName());
                                    break;

                                case госномер:
                                    c.setCellValue(vehicle.getNumber());
                                    break;
                                case водитель:
                                    c.setCellValue(driver.getFirstname() + " " + driver.getName() + " " + driver.getSurname());
                                    break;
                                case удостоверение:
                                    c.setCellValue(driver.getDriverLicense());
                                    break;
                                case класс:
                                    c.setCellValue(driver.getDriverClass());
                                    break;
                                case диспетчер:
                                    c.setCellValue(appointment.getCreator().getFullName());
                                    break;
                                case механик:
                                    String text = appointment.getMechanic() != null ? appointment.getMechanic().getFirstname() : "Не указан";
                                    c.setCellValue(text);
                                    break;
                                case время_выезда_по_графику:
                                    c.setCellValue(record.getStartDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                                    break;
                                case время_возвращения_по_графику:
                                    c.setCellValue(record.getEndDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                                    break;
                                case показание_спидометра_при_выезде:
                                    c.setCellValue(vehicle.getOdometr());
                                    break;
                                case принял:
                                    c.setCellValue(getDriverNameWithInitials(driver));
                                    break;
                                case сдал:
                                    c.setCellValue(getDriverNameWithInitials(driver));
                                    break;
                                case остаток_горючего_при_выезде:
                                    c.setCellValue(vehicle.getFuel());
                                    break;
                                case заказчик:
                                    c.setCellValue(claim.getDepartment().getShortname() + " "
                                            + getCarBossNameWithInitials(claim.getCarBoss()));
                                    break;
                            }
                        } catch (NullPointerException ex) {
                            System.out.println("При заполнении путевого листа"
                                    + " произошла ошибка. Одно из требуемых"
                                    + " полей не имеет знаения. Исключение: "
                                    + ex.toString());
                        }

                        expectedNamedCells.remove(namedCell);
                    } catch (IllegalArgumentException e) {
                        System.out.println("В файле " + excelFilePath + " обнаружен"
                                + " именованный диапазон, который не ожидался."
                                + " Имя " + cellName + " не представлено в списке возможных."
                                + " Никаких действий не выполнено.");
                    }
                }
                if (!expectedNamedCells.isEmpty()) {
                    System.out.println("В файле " + excelFilePath + " обнаружены"
                            + " не все ожидавшиеся именованные диапазоны."
                            + " Ячейки со следующими именами не были заполнены: "
                            + expectedNamedCells.toString());
                }
            }

            String fileName = "Путевой_" + dateTime.format(DateTimeFormatter.ofPattern("YYYY.MM.dd")) + "_id" + appointment.getId() + ".xls";

            File file = File.createTempFile("waybill", specialization.name());

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            workbook.close();

            MediaTypeUtils mediaTypeUtils = new MediaTypeUtils();
            MediaType mediaType = mediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

            response.setContentType(mediaType.getType());
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
            response.setContentLength((int) file.length());

            FileSystemResource resource = new FileSystemResource(file);
            return resource;
            /* InputStream inputStream = new FileInputStream(file);
              return outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    System.out.println("Writing some bytes..");
                    outputStream.write(data, 0, nRead);
                }
            };
             */
 /*try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file))) {
            BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
            
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inStream.read(buffer)) != -1) {
            
            System.out.println("Waybill downloading..(" + bytesRead + "bytes)");
            outStream.write(buffer, 0, bytesRead);
            }
            
            outStream.flush();
            }
             */
        } catch (EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
        System.out.println("return null");
        return null;
    }

    private void unexpectedNull(String message) {
        throw new UnexpectedNullException("Внимание! Не обнаружены данные"
                + " необходимые для выполнения операции. Отсутствует значение для поля \"" + message
                + "\". В случае повторения ошибки обратитесь к администратору.");
    }

    private Cell getCell(Workbook workbook, Name name) {
        AreaReference aref = new AreaReference(name.getRefersToFormula(), SpreadsheetVersion.EXCEL2007);
        CellReference[] crefs = aref.getAllReferencedCells();
        //пока предполагается, что все именованные ячейки будут одиночными, а не диапазонами
        CellReference cellRef = crefs[0];
        Sheet s = workbook.getSheet(cellRef.getSheetName());
        Row r = s.getRow(cellRef.getRow());
        return r.getCell(cellRef.getCol());
    }

    public static String getDriverNameWithInitials(Driver driver) {
        return driver.getFirstname() + " "
                + driver.getName().charAt(0) + "."
                + driver.getSurname().charAt(0) + ".";
    }

    public static String getCarBossNameWithInitials(CarBoss carBoss) {
        return carBoss.getFirstname() + " "
                + carBoss.getName().charAt(0) + "."
                + carBoss.getSurname().charAt(0) + ".";
    }

}
