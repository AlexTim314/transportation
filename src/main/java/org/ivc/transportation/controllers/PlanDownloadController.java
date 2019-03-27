/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import static org.ivc.transportation.controllers.WaybillFileDownloadController.getDriverNameWithInitials;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.RouteTask;
import org.ivc.transportation.entities.Vehicle;
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.services.VehicleService;
import org.ivc.transportation.utils.EntitiesUtils;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import org.ivc.transportation.utils.MediaTypeUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Класс для формирования и отправки пользователю файла с планом выхода
 * автомобилей.
 *
 * @author alextim
 */
@Controller
public class PlanDownloadController {

    @Autowired
    private DispatcherService dispatcherService;
    
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("planner/plandownload/{date}")
    @ResponseBody
    public FileSystemResource download(HttpServletResponse response, @PathVariable("date") String strDate) throws FileNotFoundException, IOException {

        LocalDate today = LocalDate.now();
        LocalDate purposeDate = LocalDate.parse(strDate, DateTimeFormatter.BASIC_ISO_DATE);

        System.out.println("plan.docx write");

        XWPFDocument document = new XWPFDocument();

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        //отступы 0,5 см - 283L
        pageMar.setLeft(BigInteger.valueOf(283L));
        pageMar.setTop(BigInteger.valueOf(283L));
        pageMar.setRight(BigInteger.valueOf(283L));
        pageMar.setBottom(BigInteger.valueOf(283L));

        //--- Установка Альбомной ориентации листа
        CTDocument1 doc = document.getDocument();
        CTBody body = doc.getBody();
        CTSectPr section = body.addNewSectPr();
        CTPageSz pageSize;

        if (section.isSetPgSz()) {
            pageSize = section.getPgSz();
        } else {
            pageSize = section.addNewPgSz();
        }

        pageSize.setOrient(STPageOrientation.LANDSCAPE);
        //Размер А4: 842 * 20 на 595 * 20
        pageSize.setW(BigInteger.valueOf(842 * 20));
        pageSize.setH(BigInteger.valueOf(595 * 20));
        //end--- Установка Альбомной ориентации листа

        //------------------ Шапка
        XWPFTable table = document.createTable(1, 1);
        table.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setLeftBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setRightBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setInsideVBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");

        XWPFTableRow row = table.getRow(0);
        XWPFTableCell cell = row.addNewTableCell();
        TableWidthType widthType = TableWidthType.PCT;
        table.setWidthType(widthType);

        textToParagraph(cell.getParagraphs().get(0), "УТВЕРЖДАЮ", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "Начальник Комплекса АТО", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "К.К.Мавлютов", 12, ParagraphAlignment.RIGHT); //TODO: вытаскивать из данных КАТО        
        textToParagraph(cell.addParagraph(), formateDate(today), 12, ParagraphAlignment.LEFT);
        row.getCell(0).setWidth("65.00%");
        row.getCell(1).setWidth("35.00%");

        //END------------------ Шапка
        //---------------- Заполнение файла
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingBefore(200);
        textToParagraph(paragraph, "ПЛАН", "Times New Roman", 12, true, ParagraphAlignment.CENTER);
        String planeDate = formateDate(purposeDate);
        textToParagraph(document.createParagraph(), "выхода автомобилей Комплекса автотранспортного обеспечения на "
                + planeDate, "Times New Roman", 12, true, ParagraphAlignment.CENTER);

        table = document.createTable();
        table.setWidthType(widthType);
        table.setCellMargins(0, -10, 0, -10);

        row = table.getRow(0);

        boolean isBold = true;
        int fontSize = 8;
        ParagraphAlignment parAligCenter = ParagraphAlignment.CENTER;
        textToParagraph(row.getCell(0).getParagraphs().get(0), " №  п/п",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Марка автомобиля",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Гос. номер автомобиля",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "№ ОТС",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Цели и задачи поездки",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "В чьё распоряжение",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Маршрут движения",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Время выхода",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Фамилия водителя",
                "Times New Roman", fontSize, isBold, parAligCenter);

        setCellsWidth(row);

        CTHMerge hMergeRestart = CTHMerge.Factory.newInstance();
        hMergeRestart.setVal(STMerge.RESTART);
        CTHMerge hMergeContinue = CTHMerge.Factory.newInstance();
        hMergeContinue.setVal(STMerge.CONTINUE);

        List<Appointment> appointments = dispatcherService.getAppointmentsForPlan(AppointmentStatus.READY, purposeDate);

        List<RowData> rowDataList = new LinkedList<>();
        RowData prevRow = null;
        for (Appointment a : appointments) {
            String vehicleNumber = a.getVehicle().getNumber();
            Record record = dispatcherService.findRecordByAppointment(a);
            if ((prevRow != null) && (vehicleNumber.equalsIgnoreCase(prevRow.vehicleNumber))) {
                //TODO: обработка написана из предположения, что если на одну машину
                //есть два назначения на одну дату, то у них могут различаться только
                //время и водитель. И тогда они должны быть указаны в одной сроке таблицы друг под другом.
                //!Надо выяснить как происходит подача заявок в таком случае.
                //Требуется чтобы назначения были отсортированы так, что на одну машину они идут подряд.
                String time = record.getStartDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                        + "-"
                        + record.getEndDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                prevRow.time.add(time);
                prevRow.driver.add(getDriverNameWithInitials(a.getDriver()));
            } else { 
                RowData rowData = new RowData();
                rowData.vehicleNumber = vehicleNumber;

                Claim claim = dispatcherService.findClaimByRecord(record);

                rowData.carBoss = claim.getCarBoss().getFirstname();
                rowData.driver.add(getDriverNameWithInitials(a.getDriver()));
                rowData.departmentName = claim.getDepartment().getFullname();
                rowData.purposes = claim.getPurpose();
                rowData.vehicleModelName = a.getVehicleModel().getModelName();

                String[] tmpArr = a.getTransportDep().getShortname().split(" ");
                rowData.transportDepNumber = tmpArr[tmpArr.length - 1];

                String route = "";
                List<RouteTask> routeTasks = claim.getRouteTasks();

                if (!routeTasks.isEmpty()) {
                    routeTasks.sort((t1, t2) -> {
                        return Integer.compare(t1.getOrderNum(), t2.getOrderNum());
                    });
                    for (RouteTask routeTask : routeTasks) {
                        route = route + routeTask.getPlace().getName() + ", ";
                    }
                    route = route.substring(0, route.length() - 2);
                }
                rowData.route = route;

                rowData.time.add(record.getStartDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                        + "-"
                        + record.getEndDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));

                prevRow = rowData;
                rowDataList.add(rowData);
            }
        }

