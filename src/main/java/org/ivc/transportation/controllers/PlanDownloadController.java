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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
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

        XWPFDocument document = new XWPFDocument();
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

        File file = File.createTempFile("plan", LocalDate.now().toString());
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        //------------------ Шапка
        XWPFTable table = document.createTable();
        table.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFF00");

        XWPFTableRow row = table.getRow(0);
        XWPFTableCell cell = row.addNewTableCell();
        cell.setWidth("30");
        //XWPFParagraph paragraph = ;//document.createParagraph();

        textToParagraph(cell.getParagraphs().get(0), "УТВЕРЖДАЮ", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "Начальник Комплекса АТО", 12, ParagraphAlignment.CENTER);
        textToParagraph(cell.addParagraph(), "К.К.Мавлютов", 12, ParagraphAlignment.RIGHT);
        textToParagraph(cell.addParagraph(), LocalDate.now().toString(), 12, ParagraphAlignment.LEFT);

        //END------------------ Шапка
        
        //---------------- Заполнение файла
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setSpacingBefore(200);
        textToParagraph(paragraph, "ПЛАН", "Times New Roman", 12, true, ParagraphAlignment.CENTER);
        //TODO: Дата вычисляется прибавкой 1го дня, что не правильно для пятницы и для других назначений не на завтра
        textToParagraph(document.createParagraph(), "выхода автомобилей Комплекса автотранспортного обеспечения на "
                + LocalDate.now().plusDays(1).toString(), "Times New Roman", 12, true, ParagraphAlignment.CENTER);

        table = document.createTable();
        row = table.getRow(0);
        boolean isBold = true;
        int fontSize = 8;
        ParagraphAlignment parAlig = ParagraphAlignment.CENTER;
        textToParagraph(row.getCell(0).getParagraphs().get(0), " №\n" + "п/п"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Марка\n" + "автомобиля"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Гос. номер\n" + "автомобиля"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "№\n" + "ОТС"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Цели и задачи поездки"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "В чьё распоряжение"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Маршрут движения"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Время выхода"
                , "Times New Roman", fontSize, isBold, parAlig);
        textToParagraph(row.addNewTableCell().getParagraphs().get(0), "Фамилия \n" + "водителя"
                , "Times New Roman", fontSize, isBold, parAlig);

        CTHMerge hMergeRestart = CTHMerge.Factory.newInstance();
        hMergeRestart.setVal(STMerge.RESTART);
        CTHMerge hMergeContinue = CTHMerge.Factory.newInstance();
        hMergeContinue.setVal(STMerge.CONTINUE);

        XWPFTableRow mergingRow = table.createRow();
        int index = mergingRow.getTableCells().size() - 1;

        mergingRow.getCell(0).getCTTc().addNewTcPr().setHMerge(hMergeRestart);
        mergingRow.getCell(index).getCTTc().addNewTcPr().setHMerge(hMergeContinue);
        mergingRow.getCell(0).setText("merged");
        
        row = table.createRow();
        row.getCell(4).setText("Text in new cell 4");

        document.write(fileOutputStream);
        document.close();

        System.out.println("fileName written successully");
        
        //-------------- отправка файла
        try {
            MediaTypeUtils mediaTypeUtils = new MediaTypeUtils();
            String fileName = "plan.docx";
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

    private void textToParagraph(XWPFParagraph paragraph, String text) {
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontFamily("Times New Roman");
    }

}
