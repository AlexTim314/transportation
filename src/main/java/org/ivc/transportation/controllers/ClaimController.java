package org.ivc.transportation.controllers;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Collection;
import org.ivc.transportation.config.trUtils;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.DateRange;
import static org.ivc.transportation.config.trUtils.errNotSpecifiedDepartmentException;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.FileStorage;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.entities.Waypoint;
import org.ivc.transportation.exceptions.NotSpecifiedDepartmentException;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.WaypointService;
import org.ivc.transportation.utils.UploadFileResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



/**
 *
 * @author Sokolov Slava
 */
@RestController
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WaypointService waypointService;


    /**
     * Метод возвращает заявки поданные подразделением. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением.
     *
     * @param principal данные пользователя
     * @param sD начальная дата
     * @param eD конечная дата
     * @param dr диапазон дат
     * @return При вызове без авторизации, возвращает null.
     */
    @GetMapping("/claims/byUser/{sD}/{eD}")
    public Collection<Claim> findClaimsByUser(Principal principal, @PathVariable("sD") String sD, @PathVariable("eD") String eD/*@RequestBody DateRange dr*/) {
        DateRange dr = new DateRange();
        dr.StartDate = Date.valueOf(sD);
        dr.EndDate = Date.valueOf(eD);
        System.out.println(dr);
        if (principal != null) { //может ли principal быть null если доступ только авторизованный?
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            //return claimService.getClaimsByDepartment(department.getId());
            claimService.getClaimsByDepartment(department.getId()).forEach(System.out::println);
            return claimService.getAllClaimsByDepartmentAndDate(department.getId(), dr);
        }
        return null;
    }

    /**
     * Метод для получения заявок заданного подразделения. Предполагается , что
     * доступ к этому методу будет только у администратора. Пользователям
     * предоставляется метод getClaims(Principal principal).
     *
     * @param department подразделение, заявки которого требуются
     * @param dr диапазон дат
     * @return список заявок подразделения
     */
    @PostMapping("/claims/byDepartment")
    public Collection<Claim> findClaimsByDepartment(@RequestBody Department department/*, @RequestBody DateRange dr*/) {
        return claimService.getClaimsByDepartment(department.getId());
    }

        
    @GetMapping("/claims")
    public Collection<Claim> findClaimsByDepartmentId(Principal principal) {
        if (principal != null) { 
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }

            return claimService.getClaimsByDepartment(department.getId());
        }
        return null;
    }
    
    
    /**
     * Метод для получения всех заявок выбранного промежутка времени.
     * Предполагается , что доступ к этому методу будет только у администратора.
     * Пользователям предоставляется метод getClaims(Principal principal).
     *
     * @param sD начальная дата
     * @param eD конечная дата
     * @param dr диапазон дат
     * @return список заявок
     */
    @GetMapping("/claims/{sD}/{eD}")
    public Collection<Claim> findAllClaims(@PathVariable("sD") String sD, @PathVariable("eD") String eD) {
        DateRange dr = new DateRange();
        dr.StartDate = Date.valueOf(sD);
        dr.EndDate = Date.valueOf(eD);
        return claimService.getAllClaimsByDate(dr);
    }

    /**
     * Метод возвращает заявки подразделения отфильтрованные по типу. Номер
     * подразделения извлекается из данных авторизовавшегося пользователя. Если
     * подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные пользователя
     * @param type тип заявки
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @GetMapping("/claims/byType/{type}")
    public Collection<Claim> findUserClaimsByType(Principal principal, @PathVariable("type") String type) {
        ClaimType ct = ClaimType.valueOf(type);
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.getClaimsByDepartment(department.getId()).forEach(System.out::println);
            return claimService.getClaimsByDepartmentAndClType(department.getId(), ct);
        }
        return null;
    }

    /**
     * Метод возвращает заявки подразделения отфильтрованные по состоянию. Номер
     * подразделения извлекается из данных авторизовавшегося пользователя. Если
     * подразделение не указано, то будет вызвано исключение
     * NotSpecifiedDepartmentException с соответствующим сообщением.
     *
     * @param principal данные пользователя
     * @param affirm состояние заявки (подтверждена или неподтверждена)
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @GetMapping("/claims/byAffirmation/{affirm}")
    public Collection<Claim> findClaimsByAffirmation(Principal principal, @PathVariable("affirm") String affirm) {
        Boolean af = Boolean.valueOf(affirm);
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.getClaimsByDepartment(department.getId()).forEach(System.out::println);
            return claimService.getClaimsByDepAndAffirmation(department.getId(), af);
        }
        return null;
    }

    /**
     * Метод добавляет новую заявку подразделения. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением
     *
     * @param principal данные пользователя
     * @param claim удаляемая заявка
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @PostMapping("/claims/byUser/create")
    public Collection<Claim> addClaim(Principal principal, @RequestBody Claim claim) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            
            claim.setAffirmation(Boolean.FALSE);
            claim.setDepartment(department);
            claimService.addClaim(claim);
            return claimService.getClaimsByDepartment(department.getId());
        }
        return null;
    }

    /**
     * Метод удаляет заявку подразделения. Номер подразделения извлекается из
     * данных авторизовавшегося пользователя. Если подразделение не указано, то
     * будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением
     *
     * @param principal данные пользователя
     * @param claim удаляемая заявка
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @DeleteMapping("/claims/byUser/delete/")
    public Collection<Claim> delClaimByUser(Principal principal, @RequestBody Claim claim) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.removeClaim(claim.getId());
            return claimService.getClaimsByDepartment(department.getId());
        }
        return null;
    }

    /**
     * Метод изменяет заявку подразделения. Номер подразделения извлекается из
     * данных авторизовавшегося пользователя. Если подразделение не указано, то
     * будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением
     *
     * @param principal данные пользователя
     * @param claim изменяемая заявка
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @PutMapping("/claims/byUser/update")
    public Collection<Claim> updateClaimByUser(Principal principal, @RequestBody Claim claim) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.updateClaim(claim, claim.getId());
            return claimService.getClaimsByDepartment(department.getId());
        }
        return null;
    }

    /**
     * Метод возвращает записи для каждой заявки.
     *
     * @param claim заявка
     * @return список записей
     */
    @PostMapping("/claims/byUser/records")
    public Collection<Record> findRecrodsByClaim(@RequestBody Claim claim) {
        System.out.println(claim);
        System.out.println("+++++++++++++++++++++++++++++++++");
        claimService.getRecordsByClaim(claim.getId()).forEach(System.out::println);
        return claimService.getRecordsByClaim(claim.getId());
    }

    /**
     * Метод добавляет новую запись в заявку подразделения. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением
     *
     * @param principal данные пользователя
     * @param record запись
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @PostMapping("/claims/byUser/records/create")
    public Collection<Record> addRecord(Principal principal, @RequestBody Record record) {
        System.out.println(record);
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.addRecord(record);
            System.out.println(record.getClaim());
            return claimService.getRecordsByClaim(record.getClaim().getId());
        }
        return null;
    }

    /**
     * Метод обновляет запись в заявке подразделения. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением
     *
     * @param principal данные пользователя
     * @param record запись
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @PutMapping("/claims/byUser/records/update")
    public Collection<Record> updateRecord(Principal principal, @RequestBody Record record) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.updateRecord(record, record.getId());
            return claimService.getRecordsByClaim(record.getClaim().getId());
        }
        return null;
    }

    /**
     * Метод удаляет запись в заявке подразделения. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением
     *
     * @param principal данные пользователя
     * @param record запись
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @DeleteMapping("/claims/byUser/records/delete")
    public Collection<Record> delRecord(Principal principal, @RequestBody Record record) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.removeRecord(record.getId());
            return claimService.getRecordsByClaim(record.getClaim().getId());
        }
        return null;

    }

    /**
     * Метод возвращает список типов транпортных средств. Номер подразделения
     * извлекается из данных авторизовавшегося пользователя. Если подразделение
     * не указано, то будет вызвано исключение NotSpecifiedDepartmentException с
     * соответствующим сообщением
     *
     * @param principal данные пользователя
     * @param spec специализация транспортных средств
     * @return список заявок подразделения. При вызове без авторизации,
     * возвращает null
     */
    @GetMapping("/claims/byUser/records/vehicleType")
    public Collection<VehicleType> getTypeVehicle(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }            
            return claimService.getVehicleTypes();
        }
        return null;
    }
    
    @GetMapping("/claims/byUser/records/waypoints")
    public Collection<Waypoint> getWaypoints(Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return waypointService.getWaypoints();
        }
        return null;
    }
    
    
    @PostMapping("/claims/byUser/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        FileStorage dbFile = claimService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/claims/byUser/downloadFile/")
                .path(dbFile.getId().toString())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @PostMapping("/claims/byUser/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/claims/byUser/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        FileStorage dbFile = claimService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
