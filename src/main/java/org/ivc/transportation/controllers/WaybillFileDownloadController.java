/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
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
import org.ivc.transportation.config.trUtils.*;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.entities.Waybill;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

/**
 *
 * @author alextim
 */
@Controller
public class WaybillFileDownloadController {

    @PostMapping("/waybilldownload")
    public StreamingResponseBody download(HttpServletResponse response, Principal principal, @RequestBody Appointment appointment) throws FileNotFoundException, IOException {

        if (principal != null) { //возможно стоит добавить проверку, что авторизован Диспетчер
            if (appointment.getStatus() != AppointmentStatus.appointment_status_completed) {

                return outputStream -> {
                    String message = "Распоряжение не утверждено. Для печати "
                            + "путевого листа, прежде требуется согласовать и "
                            + "утвердить распоряжение.";
                    outputStream.write(message.getBytes());
                };
            }
            VehicleSpecialization specialization = appointment.getVehicleModel().getVehicleType().getSpecialization();
            switch (specialization) {
                case Пассажирский:
                case Легковой:                    
                    break;
                case Грузовой:
                case Спецтехника:
                    break;
                default:
                    break;

            };

            String excelFilePath = "Waybill.xls"; //
            Waybill waybill = appointment.getWaybill();
            Vehicle vechicle = appointment.getVehicle();
            Driver driver = appointment.getDriver();
            Record record = appointment.getAppointmentGroup().getRecord();
            LocalDateTime dateTime = appointment.getDateTime();

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
                            switch (namedCell) {
                                case серия:
                                    c.setCellValue(waybill.getSeries());
                                    break;
                                case номер:
                                    c.setCellValue(waybill.getNumber());
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
                                /* возможно не меняется
                            case организация:
                                c.setCellValue("организация Пока не поддерживается.");
                                break;
                            case адрес_телефон:
                                c.setCellValue("адрес_телефон Пока не поддерживается.");
                                break;
                                 */
                                case марка:
                                    c.setCellValue("марка Пока не поддерживается.");
                                    break;
                                case госномер:
                                    c.setCellValue(vechicle.getNumber());
                                    break;
                                case водитель:
                                    c.setCellValue(driver.getFirstname() + " " + driver.getName() + " " + driver.getSurname());
                                    break;
                                case удостоверение:
                                    c.setCellValue(" удостоверение Пока не поддерживается.");
                                    break;
                                case класс:
                                    c.setCellValue("класс Пока не поддерживается.");
                                    break;
                                case диспетчер:
                                    //TODO: после добавления извлекать из waybill
                                    User loginedUser = (User) ((Authentication) principal).getPrincipal();
                                    c.setCellValue(loginedUser.getUsername());
                                    break;
                                case механик:

                                    c.setCellValue(" механик Пока не поддерживается. Брать с формы?");
                                    break;
                                case время_выезда_по_графику:
                                    c.setCellValue(record.getDepartureTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                                    break;
                                case время_возвращения_по_графику:
                                    c.setCellValue(record.getReturnTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
                                    break;
                                case показание_спидометра_при_выезде:
                                    c.setCellValue(vechicle.getOdometr());
                                    break;
                                case принял:
                                    c.setCellValue(driver.getFirstname() + " "
                                            + driver.getName().charAt(0) + "."
                                            + driver.getSurname().charAt(0) + ".");
                                    break;
                                case сдал:
                                    c.setCellValue(driver.getFirstname() + " "
                                            + driver.getName().charAt(0) + "."
                                            + driver.getSurname().charAt(0) + ".");
                                    break;
                                case остаток_горючего_при_выезде:
                                    c.setCellValue(vechicle.getFuelRemnant());
                                    break;
                                case заказчик:
                                    //нужно короткое имя для Подразделения                                    
                                    c.setCellValue(record.getClaim().getDepartment().getName() + " " + record.getCarBoss());
                                    break;
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
                        System.out.println("В файле " + excelFilePath + "не обнаружены"
                                + " все ожидавшиеся именованные диапазоны."
                                + " Ячейки со следующими именами не были заполнены: "
                                + expectedNamedCells.toString());
                    }
                }

                try (FileOutputStream outputStream = new FileOutputStream("Путевой лист " + waybill.getSeries() + " №" + waybill.getNumber() + ".xls")) {
                    workbook.write(outputStream);
                    workbook.close();
                }

            } catch (IOException | EncryptedDocumentException ex) {
                ex.printStackTrace();
            }

            String fileName = "Waybill.xls";

            try {
                // get your file as InputStream
                response.setContentType("application/xls");
                response.setHeader("Content-Disposition", "attachment; filename=\"Путевой лист " + waybill.getSeries() + " №" + waybill.getNumber() + ".xls\"");
                InputStream inputStream = new FileInputStream(new File(fileName));
                return outputStream -> {
                    int nRead;
                    byte[] data = new byte[1024];
                    while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                        System.out.println("Waybill downloading..");
                        outputStream.write(data, 0, nRead);
                    }
                };
            } catch (IOException ex) {
                //log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
        return null;
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

}
