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
import org.ivc.transportation.services.CommonService;
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

    @Autowired
    private CommonService commonService;

    @PostConstruct
    @Transactional
    public void init() {
        AppRole adminRole = new AppRole("ROLE_ADMIN");
        AppRole userRole = new AppRole("ROLE_USER");

        TransportDep transportDep1 = new TransportDep("Транспортный отдел №4", "ул. Лейтенанта Шмидта, 1", "18-275");
        TransportDep transportDep2 = new TransportDep("Транспортный отдел №5", "пл.9", "16-386");

        transportDepRepository.save(transportDep1);
        transportDepRepository.save(transportDep2);

        Department dep1 = commonService.findDepartmentByName("ЦИП ИК");
        Department dep2 = commonService.findDepartmentByName("Отдел организационного развития");

        AppUser admin = new AppUser("admin", "Тимошенко Александр Александрович", PASSWORD, true, dep1, transportDep1, new HashSet<>(Arrays.asList(adminRole)));
        AppUser user = new AppUser("user", "Соколов Вячеслав Владимирович", PASSWORD, true, dep2, transportDep2, new HashSet<>(Arrays.asList(userRole)));

        userRepository.save(admin);
        userRepository.save(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
