/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import org.ivc.transportation.repositories.UserRepository;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author alextim
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerIT {

  

    /**
     * Test of userManagementPage method, of class MainController.
     */
@Autowired
    private UserRepository repository;
 
    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void givenUnauthenticated_whenCallService_thenThrowsException() {
        repository.findByUserName("");
    }
 
    @WithMockUser(username="admin")
    @Test
    public void givenAuthenticated_whenCallServiceWithSecured_thenOk() {
       // assertThat(repository.findByUserName("")).isNotBlank();
        //assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    



}