        //-----------тестовые данные для проверки отображения
        if (appointments.isEmpty()) {
            fullfillTestData(rowDataList);
        }
        //end-----------тестовые данные для проверки

        String currentDepName = "";
        int number = 1;
        for (RowData rowData : rowDataList) {
            if (!currentDepName.equalsIgnoreCase(rowData.departmentName)) {
                currentDepName = rowData.departmentName.toUpperCase();
                XWPFTableRow mergingRow = table.createRow();
                System.out.println("mergingRow.isCantSplitRow" + mergingRow.isCantSplitRow());
                textToParagraph(mergingRow.getCell(0).getParagraphs().get(0),
                        currentDepName, "Times New Roman", 8, true, ParagraphAlignment.CENTER);
                mergingRow.getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                for (int i = 1; i < mergingRow.getTableCells().size(); i++) {
                    mergingRow.getCell(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                }
            }

            row = table.createRow();
            setCellsWidth(row);

            if (false) { // в отчётной версии плана цвета не нужны. Нужна ли будет рабочая версия пока не ясно
                setRowColor(row, getStrColorByTransportDep(rowData.transportDepNumber));
            }

            isBold = true;
            fontSize = 8;
            ParagraphAlignment parAligLeft = ParagraphAlignment.LEFT;
            textToParagraph(row.getCell(0).getParagraphs().get(0), Integer.toString(number++),
                    "Times New Roman", fontSize, isBold, parAligCenter);
            textToParagraph(row.getCell(1).getParagraphs().get(0), rowData.vehicleModelName,
                    "Times New Roman", fontSize, isBold, parAligLeft);
            textToParagraph(row.getCell(2).getParagraphs().get(0), rowData.vehicleNumber,
                    "Times New Roman", fontSize, isBold, parAligCenter);
            textToParagraph(row.getCell(3).getParagraphs().get(0), rowData.transportDepNumber,
                    "Times New Roman", fontSize, isBold, parAligCenter);
            textToParagraph(row.getCell(4).getParagraphs().get(0), rowData.purposes,
                    "Times New Roman", fontSize, false, parAligLeft);
            textToParagraph(row.getCell(5).getParagraphs().get(0), rowData.carBoss,
                    "Times New Roman", fontSize, false, parAligLeft);
            textToParagraph(row.getCell(6).getParagraphs().get(0), rowData.route,
                    "Times New Roman", 7, false, parAligCenter);
            listToCell(row.getCell(7), rowData.time,
                    "Times New Roman", fontSize, false, parAligLeft);
            listToCell(row.getCell(8), rowData.driver,
                    "Times New Roman", fontSize, false, parAligLeft);

            row.getTableCells().forEach((cl) -> {
                cl.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            });
        }

        //List<Vehicle> vehicles = new ArrayList<>();//TODO: нужен запрос, который будет выдавать все машины, которые указываются в конце списка. 
        List<Vehicle> vehicles = dispatcherService.getVehiclesForPlan(EntitiesUtils.VehicleStatus.ремонт);
        vehicles.addAll(dispatcherService.getVehiclesForPlan(EntitiesUtils.VehicleStatus.другое));
        //Скорее всего отбор по статусу "Другое" в самом актуальном VehicleInfo. Сортировка по тексту в note.
        vehicles = vehicles.stream().filter((t) -> {
            return t.getModel() != null;
        }).filter((t) -> {
            return t.getNumber() != null;
        }).collect(Collectors.toList());
       
        for (Vehicle vehicle : vehicles) {
            row = table.createRow();
            isBold = true;
            fontSize = 8;
            ParagraphAlignment parAligLeft = ParagraphAlignment.LEFT;
            textToParagraph(row.getCell(0).getParagraphs().get(0), Integer.toString(number++),
                    "Times New Roman", fontSize, isBold, parAligCenter);
            textToParagraph(row.getCell(1).getParagraphs().get(0), vehicle.getModel().getModelName(),
                    "Times New Roman", fontSize, isBold, parAligLeft);
            textToParagraph(row.getCell(2).getParagraphs().get(0), vehicle.getNumber(),
                    "Times New Roman", fontSize, isBold, parAligCenter);
            String[] tmpArr = vehicle.getTransportDep().getShortname().split(" ");
            textToParagraph(row.getCell(3).getParagraphs().get(0), tmpArr[tmpArr.length - 1],
                    "Times New Roman", fontSize, isBold, parAligCenter);
            textToParagraph(row.getCell(4).getParagraphs().get(0), vehicle.getNote(),
                    "Times New Roman", fontSize, false, parAligLeft);
            

            int cellIndex = 4;
            row.getCell(cellIndex).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            for (int i = cellIndex; i < row.getTableCells().size(); i++) {
                row.getCell(i).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }

        }

        paragraph = document.createParagraph();
        paragraph.setSpacingBefore(200);
        textToParagraph(paragraph, "Заместитель начальника Комплекса АТО - начальник Службы"
                + "                                                                              "
                + "В.В. Гольц", "Times New Roman", 12, false, ParagraphAlignment.CENTER); //TODO: вытаскивать из данных КАТО     

        File file = File.createTempFile("plan", LocalDate.now().toString());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        document.write(fileOutputStream);
        document.close();

        System.out.println(" written successully");

        //-------------- отправка файла
        MediaTypeUtils mediaTypeUtils = new MediaTypeUtils();
        String fileName = "plan" + today.toString() + ".docx";
        MediaType mediaType = mediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        response.setContentType(mediaType.getType());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        response.setContentLength((int) file.length());
        FileSystemResource resource = new FileSystemResource(file);
        return resource;

        /*  try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file))) {
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
        
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
        
        System.out.println("Plan downloading..(" + bytesRead + "bytes)");
        outStream.write(buffer, 0, bytesRead);
        }
        
        outStream.flush();
        }*/
    }

