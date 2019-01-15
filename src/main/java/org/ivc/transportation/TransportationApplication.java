package org.ivc.transportation;

import java.util.Arrays;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.RoleRepository;
import org.ivc.transportation.repositories.TransportDepRepository;
import org.ivc.transportation.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
public class TransportationApplication {

    private static final String PASSWORD = "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TransportDepRepository transportDepRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    @Transactional
    public void init() {
        AppRole adminRole = new AppRole("ROLE_ADMIN");
        AppRole userRole = new AppRole("ROLE_USER");

        TransportDep transportDep1 = new TransportDep("Транспортный отдел №4", "ул. Лейтенанта Шмидта, 1", "18-275");
        TransportDep transportDep2 = new TransportDep("Транспортный отдел №5", "пл.9", "16-386");
        TransportDep transportDep3 = new TransportDep("Транспортный отдел №1", "пл.10", "17-345");
        TransportDep transportDep4 = new TransportDep("Транспортный отдел №2", "пл.2", "15-646");

        transportDepRepository.save(transportDep1);
        transportDepRepository.save(transportDep2);
        transportDepRepository.save(transportDep3);
        transportDepRepository.save(transportDep4);

        Department dep1 = new Department("ЦИ-4", "ул. Титова, 7");
        Department dep2 = new Department("КАТО", "ул. Лейтенанта Шмидта, 3");
        Department dep3 = new Department("ЦИ-2", "пл. 200");
        Department dep4 = new Department("ЦИ-1", "пл. 31"); 
        Department dep5 = new Department("КЭССТ", "ул. Лейтенанта Шмидта, 5");
        Department dep6 = new Department("ЦИ-7", "ул. Авиационная, 7");
        dep1.setShortname("ЦИ-4");
        dep2.setShortname("КАТО");
        dep3.setShortname("ЦИ-2");
        dep4.setShortname("ЦИ-1");
        dep5.setShortname("КЭССТ");
        dep6.setShortname("ЦИ-7");

        departmentRepository.save(dep1);
        departmentRepository.save(dep2);
        departmentRepository.save(dep3);
        departmentRepository.save(dep4);
        departmentRepository.save(dep5);
        departmentRepository.save(dep6);

        AppUser admin = new AppUser("admin", "Тимошенко Александр Александрович", PASSWORD, true, dep1, transportDep1, new HashSet<>(Arrays.asList(adminRole)));
        AppUser user = new AppUser("user", "Соколов Вячеслав Владимирович", PASSWORD, true, dep2, transportDep2, new HashSet<>(Arrays.asList(userRole)));

        userRepository.save(admin);
        userRepository.save(user);

    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
