package org.ivc.transportation;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@SpringBootApplication
@EnableTransactionManagement
public class TransportationApplication {

    public static final String SAMPLE_XLSX_FILE_PATH = "./asd.xlsx";

    @PostConstruct
    @Transactional
    public void init() throws IOException {
//        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
//        Sheet sheet = workbook.getSheetAt(0);
//        Cell cell;
//        int iRow = 3;
//        int iColl = 2;
//        int trId = 1;
//        String model, number;
//        for (int i = 0; i < 7; i++) {
//            boolean f = true;
//        do {
//            cell = sheet.getRow(iRow).getCell(iColl);
//            model = cell.getStringCellValue();
//            cell = sheet.getRow(iRow).getCell(iColl + 1);
//            number = cell.getStringCellValue();
//            System.out.println("INSERT INTO public.vehicle(fuel, motohours, note, number, odometr, status, model_id, transport_dep_id)");
//            System.out.println("VALUES (111, 333, null, '"+number+"', 222, 0, (select id from Vehicle_Model where model_name='"+model+"' limit 1), "+trId+");");
//            System.out.println("insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 1);");
//            System.out.println("insert into vehicle_fuel(vehicle_id, fuel_id) values(currval('vehicle_id_seq'), 2);");
//            iRow++;
//            if (sheet.getRow(iRow) != null)
//            cell = sheet.getRow(iRow).getCell(iColl-2);
//            if (iRow > 399 || cell.getCellType() != CellType.NUMERIC) f = false;
//        } while (f);
//        trId++;
//        iRow++;
//        }
//        workbook.close();
    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
