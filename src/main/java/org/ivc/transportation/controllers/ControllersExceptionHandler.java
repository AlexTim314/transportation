/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import org.ivc.transportation.exceptions.NullPrincipalException;
import org.ivc.transportation.exceptions.NonExistingDepartmentException;
import org.ivc.transportation.exceptions.NotSpecifiedDepartmentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author alextim
 */
@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NonExistingDepartmentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNonExistingDepartmentException(NonExistingDepartmentException ex) {        
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(NotSpecifiedDepartmentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNotSpecifiedDepartmentException(NotSpecifiedDepartmentException ex) {
        return ex.getMessage();
    }
    
    @ResponseBody
    @ExceptionHandler(NullPrincipalException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNullPrincipalException(NullPrincipalException ex) {
        return ex.getMessage();
    }
 
}
