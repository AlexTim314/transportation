/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.ivc.transportation.config.trUtils;
import org.ivc.transportation.config.trUtils.AppointmentStatus;
import static org.ivc.transportation.config.trUtils.NamedCell.серия;

/**
 *
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"record", "vechicle", "driver"})
@ToString(exclude = {"record", "vechicle", "driver"})
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @NonNull
    @Column(nullable = false)
    private AppointmentStatus status;

    @NonNull
    @Column(nullable = false)
    private String note;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Record record;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Vechicle vechicle;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    private Waybill waybill;

    public Appointment(LocalDateTime dateTime, AppointmentStatus status, String note, Record record, Driver driver, Vechicle vechicle) {
        this.dateTime = dateTime;
        this.status = status;
        this.note = note;
        this.driver = driver;
        this.record = record;
        this.vechicle = vechicle;
    }


/*
    public void excel2pdf() {

        FileInputStream input_document = null;
        try {
            input_document = new FileInputStream(new File("Waybill_new.xls"));
            // Read workbook into HSSFWorkbook
            HSSFWorkbook my_xls_workbook = new HSSFWorkbook(input_document);
            // Read worksheet into HSSFSheet
            HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0);
            // To iterate over the rows
            Iterator<Row> rowIterator = my_worksheet.iterator();
            //We will create output PDF document objects at this point
            Document iText_xls_2_pdf = new Document();
            PdfWriter.getInstance(iText_xls_2_pdf, new FileOutputStream("Waybill2PDF.pdf"));
            iText_xls_2_pdf.open();
            //we have two columns in the Excel sheet, so we create a PDF table with two columns
            //Note: There are ways to make this dynamic in nature, if you want to.
            PdfPTable my_table = new PdfPTable(my_worksheet.getRow(0).getLastCellNum());
            //We will use the object below to dynamically add new data to the table
            PdfPCell table_cell;
            //Loop through rows.
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next(); //Fetch CELL
                    switch (cell.getCellType()) { //Identify CELL type
                        //you need to add more code here based on
                        //your requirement / transformations
                        case STRING:
                            //Push the data from Excel to PDF Cell
                            table_cell = new PdfPCell(new Phrase(cell.getStringCellValue()));
                            //feel free to move the code below to suit to your needs
                            my_table.addCell(table_cell);
                            break;
                        case NUMERIC:
                            table_cell = new PdfPCell(new Phrase(
                                    String.valueOf(cell.getNumericCellValue())
                            ));
                            my_table.addCell(table_cell);
                    }
                    //next line
                }

            }
            //Finally add the table to PDF document
            iText_xls_2_pdf.add(my_table);
            iText_xls_2_pdf.close();
            //we created our pdf file..
            input_document.close(); //close xls
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }*/

}
