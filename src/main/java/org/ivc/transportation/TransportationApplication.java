package org.ivc.transportation;

import java.util.stream.Stream;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.DepartmentRepository;
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

    @Bean
    ApplicationRunner init(DepartmentRepository dpartmentRepository) {
        return (ApplicationArguments args) -> {
            Stream.of("ЦИ-1", "ЦИ-2", "ЦИ-4", "ЦИ-ТК", "ЦИ-7",
                    "КЭССТ", "КЭЗС", "КТМО", "СЛУЖБА БЕЗОПАСНОСТИ").forEach(name -> {
                        Department dep = new Department();
                        dep.setName(name);                     
                        dpartmentRepository.save(dep);
                    });
            dpartmentRepository.findAll().forEach(System.out::println);
        };
    }

}
