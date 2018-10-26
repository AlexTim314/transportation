/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ivc.transportation.exceptions.NonExistingDepartmentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    /* @ExceptionHandler(NonExistingDepartmentException.class)
    protected ResponseEntity<ControllersException> handleNonExistingDepartmentException() {
        return new ResponseEntity<>(new ControllersException("Не найдено подразделение с таким ID номером. There is no such Department."), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class ControllersException {
        private String message;
    }
    */
}
