package org.ivc.transportation;

import java.sql.Date;
import javax.annotation.PostConstruct;

import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vechicle;

import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.DepartmentService;
import org.ivc.transportation.services.TransportDepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
public class TransportationApplication {

    @Autowired
    private TransportDepService tdS;
    @Autowired
    private DepartmentService depS;
    @Autowired
    private ClaimService clS;

    @PostConstruct
    @Transactional
    public void init() {
        TransportDep transportDep1 = new TransportDep(1, "dAdr1", "dphone1");
        TransportDep transportDep2 = new TransportDep(2, "dAdr2", "dphone2");
        tdS.addTransportDep(transportDep1);
        tdS.addTransportDep(transportDep2);

        Driver driver1 = new Driver("fname1", "name1", "sname1", new Date(0), "address1", "phone1", "", transportDep1);
        driver1.setVacant(Boolean.TRUE);
        Driver driver2 = new Driver("fname2", "name2", "sname2", new Date(0), "address2", "phone2", "Заболел", transportDep2);
        driver2.setVacant(Boolean.FALSE);
        Driver driver3 = new Driver("fname3", "name3", "sname3", new Date(0), "address3", "phone3", "Не прошел утренний медосмотр", transportDep1);
        driver3.setVacant(Boolean.FALSE);
        Driver driver4 = new Driver("fname4", "name4", "sname4", new Date(0), "address4", "phone4", "", transportDep2);
        driver4.setVacant(Boolean.TRUE);
        Driver driver5 = new Driver("fname5", "name5", "sname5", new Date(0), "address5", "phone5", "", transportDep1);
        driver5.setVacant(Boolean.TRUE);
        tdS.addDriver(driver1);
        tdS.addDriver(driver2);
        tdS.addDriver(driver3);
        tdS.addDriver(driver4);
        tdS.addDriver(driver5);

        Vechicle vechicle1 = new Vechicle("123", 36.0, 1234.2, "", transportDep2);
        vechicle1.setVacant(Boolean.TRUE);
        Vechicle vechicle2 = new Vechicle("456", 45.8, 123544.5, "", transportDep1);
        vechicle2.setVacant(Boolean.TRUE);
        Vechicle vechicle3 = new Vechicle("521", 33.2, 453454.2, "На ремонте", transportDep2);
        vechicle3.setVacant(Boolean.FALSE);
        Vechicle vechicle4 = new Vechicle("054", 86.2, 154543.0, "", transportDep1);
        vechicle4.setVacant(Boolean.TRUE);
        Vechicle vechicle5 = new Vechicle("007", 56.7, 145774.8, "", transportDep2);
        vechicle5.setVacant(Boolean.TRUE);
        tdS.addVechicle(vechicle1);
        tdS.addVechicle(vechicle2);
        tdS.addVechicle(vechicle3);
        tdS.addVechicle(vechicle4);
        tdS.addVechicle(vechicle5);

        Department dep1 = new Department("NAME-1", "ADDRES-1");
        Department dep2 = new Department("NAME-2", "ADDRES-2");
        Department dep3 = new Department("NAME-3", "ADDRES-3");
        Department dep4 = new Department("NAME-4", "ADDRES-4");
        Department dep5 = new Department("NAME-5", "ADDRES-5");
        depS.addDepartment(dep1);
        depS.addDepartment(dep2);
        depS.addDepartment(dep3);
        depS.addDepartment(dep4);
        depS.addDepartment(dep5);

        Byte[] q = {0, 1};
        Claim cl1 = new Claim(Date.valueOf("2018-10-20"), q[0], dep1);
        cl1.setAffirmation(Boolean.FALSE);
        Claim cl2 = new Claim(Date.valueOf("2018-10-20"), q[1], dep2);
        cl2.setAffirmation(Boolean.TRUE);
        Claim cl3 = new Claim(Date.valueOf("2018-10-20"), q[0], dep1);
        cl3.setAffirmation(Boolean.TRUE);
        Claim cl4 = new Claim(Date.valueOf("2018-10-24"), q[1], dep2);
        cl4.setAffirmation(Boolean.FALSE);
        Claim cl5 = new Claim(Date.valueOf("2018-10-24"), q[0], dep1);
        cl5.setAffirmation(Boolean.FALSE);
        clS.addClaim(cl1);
        clS.addClaim(cl2);
        clS.addClaim(cl3);
        clS.addClaim(cl4);
        clS.addClaim(cl5);

        System.out.println("----------------------------");
        tdS.findDriversByVacant(Boolean.TRUE).forEach(System.out::println);
        System.out.println("----------------------------");
        tdS.findVechiclesByVacant(Boolean.TRUE).forEach(System.out::println);
        System.out.println("----------------------------");
        System.out.println(tdS.getDriversByTransportDepId(transportDep1.getId()));
        System.out.println("----------------------------");
        System.out.println(tdS.getDriversByTransportDepId(transportDep2.getId()));

        System.out.println("-----------Claims-----------------");
        System.out.println("-----------order by date asc-----------------");
        clS.getAllClaimsSortByDateAsk().forEach(System.out::println);
        System.out.println("-----------order by date desc-----------------");
        clS.getAllClaimsSortByDate().forEach(System.out::println);
        System.out.println("-----------by tip 1 order date desc-----------------");
        clS.getClaimsByTip(q[1]).forEach(System.out::println);
        System.out.println("-----------by tip 0 order date asc-----------------");
        clS.getClaimsByTipAsc(q[0]).forEach(System.out::println);
        System.out.println("-----------by affirmation true order date desc-----------------");
        clS.getClaimsByAffirmation(Boolean.TRUE).forEach(System.out::println);
        System.out.println("-----------by affirmation true order date asc-----------------");
        clS.getClaimsByAffirmationAsc(Boolean.TRUE).forEach(System.out::println);
        System.out.println("-----------by affirmation false order date asc-----------------");
        clS.getClaimsByAffirmationAsc(Boolean.FALSE).forEach(System.out::println);
        System.out.println("-----------by ByDep 1-----------------");
        clS.getClaimsByDepartment(dep1.getId()).forEach(System.out::println);
        System.out.println("-----------by ByDep 2-----------------");
        clS.getClaimsByDepartment(dep2.getId()).forEach(System.out::println);
        System.out.println("-----------by ByDep 1 Affirmation true order date desc-----------------");
        clS.getClaimsByDepAndAffirmation(dep1.getId(), Boolean.TRUE).forEach(System.out::println);
        System.out.println("-----------by ByDep 2 Affirmation true order date desc-----------------");
        clS.getClaimsByDepAndAffirmation(dep2.getId(), Boolean.TRUE).forEach(System.out::println);
        System.out.println("-----------by ByDep 1 Affirmation false order date desc-----------------");
        clS.getClaimsByDepAndAffirmation(dep1.getId(), Boolean.FALSE).forEach(System.out::println);
        System.out.println("-----------by ByDep 2 Affirmation false order date desc-----------------");
        clS.getClaimsByDepAndAffirmation(dep2.getId(), Boolean.FALSE).forEach(System.out::println);
        System.out.println("-----------by ByDep 1 Affirmation true order date asc-----------------");
        clS.getClaimsByDepAndAffirmationAsc(dep1.getId(), Boolean.TRUE).forEach(System.out::println);
        System.out.println("-----------by ByDep 2 Affirmation true order date asc-----------------");
        clS.getClaimsByDepAndAffirmationAsc(dep2.getId(), Boolean.TRUE).forEach(System.out::println);
        System.out.println("-----------by ByDep 1 Affirmation false order date asc-----------------");
        clS.getClaimsByDepAndAffirmationAsc(dep1.getId(), Boolean.FALSE).forEach(System.out::println);
        System.out.println("-----------by ByDep 2 Affirmation false order date asc-----------------");
        clS.getClaimsByDepAndAffirmationAsc(dep2.getId(), Boolean.FALSE).forEach(System.out::println);
        System.out.println("-----------by Date 2018-10-20-----------------");
        clS.getClaimsByclDate(Date.valueOf("2018-10-20")).forEach(System.out::println);

    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
