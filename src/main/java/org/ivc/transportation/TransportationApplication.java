package org.ivc.transportation;

import java.sql.Date;
import java.util.Arrays;
import javax.annotation.PostConstruct;

import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.repositories.ClaimRepository;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.ivc.transportation.repositories.DriverRepository;
import org.ivc.transportation.repositories.TransportDepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
public class TransportationApplication {

    @Autowired
    private TransportDepRepository transportDepRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentRepository dpartmentRepository;
    @Autowired
    private DriverRepository drvRepository;
    @Autowired
    private TransportDepRepository transpDepRepository;
    @Autowired
    private ClaimRepository claimRepository;


    @PostConstruct
    @Transactional
    public void init() {
        TransportDep transportDep1 = new TransportDep(1, "dAdr1", "dphone1");
        TransportDep transportDep2 = new TransportDep(2, "dAdr2", "dphone2");
        transportDepRepository.saveAll(Arrays.asList(transportDep1, transportDep2));
        transportDepRepository.flush();
        Driver driver1 = new Driver("fname1", "name1", "sname1", new Date(0), "address1", "phone1", "vacant1", transportDep1);
        Driver driver2 = new Driver("fname2", "name2", "sname2", new Date(0), "address2", "phone2", "vacant2", transportDep2);
        Driver driver3 = new Driver("fname3", "name3", "sname3", new Date(0), "address3", "phone3", "vacant3", transportDep1);
        Driver driver4 = new Driver("fname4", "name4", "sname4", new Date(0), "address4", "phone4", "vacant4", transportDep2);
        Driver driver5 = new Driver("fname5", "name5", "sname5", new Date(0), "address5", "phone5", "vacant5", transportDep1);
        driverRepository.saveAll(Arrays.asList(driver1, driver2, driver3, driver4, driver5));
        driverRepository.flush();
        Department dep1 = new Department("NAME-1", "ADDRES-1");
        Department dep2 = new Department("NAME-2", "ADDRES-2");
        Department dep3 = new Department("NAME-3", "ADDRES-3");
        Department dep4 = new Department("NAME-4", "ADDRES-4");
        Department dep5 = new Department("NAME-5", "ADDRES-5");
        departmentRepository.saveAll(Arrays.asList(dep1, dep2, dep3, dep4, dep5));
        departmentRepository.flush();
        Byte[] q = {0};
        Claim cl1 = new Claim(new Date(0), q[0], dep1);
        Claim cl2 = new Claim(new Date(0), q[0], dep2);
        Claim cl3 = new Claim(new Date(0), q[0], dep1);
        Claim cl4 = new Claim(new Date(0), q[0], dep2);
        Claim cl5 = new Claim(new Date(0), q[0], dep1);
        claimRepository.saveAll(Arrays.asList(cl1, cl2, cl3, cl4, cl5));
        claimRepository.flush();
    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
