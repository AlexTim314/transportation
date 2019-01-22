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
import java.time.LocalDate;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.ivc.transportation.services.CommonService;
import org.ivc.transportation.utils.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author alextim
 */
@Controller
public class PlanDownloadController {
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private ServletContext servletContext;
    
    @GetMapping("/plandownload")
    public void download(HttpServletResponse response) throws FileNotFoundException, IOException {
        
        System.out.println("plan.docx write");

        //Blank Document
        XWPFDocument document = new XWPFDocument();
        
        String fileName = "plan" + LocalDate.now().toString() + ".docx";
        File file = File.createTempFile("plan", LocalDate.now().toString());
        //Write the Document in file system
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        //create table
        XWPFTable table = document.createTable();
        //table.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");

        //create first row
        XWPFTableRow row = table.getRow(0);        
        XWPFTableCell cell = row.addNewTableCell();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontFamily("Times New Roman");
        run.setFontSize(12);
        run.setText("УТВЕРЖДАЮ1");        
        cell.addParagraph(paragraph);
        //cell.removeParagraph(0);
        //cell.setText("УТВЕРЖДАЮ");
        //cell.setText("Начальник Комплекса АТО");

        /* setText("УТВЕРЖДАЮ\n" +
"        Начальник Комплекса АТО\n" +
"\n" +
"                                                                          К.К.Мавлютов\n" +
" « 12 »  сентября 2018 г.");
         */
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontFamily("Times New Roman");
        run.setFontSize(12);
        run.setBold(true);
        run.setText("План");
        
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run.setFontFamily("Times New Roman");
        run.setFontSize(12);
        run.setBold(true);
        
        run.setText("выхода автомобилей "
                + "Комплекса автотранспортного обеспечения на " + LocalDate.now().plusDays(1).toString());
        
        table = document.createTable();        
        row = table.getRow(0);        
        row.getCell(0).setText(" №\n" + "п/п");
        row.addNewTableCell().setText("Марка\n" + "автомобиля");
        row.addNewTableCell().setText("Гос. номер\n" + "автомобиля");
        row.addNewTableCell().setText("№\n" + "ОТС");
        row.addNewTableCell().setText("Цели и задачи поездки");
        row.addNewTableCell().setText("В чье распоряжение");
        row.addNewTableCell().setText("Маршрут движения");
        row.addNewTableCell().setText("Время выхода");
        row.addNewTableCell().setText("Фамилия \n" + "водителя");
        
        row = table.createRow();
        row.addNewTableCell().setText("Text in new cell");
        
        
        
        
        
        
        
        
        document.write(fileOutputStream);
        document.close();

//fileOutputStream.close();
        System.out.println("fileName written successully");
        
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
                    
                    System.out.println("Waybill downloading..(" + bytesRead + "bytes)");
                    outStream.write(buffer, 0, bytesRead);
                }
                
                outStream.flush();
            }
            
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        
    }
    
}
