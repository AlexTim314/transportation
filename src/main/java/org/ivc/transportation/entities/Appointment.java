/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import org.ivc.transportation.config.trUtils.AppointmentStatus;

/**
 *
 * @author Nesterov Yuriy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"appointmentGroup", "vehicle", "driver", "transportDep", "vehicleModel"})
@ToString(exclude = {"appointmentGroup", "vehicle", "driver", "transportDep", "vehicleModel"})
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

    @Column(nullable = true)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    @OneToOne(fetch = FetchType.EAGER)
    private Waybill waybill;
    
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private TransportDep transportDep;
    
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    private VehicleModel vehicleModel;


    public Appointment(LocalDateTime dateTime, TransportDep transportDep, VehicleModel vehicleModel) {
        this.dateTime = dateTime;
        this.status = AppointmentStatus.appointment_status_created;
        this.transportDep = transportDep;
        this.vehicleModel = vehicleModel;
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
