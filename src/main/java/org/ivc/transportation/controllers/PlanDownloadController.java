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
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
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
import org.ivc.transportation.services.DispatcherService;
import org.ivc.transportation.utils.EntitiesUtils.AppointmentStatus;
import org.ivc.transportation.utils.MediaTypeUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
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
    private ServletContext servletContext;

    @GetMapping("planner/plandownload/")
    @ResponseBody
    public FileSystemResource download(HttpServletResponse response) throws FileNotFoundException, IOException {
        ZonedDateTime date = ZonedDateTime.now();
        ZonedDateTime tomorrow = ZonedDateTime.now().plusDays(1);

        System.out.println("plan.docx write");

        XWPFDocument document = new XWPFDocument();

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
        pageSize.setW(BigInteger.valueOf(842 * 20));
        pageSize.setH(BigInteger.valueOf(595 * 20));

        //end--- Установка Альбомной ориентации листа
        File file = File.createTempFile("plan", LocalDate.now().toString());
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        //------------------ Шапка
        XWPFTable table = document.createTable(1, 1);
        table.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");

        XWPFTableRow row = table.getRow(0);
        XWPFTableCell cell = row.addNewTableCell();
        TableWidthType widthType = TableWidthType.DXA;
        table.setWidthType(widthType);
        //row.getCell(0).setWidthType(widthType);
        row.getCell(0).setWidth("20");
        //row.getCell(1).setWidthType(widthType);
        row.getCell(1).setWidth("10");

        textToParagraph(cell.getParagraphs().get(0), "УТВЕРЖДАЮ", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "Начальник Комплекса АТО", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "К.К.Мавлютов", 12, ParagraphAlignment.RIGHT); //TODO: вытаскивать из данных КАТО
        //LocalDate now = LocalDate.now(); //TODO: уточнить, что допустимо время текущего момента, а не брать из заявки, например        
        textToParagraph(cell.addParagraph(), formateDate(date.toLocalDate()), 12, ParagraphAlignment.LEFT);

        //END------------------ Шапка
        //---------------- Заполнение файла
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingBefore(200);
        textToParagraph(paragraph, "ПЛАН", "Times New Roman", 12, true, ParagraphAlignment.CENTER);
        String planeDate = formateDate(tomorrow.toLocalDate());//TODO: Дата вычисляется прибавкой 1го дня, что не правильно для пятницы и для других назначений не на завтра
        textToParagraph(document.createParagraph(), "выхода автомобилей Комплекса автотранспортного обеспечения на "
                + planeDate, "Times New Roman", 12, true, ParagraphAlignment.CENTER);

        table = document.createTable();
        //table.setWidthType(TableWidthType.PCT);        

        row = table.getRow(0);
        boolean isBold = true;
        int fontSize = 8;
        ParagraphAlignment parAligCenter = ParagraphAlignment.CENTER;
        textToParagraph(row.getCell(0).getParagraphs().get(0), " №\n" + "п/п",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Марка\n" + "автомобиля",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Гос. номер\n" + "автомобиля",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "№\n" + "ОТС",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Цели и задачи поездки",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "В чьё распоряжение",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Маршрут движения",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Время выхода",
                "Times New Roman", fontSize, isBold, parAligCenter);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Фамилия \n" + "водителя",
                "Times New Roman", fontSize, isBold, parAligCenter);

        //row.getCell(0).setWidth("5");
        System.out.println("table widthtype " + table.getWidthType());
        System.out.println("table width " + table.getWidth());

        CTHMerge hMergeRestart = CTHMerge.Factory.newInstance();
        hMergeRestart.setVal(STMerge.RESTART);
        CTHMerge hMergeContinue = CTHMerge.Factory.newInstance();
        hMergeContinue.setVal(STMerge.CONTINUE);
        
        
        List<Appointment> appointments = dispatcherService.getAppointmentsForPlan(AppointmentStatus.READY, tomorrow);

        //TODO: оценить возможность формирования сущности запросом, или хоть разобраться с сортировкой средствами БД/JPA
        List<RowData> rowDataList = new LinkedList<>();
        for (Appointment a : appointments) {
            RowData rowData = new RowData();
            Record record = dispatcherService.findRecordByAppointment(a);
            Claim claim = dispatcherService.findClaimByRecord(record);

            rowData.carBoss = claim.getCarBoss().getFirstname();
            rowData.driver = getDriverNameWithInitials(a.getDriver());
            rowData.departmentName = claim.getDepartment().getFullname();
            rowData.purposes = claim.getPurpose();
            rowData.vehicleModelName = a.getVehicleModel().getModelName();
            rowData.vehicleNumber = a.getVehicle().getNumber();            

            String[] tmpArr = a.getTransportDep().getShortname().split(" ");
            rowData.transportDepNumber = tmpArr[tmpArr.length - 1];

            String route = "";
            Set<RouteTask> routeTasks = claim.getRouteTasks();
            if (!routeTasks.isEmpty()) {
                List<RouteTask> routeTaskList = routeTasks.stream().sorted((t1, t2) -> {
                    return Integer.compare(t1.getOrderNum(), t2.getOrderNum());
                }).collect(Collectors.toList());
                for (RouteTask routeTask : routeTaskList) {
                    route = route + routeTask.getPlace().getName() + ", ";
                }
                route = route.substring(0, route.length() - 2);
            }
            rowData.route = route;

            rowData.time = record.getStartDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                    + "-"
                    + record.getEndDate().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

            rowDataList.add(rowData);
        }

        //-----------тестовые данные для проверки отображения
        if (appointments.isEmpty()) {
            RowData rd = new RowData();
            rd.carBoss = "Старший машины С.М.";
            rd.departmentName = "ЦИ-1";
            rd.driver = "Водитель В.В.";
            rd.purposes = "Перевозка пассажиров";
            rd.route = "Пл.10, Пл. 95";
            rd.time = "8:00-19:00";
            rd.transportDepNumber = "4";
            rd.vehicleModelName = "Газ 2121";
            rd.vehicleNumber = "Е777КХ";

            rowDataList.add(rd);

            rd = new RowData();
            rd.carBoss = "Старший машины С.М.";
            rd.departmentName = "ЦИ-1";
            rd.driver = "Водитель В.В.";
            rd.purposes = "Перевозка грузов";
            rd.route = "Пл.10, Пл. 95";
            rd.time = "8:00-19:00";
            rd.transportDepNumber = "4";
            rd.vehicleModelName = "Газ 2121";
            rd.vehicleNumber = "Е777КХ";

            rowDataList.add(rd);

            rd = new RowData();
            rd.carBoss = "Старший машины С.М.";
            rd.departmentName = "ЦИ-2";
            rd.driver = "Водитель В.В.";
            rd.purposes = "Нужно поездить туда-сюда";
            rd.route = "Пл.10, Пл. 95";
            rd.time = "8:00-19:00";
            rd.transportDepNumber = "4";
            rd.vehicleModelName = "Газ 2121";
            rd.vehicleNumber = "Е777КХ";

            rowDataList.add(rd);

            rd = new RowData();
            rd.carBoss = "Старший машины С.М.";
            rd.departmentName = "ЦИ-2";
            rd.driver = "Водитель В.В.";
            rd.purposes = "Нужно поездить туда-сюда";
            rd.route = "Пл.10, Пл. 95";
            rd.time = "8:00-19:00";
            rd.transportDepNumber = "4";
            rd.vehicleModelName = "Газ 2121";
            rd.vehicleNumber = "Е777КХ";

            rowDataList.add(rd);

        }//end-----------тестовые данные для проверки
        rowDataList.sort((r1, r2) -> {
            return r1.departmentName.compareTo(r2.departmentName); //TODO: написать специальную сортировку, чтобы сначала те, что должны быть первыми, потом алфавитный
        });

        String currentDepName = "";
        int number = 1;
        for (RowData rowData : rowDataList) {
            if (!currentDepName.equalsIgnoreCase(rowData.departmentName)) {
                currentDepName = rowData.departmentName.toUpperCase();
                XWPFTableRow mergingRow = table.createRow();
                int index = mergingRow.getTableCells().size() - 1;

                mergingRow.getCell(0).getCTTc().addNewTcPr().setHMerge(hMergeRestart);
                mergingRow.getCell(index).getCTTc().addNewTcPr().setHMerge(hMergeContinue);
                textToParagraph(mergingRow.getCell(0).getParagraphs().get(0),
                        currentDepName, "Times New Roman", 8, true, ParagraphAlignment.CENTER);
            };

            row = table.createRow();
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
            textToParagraph(row.getCell(7).getParagraphs().get(0), rowData.time,
                    "Times New Roman", fontSize, isBold, parAligCenter);
            textToParagraph(row.getCell(8).getParagraphs().get(0), rowData.driver,
                    "Times New Roman", fontSize, false, parAligLeft);
        }

        document.write(fileOutputStream);
        document.close();

        System.out.println(" written successully");

        //-------------- отправка файла
        MediaTypeUtils mediaTypeUtils = new MediaTypeUtils();
        String fileName = "plan" + date.toLocalDate().toString() + ".docx";
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

    private class RowData {

        String departmentName;
        String vehicleModelName;
        String vehicleNumber;
        String transportDepNumber;
        String purposes;
        String carBoss;
        String route;
        String time;
        String driver;
    }

    private void textToParagraph(XWPFParagraph paragraph, String text,
            String fontFamily, int fontSize, boolean isBold,
            ParagraphAlignment paragraphAlignment) {
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(paragraphAlignment);
        run.setFontFamily(fontFamily);
        run.setFontSize(fontSize);
        run.setBold(isBold);
        run.setText(text);
    }

    private void textToParagraph(XWPFParagraph paragraph, String text,
            int fontSize, ParagraphAlignment paragraphAlignment) {
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(paragraphAlignment);
        run.setFontFamily("Times New Roman");
        run.setFontSize(fontSize);
        run.setText(text);
    }

}
