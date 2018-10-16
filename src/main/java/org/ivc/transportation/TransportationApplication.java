package org.ivc.transportation;

import java.sql.Date;
import java.util.stream.Stream;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransportationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);
    }
long[] trdepId; 
    @Bean
    ApplicationRunner init_dep(DepartmentRepository dpartmentRepository) {
        return (ApplicationArguments args) -> {

            String[] name = {"ЦИ-1", "ЦИ-2", "ЦИ-4", "ЦИ-ТК", "ЦИ-7",
                "КЭССТ", "КЭЗС", "КАТО", "СЛУЖБА БЕЗОПАСНОСТИ"};
            String[] addres = {"Площадка №1", "Площадка №2", "Титова 7", "Нет адреса", "Площадка №254",
                "Советской армии 5", "В степи по дороге", "Советской армии 7", "Везде"};

            for (int i = 0; i < name.length; i++) {
                Department dep = new Department();
                dep.setName(name[i]);
                dep.setAddres(addres[i]);
                dpartmentRepository.save(dep);
            }

           // dpartmentRepository.findAll().forEach(System.out::println);
        };
    }

    @Bean
    ApplicationRunner init_td(TransportDepRepository transpDepRepository) {
        return (ApplicationArguments args) -> {

            int[] nom = {1, 2, 3, 4, 5, 6, 7, 8};
            String[] phone = {"13-256", "14-586", "11-111", "83362245689", "10-014",
                "18-523", "12-001", "12-345"};
            String[] addres = {"Абая 3", "Площадка №2", "Советской армии 3", "Нет адреса", "Королева 36",
                "Советской армии 7", "Неизвестно", "7 мкр"};
            trdepId = new long[nom.length];
            for (int i = 0; i < nom.length; i++) {
                TransportDep td = new TransportDep();
                td.setNumDep(nom[i]);
                td.setAddres(addres[i]);
                td.setPhone(phone[i]);
                transpDepRepository.save(td);
                trdepId[i] = td.getId();
            }

            //transpDepRepository.findAll().forEach(System.out::println);
        };
    }
    
    @Bean
    ApplicationRunner init_dr(DriverRepository drvRepository) {
        return (ApplicationArguments args) -> {

            String[] firstName = {"Иванов", "Сидоров", "Петров"};
            String[] name = {"Александр", "Илья", "Иван"};
            String[] sureName ={"Владимирович", "Петрович", "Юрьевич"};
            Date[] dateBirth = {Date.valueOf("1985-01-15"),Date.valueOf("1990-08-13"),Date.valueOf("1972-10-11")};
            String[] phone = {"87771234569", "87712564785", "87051234568"};
            String[] addres = {"Абая 12-25", "Максимова 20-10", "6 мкр 15-48"};
            String[] vac = {"Не занят", "Занят", "Не занят"};
            

            for (int i = 0; i < name.length; i++) {
                Driver dr = new Driver();
                System.out.println("id trdepId: ");
                System.out.println(trdepId[i]);
                TransportDep asd = new TransportDep();
                asd.setId(trdepId[i]);
                dr.setTransportDep(asd);
                dr.setFirstname(firstName[i]);
                dr.setName(name[i]);
                dr.setSurname(sureName[i]);
                dr.setBirthday(dateBirth[i]);
                dr.setAddres(addres[i]);
                dr.setPhone(phone[i]);
                dr.setVacant(vac[i]);
                drvRepository.save(dr);
            }

       //    drvRepository.findAll().forEach((d) -> {
        //       System.out.println(d.getTransportDep().getId() + " " +  d.getTransportDep().getAddres());
              //System.out.println(d.getTransportDep().getDrivers().toString());
        //   });
           // System.out.println(drvRepository.findAll().get(0).getName());
            //drvRepository.findAll();
            
        };
        
    }

}
