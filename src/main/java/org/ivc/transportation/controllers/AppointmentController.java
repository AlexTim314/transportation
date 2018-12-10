/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.ivc.transportation.config.trUtils.AppointmentStatus;
import static org.ivc.transportation.config.trUtils.errNotSpecifiedDepartmentException;
import org.ivc.transportation.entities.Appointment;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Driver;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.Vechicle;
import org.ivc.transportation.exceptions.NotSpecifiedDepartmentException;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.AppointmentService;
import org.ivc.transportation.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author first
 */
@RestController
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointServ;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ClaimService claimService;

    /**
     * Метод возвращает пользователю все назначения по диапазону дат. Номер
     * подразделения извлекается из данных авторизовавшегося пользователя. Если
     * подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @GetMapping("/appointments/byUser/{sD}/{eD}")
    public Collection<Appointment> getAppointmentsByUserAndDate(Principal principal, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByDate(sD, eD);
        }
        return null;
    }

    /**
     * Метод возвращает все назначения по диапазону дат. Предполагается , что
     * доступ к этому методу будет только у администратора. Пользователям
     * предоставляется метод getAppointmentsByDate(Principal principal).
     *
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @GetMapping("/appointments/{sD}/{eD}")
    public Collection<Appointment> getAppointmentsByDate(@PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        /*appointServ.getAppointmentByDate(sD, eD).forEach(System.out::println);*/
        return appointServ.getAppointmentByDate(sD, eD);
    }

    /**
     * Метод создает новое назначение. Номер подразделения извлекается из данных
     * авторизовавшегося пользователя. Если подразделение не указано, то будет
     * вызвано исключение NotSpecifiedDepartmentException с соответствующим
     * сообщением.
     *
     * @param principal данные пользователя
     * @param ap данные нового назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений отфильтрованный по диапазону дат
     */
    @PostMapping("/appointments/byUser/{sD}/{eD}/create")
    public Collection<Appointment> addAppointmentByUser(Principal principal, @RequestBody Appointment ap, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            appointServ.addAppointment(ap);
            return appointServ.getAppointmentByDate(sD, eD);
        }
        return null;
    }

    /**
     * Метод создает новое назначение. Предполагается, что доступ к этому методу
     * будет только у администратора. Пользователям предоставляется метод
     * addAppointmentByUser(Principal principal).
     *
     * @param ap данные нового назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений отфильтрованный по диапазону дат
     */
    @PostMapping("/appointments/{sD}/{eD}/create")
    public Collection<Appointment> addAppointment(@RequestBody Appointment ap, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        appointServ.addAppointment(ap);
        return appointServ.getAppointmentByDate(sD, eD);
    }

    /**
     * Метод изменяет выбранное пользователем назначение. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением.
     *
     * @param principal данные пользователя
     * @param ap новые данные назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений отфильтрованный по диапазону дат
     */
    @PutMapping("/appointments/byUser/{sD}/{eD}/update")
    public Collection<Appointment> updateAppointmentByUser(Principal principal, @RequestBody Appointment ap, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            appointServ.updateAppointment(ap, ap.getId());
            return appointServ.getAppointmentByDate(sD, eD);
        }
        return null;
    }

    /**
     * Метод изменяет назначение. Предполагается, что доступ к этому методу
     * будет только у администратора. Пользователям предоставляется метод
     * updateAppointmentByUser(Principal principal).
     *
     * @param ap данные нового назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений отфильтрованный по диапазону дат
     */
    @PutMapping("/appointments/{sD}/{eD}/update")
    public Collection<Appointment> updateAppointment(@RequestBody Appointment ap, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        appointServ.updateAppointment(ap, ap.getId());
        return appointServ.getAppointmentByDate(sD, eD);
    }

    /**
     * Метод удаляет назначение. Предполагается, что доступ к этому методу будет
     * только у администратора.
     *
     * @param ap данные нового назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений отфильтрованный по диапазону дат
     */
    @DeleteMapping("/appointments/{sD}/{eD}/delete")
    public Collection<Appointment> delAppointment(@RequestBody Appointment ap, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        appointServ.removeAppointment(ap.getId());
        return appointServ.getAppointmentByDate(sD, eD);
    }

    /**
     * Метод возвращает пользователю все назначения по записи и статусу. Номер
     * подразделения извлекается из данных авторизовавшегося пользователя. Если
     * подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param r данные записи
     * @param aps статус назначения
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{aps}/record")
    public Collection<Appointment> getAppointmentsByUserAndRecordAndStatus(Principal principal, @RequestBody Record r, @PathVariable("aps") AppointmentStatus aps) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByRecordAndStatus(r, aps);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по заявке и статусу. Номер
     * подразделения извлекается из данных авторизовавшегося пользователя. Если
     * подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param cl данные заявки
     * @param aps статус назначения
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{aps}/records")
    public Collection<Appointment> getAppointmentsByUserAndRecordsAndStatus(Principal principal, @RequestBody Claim cl, @PathVariable("aps") AppointmentStatus aps) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            List<Record> lr = (List<Record>) claimService.getRecordsByClaim(cl.getId());  // правильно ли конвертирован?
            return appointServ.getAppointmentByRecordsAndStatus(lr, aps);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по заявке и ее зписям,
     * статусу и дате. Номер подразделения извлекается из данных
     * авторизовавшегося пользователя. Если подразделение не указано, то будет
     * вызвано исключение NotSpecifiedDepartmentException с соответствующим
     * сообщением.
     *
     * @param principal данные ползователя
     * @param cl данные заявки
     * @param aps статус назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{sD}/{eD}/{aps}/records")
    public Collection<Appointment> getAppointmentsByUserAndRecordsAndStatusAndDate(Principal principal, @RequestBody Claim cl, @PathVariable("aps") AppointmentStatus aps, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            List<Record> lr = (List<Record>) claimService.getRecordsByClaim(cl.getId());  // правильно ли конвертирован?
            return appointServ.getAppointmentByRecordsAndStatusAndDate(lr, aps, sD, eD);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по заявке, статусу и дате.
     * Номер подразделения извлекается из данных авторизовавшегося пользователя.
     * Если подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param r запись
     * @param aps статус назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{sD}/{eD}/{aps}/record")
    public Collection<Appointment> getAppointmentsByUserAndRecordAndStatusAndDate(Principal principal, @RequestBody Record r, @PathVariable("aps") AppointmentStatus aps, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByRecordAndStatusAndDate(r, aps, eD, eD);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по диапазону дат и статусу.
     * Номер подразделения извлекается из данных авторизовавшегося пользователя.
     * Если подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param aps
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @GetMapping("/appointments/byUser/{sD}/{eD}/{aps}")
    public Collection<Appointment> getAppointmentsByUserAndStatusAndDate(Principal principal, @PathVariable("aps") AppointmentStatus aps, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByStatusAndDate(aps, eD, eD);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по водителю, статусу и дате.
     * Номер подразделения извлекается из данных авторизовавшегося пользователя.
     * Если подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param d водитель
     * @param aps статус назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{sD}/{eD}/{aps}/driver")
    public Collection<Appointment> getAppointmentsByUserAndDriverAndStatusAndDate(Principal principal, @RequestBody Driver d, @PathVariable("aps") AppointmentStatus aps, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByDriverAndStatusAndDate(d, aps, eD, eD);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по водителю и дате. Номер
     * подразделения извлекается из данных авторизовавшегося пользователя. Если
     * подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param d водитель
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{sD}/{eD}/driver")
    public Collection<Appointment> getAppointmentsByUserAndDriverAndDate(Principal principal, @RequestBody Driver d, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByDriverAndDate(d, eD, eD);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по транспортному средству и
     * дате. Номер подразделения извлекается из данных авторизовавшегося
     * пользователя. Если подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param v транспортное средство
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{sD}/{eD}/vechicle")
    public Collection<Appointment> getAppointmentsByUserAndVechicleAndDate(Principal principal, @RequestBody Vechicle v, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByVechicleAndDate(v, eD, eD);
        }
        return null;
    }

    /**
     * Метод возвращает пользователю все назначения по водителю, статусу и дате.
     * Номер подразделения извлекается из данных авторизовавшегося пользователя.
     * Если подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные ползователя
     * @param v транспортное средство
     * @param aps статус назначения
     * @param sD начальная дата фильтрации
     * @param eD конечная дата фильтрации
     * @return список назначений
     */
    @PostMapping("/appointments/byUser/{sD}/{eD}/{aps}/vechicle")
    public Collection<Appointment> getAppointmentsByUserAndVechicleAndStatusAndDate(Principal principal, @RequestBody Vechicle v, @PathVariable("aps") AppointmentStatus aps, @PathVariable("sD") LocalDateTime sD, @PathVariable("eD") LocalDateTime eD) {
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return appointServ.getAppointmentByVechicleAndStatusAndDate(v, aps, eD, eD);
        }
        return null;
    }
    
}
