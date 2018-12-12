package org.ivc.transportation.controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.Collection;
import org.ivc.transportation.config.trUtils.ClaimType;
import org.ivc.transportation.config.trUtils.DateRange;
import static org.ivc.transportation.config.trUtils.errNotSpecifiedDepartmentException;
import org.ivc.transportation.entities.Claim;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.entities.Record;
import org.ivc.transportation.entities.VehicleType;
import org.ivc.transportation.exceptions.NotSpecifiedDepartmentException;
import org.ivc.transportation.repositories.UserRepository;
import org.ivc.transportation.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
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
 * @author Sokolov Slava
 */
@RestController
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private UserRepository userRepository;

    /**
     * РњРµС‚РѕРґ РІРѕР·РІСЂР°С‰Р°РµС‚ Р·Р°СЏРІРєРё РїРѕРґР°РЅРЅС‹Рµ РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµРј. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ
     * РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ
     * РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј.
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param sD РЅР°С‡Р°Р»СЊРЅР°СЏ РґР°С‚Р°
     * @param eD РєРѕРЅРµС‡РЅР°СЏ РґР°С‚Р°
     * @param dr РґРёР°РїР°Р·РѕРЅ РґР°С‚
     * @return РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё, РІРѕР·РІСЂР°С‰Р°РµС‚ null.
     */
    @GetMapping("/claims/byUser/{sD}/{eD}")
    public Collection<Claim> findClaimsByUser(Principal principal, @PathVariable("sD") String sD, @PathVariable("eD") String eD/*@RequestBody DateRange dr*/) {
        DateRange dr = new DateRange();
        dr.StartDate = Date.valueOf(sD);
        dr.EndDate = Date.valueOf(eD);
        System.out.println(dr);
        if (principal != null) { //РјРѕР¶РµС‚ Р»Рё principal Р±С‹С‚СЊ null РµСЃР»Рё РґРѕСЃС‚СѓРї С‚РѕР»СЊРєРѕ Р°РІС‚РѕСЂРёР·РѕРІР°РЅРЅС‹Р№?
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
     * РњРµС‚РѕРґ РґР»СЏ РїРѕР»СѓС‡РµРЅРёСЏ Р·Р°СЏРІРѕРє Р·Р°РґР°РЅРЅРѕРіРѕ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРµРґРїРѕР»Р°РіР°РµС‚СЃСЏ , С‡С‚Рѕ
     * РґРѕСЃС‚СѓРї Рє СЌС‚РѕРјСѓ РјРµС‚РѕРґСѓ Р±СѓРґРµС‚ С‚РѕР»СЊРєРѕ Сѓ Р°РґРјРёРЅРёСЃС‚СЂР°С‚РѕСЂР°. РџРѕР»СЊР·РѕРІР°С‚РµР»СЏРј
     * РїСЂРµРґРѕСЃС‚Р°РІР»СЏРµС‚СЃСЏ РјРµС‚РѕРґ getClaims(Principal principal).
     *
     * @param department РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ, Р·Р°СЏРІРєРё РєРѕС‚РѕСЂРѕРіРѕ С‚СЂРµР±СѓСЋС‚СЃСЏ
     * @param dr РґРёР°РїР°Р·РѕРЅ РґР°С‚
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ
     */
    @PostMapping("/claims/byDepartment")
    public Collection<Claim> findClaimsByDepartment(@RequestBody Department department/*, @RequestBody DateRange dr*/) {
        //return claimService.getAllClaimsByDate(dr);
        return claimService.getClaimsByDepartment(department.getId());
    }

    /**
     * РњРµС‚РѕРґ РґР»СЏ РїРѕР»СѓС‡РµРЅРёСЏ РІСЃРµС… Р·Р°СЏРІРѕРє РІС‹Р±СЂР°РЅРЅРѕРіРѕ РїСЂРѕРјРµР¶СѓС‚РєР° РІСЂРµРјРµРЅРё.
     * РџСЂРµРґРїРѕР»Р°РіР°РµС‚СЃСЏ , С‡С‚Рѕ РґРѕСЃС‚СѓРї Рє СЌС‚РѕРјСѓ РјРµС‚РѕРґСѓ Р±СѓРґРµС‚ С‚РѕР»СЊРєРѕ Сѓ Р°РґРјРёРЅРёСЃС‚СЂР°С‚РѕСЂР°.
     * РџРѕР»СЊР·РѕРІР°С‚РµР»СЏРј РїСЂРµРґРѕСЃС‚Р°РІР»СЏРµС‚СЃСЏ РјРµС‚РѕРґ getClaims(Principal principal).
     *
     * @param sD РЅР°С‡Р°Р»СЊРЅР°СЏ РґР°С‚Р°
     * @param eD РєРѕРЅРµС‡РЅР°СЏ РґР°С‚Р°
     * @param dr РґРёР°РїР°Р·РѕРЅ РґР°С‚
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє
     */
    @GetMapping("/claims/{sD}/{eD}")
    public Collection<Claim> findAllClaims(@PathVariable("sD") String sD, @PathVariable("eD") String eD) {
        DateRange dr = new DateRange();
        dr.StartDate = Date.valueOf(sD);
        dr.EndDate = Date.valueOf(eD);
        return claimService.getAllClaimsByDate(dr);
    }

    /**
     * РњРµС‚РѕРґ РІРѕР·РІСЂР°С‰Р°РµС‚ Р·Р°СЏРІРєРё РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ РѕС‚С„РёР»СЊС‚СЂРѕРІР°РЅРЅС‹Рµ РїРѕ С‚РёРїСѓ. РќРѕРјРµСЂ
     * РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё
     * РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ
     * NotSpecifiedDepartmentException СЃ СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј.
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param type С‚РёРї Р·Р°СЏРІРєРё
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
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
            return claimService.getClaimsByDepartmentAndTip(department.getId(), ct);
        }
        return null;
    }

    /**
     * РњРµС‚РѕРґ РІРѕР·РІСЂР°С‰Р°РµС‚ Р·Р°СЏРІРєРё РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ РѕС‚С„РёР»СЊС‚СЂРѕРІР°РЅРЅС‹Рµ РїРѕ СЃРѕСЃС‚РѕСЏРЅРёСЋ. РќРѕРјРµСЂ
     * РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё
     * РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ
     * NotSpecifiedDepartmentException СЃ СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј.
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param affirm СЃРѕСЃС‚РѕСЏРЅРёРµ Р·Р°СЏРІРєРё (РїРѕРґС‚РІРµСЂР¶РґРµРЅР° РёР»Рё РЅРµРїРѕРґС‚РІРµСЂР¶РґРµРЅР°)
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
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
     * РњРµС‚РѕРґ РґРѕР±Р°РІР»СЏРµС‚ РЅРѕРІСѓСЋ Р·Р°СЏРІРєСѓ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ
     * РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ
     * РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param claim СѓРґР°Р»СЏРµРјР°СЏ Р·Р°СЏРІРєР°
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
     */
    @PostMapping("/claims/byUser/create")
    public Collection<Claim> addClaim(Principal principal, @RequestBody Claim claim) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claim.setDepartment(department);
            claimService.addClaim(claim);
            return claimService.getClaimsByDepartment(department.getId());
        }
        return null;
    }

    /**
     * РњРµС‚РѕРґ СѓРґР°Р»СЏРµС‚ Р·Р°СЏРІРєСѓ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ РёР·РІР»РµРєР°РµС‚СЃСЏ РёР·
     * РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ
     * Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param claim СѓРґР°Р»СЏРµРјР°СЏ Р·Р°СЏРІРєР°
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
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
     * РњРµС‚РѕРґ РёР·РјРµРЅСЏРµС‚ Р·Р°СЏРІРєСѓ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ РёР·РІР»РµРєР°РµС‚СЃСЏ РёР·
     * РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ
     * Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param claim РёР·РјРµРЅСЏРµРјР°СЏ Р·Р°СЏРІРєР°
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
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
     * РњРµС‚РѕРґ РІРѕР·РІСЂР°С‰Р°РµС‚ Р·Р°РїРёСЃРё РґР»СЏ РєР°Р¶РґРѕР№ Р·Р°СЏРІРєРё.
     *
     * @param claim Р·Р°СЏРІРєР°
     * @return СЃРїРёСЃРѕРє Р·Р°РїРёСЃРµР№
     */
    @PostMapping("/claims/byUser/records")
    public Collection<Record> findRecrodsByClaim(@RequestBody Claim claim) {
        System.out.println(claim);
        claimService.getRecordsByClaim(claim.getId()).forEach(System.out::println);
        return claimService.getRecordsByClaim(claim.getId());
    }

    /**
     * РњРµС‚РѕРґ РґРѕР±Р°РІР»СЏРµС‚ РЅРѕРІСѓСЋ Р·Р°РїРёСЃСЊ РІ Р·Р°СЏРІРєСѓ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ
     * РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ
     * РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param record Р·Р°РїРёСЃСЊ
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
     */
    @PostMapping("/claims/byUser/records/create")
    public Collection<Record> addRecord(Principal principal, @RequestBody Record record) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            claimService.addRecord(record);
            return claimService.getRecordsByClaim(record.getClaim().getId());
        }
        return null;
    }

    /**
     * РњРµС‚РѕРґ РѕР±РЅРѕРІР»СЏРµС‚ Р·Р°РїРёСЃСЊ РІ Р·Р°СЏРІРєРµ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ
     * РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ
     * РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param record Р·Р°РїРёСЃСЊ
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
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
     * РњРµС‚РѕРґ СѓРґР°Р»СЏРµС‚ Р·Р°РїРёСЃСЊ РІ Р·Р°СЏРІРєРµ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ
     * РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ
     * РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param record Р·Р°РїРёСЃСЊ
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
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
     * РњРµС‚РѕРґ РІРѕР·РІСЂР°С‰Р°РµС‚ СЃРїРёСЃРѕРє С‚РёРїРѕРІ С‚СЂР°РЅРїРѕСЂС‚РЅС‹С… СЃСЂРµРґСЃС‚РІ. РќРѕРјРµСЂ РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ
     * РёР·РІР»РµРєР°РµС‚СЃСЏ РёР· РґР°РЅРЅС‹С… Р°РІС‚РѕСЂРёР·РѕРІР°РІС€РµРіРѕСЃСЏ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ. Р•СЃР»Рё РїРѕРґСЂР°Р·РґРµР»РµРЅРёРµ
     * РЅРµ СѓРєР°Р·Р°РЅРѕ, С‚Рѕ Р±СѓРґРµС‚ РІС‹Р·РІР°РЅРѕ РёСЃРєР»СЋС‡РµРЅРёРµ NotSpecifiedDepartmentException СЃ
     * СЃРѕРѕС‚РІРµС‚СЃС‚РІСѓСЋС‰РёРј СЃРѕРѕР±С‰РµРЅРёРµРј
     *
     * @param principal РґР°РЅРЅС‹Рµ РїРѕР»СЊР·РѕРІР°С‚РµР»СЏ
     * @param spec СЃРїРµС†РёР°Р»РёР·Р°С†РёСЏ С‚СЂР°РЅСЃРїРѕСЂС‚РЅС‹С… СЃСЂРµРґСЃС‚РІ
     * @return СЃРїРёСЃРѕРє Р·Р°СЏРІРѕРє РїРѕРґСЂР°Р·РґРµР»РµРЅРёСЏ. РџСЂРё РІС‹Р·РѕРІРµ Р±РµР· Р°РІС‚РѕСЂРёР·Р°С†РёРё,
     * РІРѕР·РІСЂР°С‰Р°РµС‚ null
     */
    @GetMapping("/claims/byUser/records/{spec}")
    public Collection<VehicleType> getTypeVechicleBySpecialization(Principal principal, @PathVariable("spec") String spec) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            Department department = userRepository.findByUserName(loginedUser.getUsername()).getDepartment();
            if (department == null) {
                throw new NotSpecifiedDepartmentException(errNotSpecifiedDepartmentException);
            }
            return claimService.getTypeVechiclesBySpicialization(spec);
        }
        return null;
    }

}
