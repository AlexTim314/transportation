/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.exceptions.NullPrincipalException;
import org.ivc.transportation.exceptions.UnexpectedNullException;
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.utils.EntitiesUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author alextim
 */
public class WaybillFileDownloadController {
    
    @Autowired
    private DispatcherService dispatcherService;
    
    @Autowired
    private ServletContext servletContext;
    
    @PostMapping("/waybilldownload")
    public void download(HttpServletResponse response, Principal principal, @RequestBody Appointment appointment) throws FileNotFoundException, IOException {

        if (principal == null) {
            throw new NullPrincipalException("Неавторизованный доступ к данной странице закрыт. "
                    + "Пожалуйста <a href=\"/transportation/login\"> войдите в систему</a>.");
        }
        if (appointment == null) { unexpectedNull("Назначение"); }
        if (appointment.getStatus() != AppointmentStatus.READY) {

            String message = "Error2. Распоряжение не утверждено. Для печати "
                    + "путевого листа, прежде требуется согласовать и "
                    + "утвердить распоряжение." + appointment.getStatus();
            System.out.println(message);
        }

        String excelFilePath;
        VehicleSpecialization specialization = appointment.getVehicleModel().getVehicleType().getSpecialization();
        switch (specialization) {
            case пассажирский:
            case легковой:                
                excelFilePath = "Waybill6.xls";
                break;
            case грузовой:
            case спецтехника:
            default:
                excelFilePath = "Waybill3.xls";
                break;
        };

        /*   На данный момент рассматривается возможность исключить сущность Waybill
         
        Waybill waybill = appointment.getWaybill();
        if (waybill == null) { unexpectedNull("Путевой лист"); }
        */
        Vehicle vehicle = appointment.getVehicle();
        if (vehicle == null) { unexpectedNull("Транспортное средство"); }
        Driver driver = appointment.getDriver();
        if (driver == null) { unexpectedNull("Водитель"); }
        //TODO: добавить обработку случая, когда  Записей больше 1
        Record record = dispatcherService.findRecordsByAppointmentGroup(appointment.getAppointmentGroup()).get(0);
        Claim claim = dispatcherService.findClaimByRecord(record);
        LocalDateTime dateTime = appointment.getAppDateTime();

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
                                //c.setCellValue(waybill.getSeries());
                                c.setCellValue("Временно не поддержиавется");
                                break;
                            case номер:
                                //c.setCellValue(waybill.getNumber());
                                c.setCellValue("Временно не поддержиавется");
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
                                c.setCellValue(""
                                //        waybill.getDispatcher().getUserName()
                                );
                                break;
                            case механик:
                                c.setCellValue(appointment.getMechanic().getFirstname());
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
                                c.setCellValue(vehicle.getFuel());
                                break;
                            case заказчик:
                                c.setCellValue(claim.getDepartment().getShortname()+ " " + claim.getCarBoss());
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
                    System.out.println("В файле " + excelFilePath + " обнаружены"
                            + " не все ожидавшиеся именованные диапазоны."
                            + " Ячейки со следующими именами не были заполнены: "
                            + expectedNamedCells.toString());
                }
            }
            
           // workbook.createSheet("доплер");

            //String fileName = waybill.getSeries() + "_" + waybill.getNumber() + ".xls";
            String fileName = "DownloadWaybill.xls";
            File file = File.createTempFile("waybill", specialization.name());

           
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            workbook.close();

            try {
                MediaTypeUtils mediaTypeUtils = new MediaTypeUtils();
                MediaType mediaType = mediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

                response.setContentType(mediaType.getType());
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
                response.setContentLength((int) file.length());

                try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file))) {
                    BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
                    
                    byte[] buffer = new byte[1024];
                    int bytesRead = 0;
                    while ((bytesRead = inStream.read(buffer)) != -1) {
                        
                        System.out.println("Waybill downloading..(" + bytesRead +"bytes)");
                        outStream.write(buffer, 0, bytesRead);
                    }
                    
                    outStream.flush();
                }

            } catch (IOException ex) {
                throw new RuntimeException("IOError writing file to output stream");
            }

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }

    }

    private void unexpectedNull(String message) {
     throw new UnexpectedNullException("Внимание! Не обнаружены данные"
             + " необходимые для выполнения операции. Отсутствует значение для поля \"" + message 
             + "\". В случае повторения ошибки обратитесь к администратору.");
    }

    class MediaTypeUtils {

        // abc.zip
        // abc.pdf,..
        public MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
            // application/pdf
            // application/xml
            // image/gif, ...
            String mineType = servletContext.getMimeType(fileName);
            try {
                MediaType mediaType = MediaType.parseMediaType(mineType);
                return mediaType;
            } catch (Exception e) {
                return MediaType.APPLICATION_OCTET_STREAM;
            }
        }
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
