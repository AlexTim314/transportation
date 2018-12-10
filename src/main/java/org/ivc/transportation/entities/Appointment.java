/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
    
    
    public void createWaybill() {
        String excelFilePath = "Waybill.xls";
        
        

        try {
            Workbook workbook;
            try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
                workbook = WorkbookFactory.create(inputStream);
                List<? extends Name> allNames = workbook.getAllNames();
                List<trUtils.NamedCell> expectedNamedCells = Arrays.stream(trUtils.NamedCell.values()).collect(Collectors.toList());

                for (Name name : allNames) {
                    String cellName = name.getNameName().trim().toLowerCase().replace(" ", "_");
                    try {
                        trUtils.NamedCell namedCell = trUtils.NamedCell.valueOf(cellName);
                        Cell c = getCell(workbook, name);
                        switch (namedCell) {
                            case серия:
                                c.setCellValue(waybill.getSeries());
                                break;
                            case номер:
                                c.setCellValue(waybill.getNumber());
                                break;
                            case число:
                                c.setCellValue(this.dateTime.getDayOfMonth());
                                break;
                            case месяц:
                                c.setCellValue(this.dateTime.getMonth().getDisplayName(TextStyle.FULL, new Locale("ru")));
                                break;
                            case год:
                                c.setCellValue(this.dateTime.getYear());
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
                                c.setCellValue("диспетчер Пока не поддерживается. из принципал?");
                                break;
                            case механик:
                                c.setCellValue(" механик Пока не поддерживается. Брать с формы?");
                                break;
                            case время_выезда_по_графику:
                                c.setCellValue(this.record.getDepartureTime().toLocalTime().toString());
                                break;
                            case время_возвращения_по_графику:
                                c.setCellValue(this.record.getReturnTime().toLocalTime().toString());
                                break;
                            case показание_спидометра_при_выезде:
                                c.setCellValue(this.vechicle.getOdometr());
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
                                c.setCellValue(this.vechicle.getFuelRemnant());
                                break;
                            case заказчик:
                                c.setCellValue(" заказчик Пока не поддерживается. Брать с формы?");
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

            try (FileOutputStream outputStream = new FileOutputStream("Waybill_new.xls")) {
                workbook.write(outputStream);
                workbook.close();
            }

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
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
