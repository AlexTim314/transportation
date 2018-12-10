package org.ivc.transportation;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import org.ivc.transportation.config.trUtils.AppointmentStatus;
import org.ivc.transportation.entities.AppRole;
import org.ivc.transportation.entities.AppUser;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.RecordStatus;
import org.ivc.transportation.entities.Appointment;

import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Criterion;
import org.ivc.transportation.entities.CriterionType;
import org.ivc.transportation.entities.CriterionValue;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.TaskList;
import org.ivc.transportation.entities.TransportDep;
import org.ivc.transportation.entities.TypeVechicle;
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.entities.Waybill;
import org.ivc.transportation.repositories.AppointmentRepository;
import org.ivc.transportation.repositories.RoleRepository;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.AppointmentService;

import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.CriterionService;
import org.ivc.transportation.services.DepartmentService;
import org.ivc.transportation.services.PlanService;
import org.ivc.transportation.services.TransportDepService;
import org.ivc.transportation.services.WaybillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

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

    @Autowired
    private AppointmentRepository aprep;

    @Autowired
    private CriterionService critServ;

    @Autowired
    private AppointmentService appointmentServ;

    @Autowired
    private WaybillService waybillService;

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

        TypeVechicle typeVech1 = new TypeVechicle("Автобус", "Пассажирский");
        TypeVechicle typeVech2 = new TypeVechicle("Самосвал", "Грузовой");
        TypeVechicle typeVech3 = new TypeVechicle("Легковой", "Легковой");
        TypeVechicle typeVech4 = new TypeVechicle("Автокран", "Специальный");
        TypeVechicle typeVech5 = new TypeVechicle("Фура", "Грузовой");
        TypeVechicle typeVech6 = new TypeVechicle("Грузовик", "Грузовой");
        TypeVechicle typeVech7 = new TypeVechicle("АГП", "Специальный");
        TypeVechicle typeVech8 = new TypeVechicle("Микроавтобус", "Пассажирский");
        tdS.addTypeVechicle(typeVech1);
        tdS.addTypeVechicle(typeVech2);
        tdS.addTypeVechicle(typeVech3);
        tdS.addTypeVechicle(typeVech4);
        tdS.addTypeVechicle(typeVech5);
        tdS.addTypeVechicle(typeVech6);
        tdS.addTypeVechicle(typeVech7);
        tdS.addTypeVechicle(typeVech8);

        Vechicle vechicle1 = new Vechicle("123", 36.0, 1234.2, "", transportDep2, typeVech1);
        vechicle1.setVacant(Boolean.TRUE);
        Vechicle vechicle2 = new Vechicle("456", 45.8, 123544.5, "", transportDep1, typeVech2);
        vechicle2.setVacant(Boolean.TRUE);
        Vechicle vechicle3 = new Vechicle("521", 33.2, 453454.2, "На ремонте", transportDep2, typeVech8);
        vechicle3.setVacant(Boolean.FALSE);
        Vechicle vechicle4 = new Vechicle("054", 86.2, 154543.0, "", transportDep1, typeVech7);
        vechicle4.setVacant(Boolean.TRUE);
        Vechicle vechicle5 = new Vechicle("007", 56.7, 145774.8, "", transportDep2, typeVech3);
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

        Record rec1 = new Record(hash[0], Date.valueOf("2018-10-20"), Date.valueOf("2018-10-20"), Date.valueOf("2018-10-25"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "Пассажирский транспорт", "маршрут1", "Сервисное поле", "шаблон1", "Старший машины 1", cl1, null);
        rec1.setStatus(RecordStatus.record_status_created);
        Record rec2 = new Record(hash[1], Date.valueOf("2018-10-20"), Date.valueOf("2018-10-20"), Date.valueOf("2018-10-25"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "Пассажирский транспорт", "маршрут2", "Сервисное поле", "шаблон2", "Старший машины 2", cl1, null);
        rec2.setStatus(RecordStatus.record_status_inprogress);
        Record rec3 = new Record(hash[2], Date.valueOf("2018-10-20"), Date.valueOf("2018-10-20"), Date.valueOf("2018-10-25"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "Пассажирский транспорт", "маршрут3", "Сервисное поле", "шаблон3", "Старший машины 3", cl1, null);
        rec3.setStatus(RecordStatus.record_status_created);
        Record rec4 = new Record(hash[3], Date.valueOf("2018-10-24"), Date.valueOf("2018-10-24"), Date.valueOf("2018-10-29"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "Пассажирский транспорт", "маршрут4", "Сервисное поле", "шаблон4", "Старший машины 4", cl2, null);
        rec4.setStatus(RecordStatus.record_status_inprogress);
        Record rec5 = new Record(hash[4], Date.valueOf("2018-10-24"), Date.valueOf("2018-10-24"), Date.valueOf("2018-10-29"), Time.valueOf(LocalTime.now()), "Какойто текст", Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), "Пассажирский транспорт", "маршрут5", "Сервисное поле", "шаблон5", "Старший машины 5", cl2, null);
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
//        System.out.println("-----------Deleting claim 1-----------------");
//        clS.removeClaim(cl1.getId());
//        System.out.println("-----------Claims after deleting-----------------");
//        clS.getAllClaimsSortByDate().forEach(System.out::println);
//        System.out.println("-----------Record after-----------------");
//        clS.getRecords().forEach(System.out::println);

        Appointment ap1 = new Appointment(LocalDateTime.parse("2018-11-21T09:10:11", DateTimeFormatter.ISO_LOCAL_DATE_TIME), AppointmentStatus.appointment_status_created, "APPOINTMENT-NOTE-1", rec4, driver1, vechicle1);
        Appointment ap2 = new Appointment(LocalDateTime.parse("2018-11-21T12:13:14", DateTimeFormatter.ISO_LOCAL_DATE_TIME), AppointmentStatus.appointment_status_created, "APPOINTMENT-NOTE-2", rec4, driver2, vechicle2);
        Appointment ap3 = new Appointment(LocalDateTime.parse("2018-11-21T15:16:17", DateTimeFormatter.ISO_LOCAL_DATE_TIME), AppointmentStatus.appointment_status_created, "APPOINTMENT-NOTE-3", rec4, driver3, vechicle3);
        Appointment ap4 = new Appointment(LocalDateTime.parse("2018-11-22T18:19:20", DateTimeFormatter.ISO_LOCAL_DATE_TIME), AppointmentStatus.appointment_status_created, "APPOINTMENT-NOTE-4", rec4, driver4, vechicle4);
        Appointment ap5 = new Appointment(LocalDateTime.parse("2018-11-22T21:22:23", DateTimeFormatter.ISO_LOCAL_DATE_TIME), AppointmentStatus.appointment_status_created, "APPOINTMENT-NOTE-5", rec4, driver5, vechicle5);
        appointmentServ.addAppointment(ap1);
        appointmentServ.addAppointment(ap2);
        appointmentServ.addAppointment(ap3);
        appointmentServ.addAppointment(ap4);
        appointmentServ.addAppointment(ap5);
        System.out.println("-----------###########################-----------------");
        appointmentServ.getAppointmentByRecordAndStatus(rec4, AppointmentStatus.appointment_status_created).forEach(System.out::println);
        System.out.println("-----------###########################-----------------");
        LocalDateTime dateTimeStart = LocalDateTime.parse("2018-11-21T15:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime dateTimeEnd = LocalDateTime.parse("2018-11-22T19:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        appointmentServ.getAppointmentByRecordAndStatusAndDate(rec4, AppointmentStatus.appointment_status_created, dateTimeStart, dateTimeEnd).forEach(System.out::println);
        System.out.println("-----------###########################-----------------");
        List<Record> rls = (List<Record>) clS.getRecordsByClaim(cl2.getId());
        appointmentServ.getAppointmentByRecordsAndStatus(rls, AppointmentStatus.appointment_status_created).forEach(System.out::println);

        CriterionType crT1 = new CriterionType("л/с");
        CriterionType crT2 = new CriterionType("чел");
        CriterionType crT3 = new CriterionType("м");
        CriterionType crT4 = new CriterionType("мм");
        CriterionType crT5 = new CriterionType("кг");
        critServ.addCriterionType(crT1);
        critServ.addCriterionType(crT2);
        critServ.addCriterionType(crT3);
        critServ.addCriterionType(crT4);
        critServ.addCriterionType(crT5);

        Criterion cr1 = new Criterion("Мощность двигателя", crT1);
        Criterion cr2 = new Criterion("Вместимость пассажиров", crT2);
        Criterion cr3 = new Criterion("Длина стропы крана", crT3);
        Criterion cr4 = new Criterion("Максимальная длина перевозимого груза", crT3);
        Criterion cr5 = new Criterion("Длина кузова", crT3);
        Criterion cr6 = new Criterion("Высота", crT4);
        Criterion cr7 = new Criterion("Грузоподъёмность", crT5);
        Criterion cr8 = new Criterion("Масса", crT5);
        critServ.addCriterion(cr1);
        critServ.addCriterion(cr2);
        critServ.addCriterion(cr3);
        critServ.addCriterion(cr4);
        critServ.addCriterion(cr5);
        critServ.addCriterion(cr6);
        critServ.addCriterion(cr7);
        critServ.addCriterion(cr8);

        CriterionValue crV1 = new CriterionValue("115", rec5, vechicle1, cr1);
        CriterionValue crV2 = new CriterionValue("14", rec1, vechicle1, cr2);
        CriterionValue crV3 = new CriterionValue("15", rec2, vechicle3, cr3);
        CriterionValue crV4 = new CriterionValue("25", rec3, vechicle2, cr4);
        CriterionValue crV5 = new CriterionValue("20", rec3, vechicle2, cr5);
        CriterionValue crV6 = new CriterionValue("2500", rec4, vechicle4, cr6);
        CriterionValue crV7 = new CriterionValue("3560", rec5, vechicle2, cr7);
        CriterionValue crV8 = new CriterionValue("2100", rec1, vechicle4, cr8);
        critServ.addCriterionValue(crV1);
        critServ.addCriterionValue(crV2);
        critServ.addCriterionValue(crV3);
        critServ.addCriterionValue(crV4);
        critServ.addCriterionValue(crV5);
        critServ.addCriterionValue(crV6);
        critServ.addCriterionValue(crV7);
        critServ.addCriterionValue(crV8);
        System.out.println("critServ*************************************************************");

        critServ.removeCriterionType(crT3.getId());

        TaskList taskList = null;//new TaskList();
        Waybill waybill1 = new Waybill("серия1", "0001", 500.01f, 50.5f, "�?справено", "Здоров", "Замечаний нет", taskList);
        Waybill waybill2 = new Waybill("серия1", "0002", 400.01f, 40.5f, "�?справно", "Здоров", "Замечаний нет", taskList);
        Waybill waybill3 = new Waybill("серия2", "0001", 300.01f, 30.5f, "�?справно", "Здоров", "Замечаний нет", taskList);
        waybillService.addWaybill(waybill1);
        waybillService.addWaybill(waybill2);
        waybillService.addWaybill(waybill3);

        ap1.setWaybill(waybill1);
        ap2.setWaybill(waybill2);
        ap3.setWaybill(waybill3);

        ap1.createWaybill();
        ap1.excel2pdf();

        System.out.println("ap1:" + ap1.toString());

    }

    public static void main(String[] args) {
        SpringApplication.run(TransportationApplication.class, args);

    }

}
