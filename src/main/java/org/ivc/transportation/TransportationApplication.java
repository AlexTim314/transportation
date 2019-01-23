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

    @PostConstruct
    @Transactional
    public void init() {
        //
    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
