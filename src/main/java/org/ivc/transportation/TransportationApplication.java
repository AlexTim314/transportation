package org.ivc.transportation;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.sql.Time;
import java.time.LocalTime;
import javax.annotation.PostConstruct;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.config.trUtils;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.RecordStatus;

import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.repositories.RoleRepository;
import org.ivc.transportation.repositories.UserRepository;

import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.DepartmentService;
import org.ivc.transportation.services.PlanService;
import org.ivc.transportation.services.TransportDepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableTransactionManagement
public class TransportationApplication {

    private static final String PASSWORD = "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu";
//    @Autowired
//    private TransportDepRepository transportDepRepository;
//    @Autowired
//    private DriverRepository driverRepository;
//    @Autowired
//    private DepartmentRepository departmentRepository;
//    @Autowired
//    private VechicleRepository vclRepository;
//    @Autowired
//    private ClaimRepository claimRepository;
    @Autowired
    private TransportDepService tdS;

    /*
    @Autowired
    private DriverService drvS;
*/
    @Autowired
    private DepartmentService depS;
/*
    @Autowired
    private VechicleService veclS;
*/
    @Autowired
    private ClaimService clS;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PlanService plS;

    @PostConstruct
    @Transactional
    public void init() {
        AppRole adminRole = new AppRole("ROLE_ADMIN");
        AppRole userRole = new AppRole("ROLE_USER");

        AppUser admin = new AppUser("admin", PASSWORD, true, new HashSet<>(Arrays.asList(adminRole)));
        AppUser user = new AppUser("user", PASSWORD, true, new HashSet<>(Arrays.asList(userRole)));
        userRepository.saveAndFlush(admin);
        userRepository.saveAndFlush(user);

        TransportDep transportDep1 = new TransportDep("TransportDep1", "dAdr1", "dphone1");
        TransportDep transportDep2 = new TransportDep("TransportDep2", "dAdr2", "dphone2");
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
        depS.saveDepartment(dep1);
        depS.saveDepartment(dep2);
        depS.saveDepartment(dep3);
        depS.saveDepartment(dep4);
        depS.saveDepartment(dep5);

        
        Claim cl1 = new Claim(Date.valueOf("2018-10-20"), ClaimType.claim_type_weekly, dep1);
        cl1.setAffirmation(Boolean.FALSE);
        Claim cl2 = new Claim(Date.valueOf("2018-10-20"), ClaimType.claim_type_weekly, dep2);
        cl2.setAffirmation(Boolean.TRUE);
        Claim cl3 = new Claim(Date.valueOf("2018-10-20"), ClaimType.claim_type_spec, dep1);
        cl3.setAffirmation(Boolean.TRUE);
        Claim cl4 = new Claim(Date.valueOf("2018-10-24"), ClaimType.claim_type_car, dep2);
        cl4.setAffirmation(Boolean.FALSE);
        Claim cl5 = new Claim(Date.valueOf("2018-10-24"), ClaimType.claim_type_urgent, dep1);
        cl5.setAffirmation(Boolean.FALSE);
        clS.addClaim(cl1);
        clS.addClaim(cl2);
        clS.addClaim(cl3);
        clS.addClaim(cl4);
        clS.addClaim(cl5);

        String[] hash = {"g54drg546s", "g54drg546s", "g54drg546s", "s6d54g6s846h5", "s6d54g6s846h5"};
        
        Record rec1 = new Record(hash[0], Date.valueOf("2018-10-20"), Date.valueOf("2018-10-20"), Date.valueOf("2018-10-25"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "маршрут1", "Сервисное поле", "шаблон1", "Старший машины 1", cl1);
        rec1.setStatus(RecordStatus.record_status_created);
        Record rec2 = new Record(hash[1], Date.valueOf("2018-10-20"), Date.valueOf("2018-10-20"), Date.valueOf("2018-10-25"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "маршрут2", "Сервисное поле", "шаблон2", "Старший машины 2", cl1);
        rec2.setStatus(RecordStatus.record_status_inprogress);
        Record rec3 = new Record(hash[2], Date.valueOf("2018-10-20"), Date.valueOf("2018-10-20"), Date.valueOf("2018-10-25"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "маршрут3", "Сервисное поле", "шаблон3", "Старший машины 3", cl1);
        rec3.setStatus(RecordStatus.record_status_created);
        Record rec4 = new Record(hash[3], Date.valueOf("2018-10-24"), Date.valueOf("2018-10-24"), Date.valueOf("2018-10-29"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "маршрут4", "Сервисное поле", "шаблон4", "Старший машины 4", cl2);
        rec4.setStatus(RecordStatus.record_status_inprogress);
        Record rec5 = new Record(hash[4], Date.valueOf("2018-10-24"), Date.valueOf("2018-10-24"), Date.valueOf("2018-10-29"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "маршрут5", "Сервисное поле", "шаблон5", "Старший машины 5", cl2);
        rec5.setStatus(RecordStatus.record_status_completed);
        clS.addRecord(rec1);
        clS.addRecord(rec2);
        clS.addRecord(rec3);
        clS.addRecord(rec4);
        clS.addRecord(rec5);

        
        
        
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
        System.out.println("-----------by tip car order date desc-----------------");
        clS.getClaimsByTip(ClaimType.claim_type_car).forEach(System.out::println);
        System.out.println("-----------by tip weekly order date asc-----------------");
        clS.getClaimsByTipAsc(ClaimType.claim_type_weekly).forEach(System.out::println);
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
        System.out.println("-----#######################################-----------------------");
        System.out.println("-----------Record table-----------------");
        clS.getRecords().forEach(System.out::println);
        System.out.println("-----------Record by Date-----------------");
        clS.getRecordsByDate(Date.valueOf("2018-10-20")).forEach(System.out::println);
        System.out.println("-----------Record by Hash s6d54g6s846h5-----------------");
        clS.getRecordsByHash("s6d54g6s846h5").forEach(System.out::println);
        System.out.println("-----------Record by record_status_inprogress-----------------");
        clS.getRecordsByState(RecordStatus.record_status_inprogress).forEach(System.out::println);
        System.out.println("-----------Record by Claim 2-----------------");
        clS.getRecordsByClaim(cl2.getId()).forEach(System.out::println);
        System.out.println("-----------###########################-----------------");
         System.out.println("-----------all claims-----------------");
        clS.getAllClaimsSortByDate().forEach(System.out::println);
        System.out.println("-----------Record table-----------------");
        clS.getRecords().forEach(System.out::println);
        System.out.println("-----------Deleting claim 1-----------------");
        clS.removeClaim(cl1.getId());
        System.out.println("-----------Claims after deleting-----------------");
        clS.getAllClaimsSortByDate().forEach(System.out::println);
        System.out.println("-----------Record after-----------------");
        clS.getRecords().forEach(System.out::println);
        
        
    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