    private String formateDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("« dd » "
                + date.getMonth().getDisplayName(TextStyle.FULL, new Locale("ru"))
                + "  YYYY г."));
    }

    private void setCellsWidth(XWPFTableRow row) {
        row.getTableCells().get(0).setWidth("02.45%");
        row.getTableCells().get(1).setWidth("12.80%");
        row.getTableCells().get(2).setWidth("07.09%");
        row.getTableCells().get(3).setWidth("02.53%");
        row.getTableCells().get(4).setWidth("31.54%");
        row.getTableCells().get(5).setWidth("12.91%");
        row.getTableCells().get(6).setWidth("12.28%");
        row.getTableCells().get(7).setWidth("08.91%");
        row.getTableCells().get(8).setWidth("09.50%");
    }

    private void setRowColor(XWPFTableRow row, String color) {
        row.getTableCells().forEach((cell) -> {
            cell.setColor(color);
        });
    }

    private String getStrColorByTransportDep(String transportDepNumber) {
        switch (transportDepNumber) {
            case "1":
                return "00B040";
            case "2":
                return "90B0FF";
            case "3":
                return "FFB010";
            case "4":
                return "E0E010";
            case "5":
                return "90EE90";
            case "6":
            case "7":
            case "8":
                return "EEA0A0";
            default:
                return "FFFFFF";
        }
    }

    private void fullfillTestData(List<RowData> rowDataList) {

        RowData rd = new RowData();
        rd.carBoss = "Старший машины С.М.";
        rd.departmentName = "ЦИ-1";
        rd.driver.add("Водитель В.В.");
        rd.purposes = "Перевозка пассажиров";
        rd.route = "Пл.10, Пл. 95";
        rd.time.add("8:00-19:00");
        rd.transportDepNumber = "1";
        rd.vehicleModelName = "Газ 2121";
        rd.vehicleNumber = "Е777КХ";

        rowDataList.add(rd);

        rd = new RowData();
        rd.carBoss = "Старший машины С.М.";
        rd.departmentName = "ЦИ-1";
        rd.driver.add("Водитель В.В.");
        rd.purposes = "Перевозка грузов";
        rd.route = "Пл.10, Пл. 95";
        rd.time.add("8:00-19:00");
        rd.transportDepNumber = "2";
        rd.vehicleModelName = "Газ 2121";
        rd.vehicleNumber = "Е777КХ";

        rowDataList.add(rd);

        rd = new RowData();
        rd.carBoss = "Старший машины С.М.";
        rd.departmentName = "ЦИ-2";
        rd.driver.add("Водитель В.В.");
        rd.purposes = "Нужно поездить туда-сюда";
        rd.route = "Пл.10, Пл. 95";
        rd.time.add("8:00-19:00");
        rd.transportDepNumber = "4";
        rd.vehicleModelName = "Газ 2121";
        rd.vehicleNumber = "Е777КХ";

        rowDataList.add(rd);

        rd = new RowData();
        rd.carBoss = "Старший машины С.М.";
        rd.departmentName = "ЦИ-2";
        rd.driver.add("Водитель В.В.");
        rd.purposes = "Нужно поездить туда-сюда";
        rd.route = "Пл.10, Пл. 95";
        rd.time.add("8:00-19:00");
        rd.transportDepNumber = "5";
        rd.vehicleModelName = "Газ 2121";
        rd.vehicleNumber = "Е777КХ";

        rowDataList.add(rd);

        rd = new RowData();
        rd.carBoss = "Старший машины С.М.";
        rd.departmentName = "ЦИ-1";
        rd.driver.add("Водитель В.В.");
        rd.purposes = "Перевозка грузов";
        rd.route = "Пл.10, Пл. 95";
        rd.time.add("8:00-19:00");
        rd.transportDepNumber = "3";
        rd.vehicleModelName = "Газ 2121";
        rd.vehicleNumber = "Е777КХ";

        rowDataList.add(rd);

        rd = new RowData();
        rd.carBoss = "Старший машины С.М.";
        rd.departmentName = "ЦИ-2";
        rd.driver.add("Водитель В.В.");
        rd.purposes = "Нужно поездить туда-сюда";
        rd.route = "Пл.10, Пл. 95";
        rd.time.add("8:00-19:00");
        rd.transportDepNumber = "8";
        rd.vehicleModelName = "Газ 2121";
        rd.vehicleNumber = "Е777КХ";

        rowDataList.add(rd);

    }

    private class RowData {

        String departmentName;
        String vehicleModelName;
        String vehicleNumber;
        String transportDepNumber;
        String purposes;
        String carBoss;
        String route;
        List<String> time = new ArrayList<>();
        List<String> driver = new ArrayList<>();
    }

    private void textToParagraph(XWPFParagraph paragraph, String text,
            String fontFamily, int fontSize, boolean isBold,
            ParagraphAlignment paragraphAlignment) {
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(paragraphAlignment);
        paragraph.setSpacingAfter(0);
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setBold(isBold);
        run.setText(text);
    }

    private void textToParagraph(XWPFParagraph paragraph, String text,
            int fontSize, ParagraphAlignment paragraphAlignment) {
        textToParagraph(paragraph, text, "Times New Roman", fontSize, true, paragraphAlignment);
    }

    private void listToCell(XWPFTableCell cell, List<String> list,
            String fontFamily, int fontSize, boolean isBold,
            ParagraphAlignment paragraphAlignment) {

        if (list.isEmpty()) {
            return;
        }
        textToParagraph(cell.getParagraphs().get(0), list.get(0),
                "Times New Roman", fontSize, isBold, paragraphAlignment);
        for (int i = 1; i < list.size(); i++) {
            textToParagraph(cell.addParagraph(), list.get(i),
                    "Times New Roman", fontSize, isBold, paragraphAlignment);
        }
    }

}
