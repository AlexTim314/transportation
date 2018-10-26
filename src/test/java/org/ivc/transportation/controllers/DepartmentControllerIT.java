/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.ivc.transportation.entities.Department;
import org.ivc.transportation.services.ClaimService;
import org.ivc.transportation.services.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author alextim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DepartmentControllerIT {

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private ClaimService claimService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url;
    private List<Department> allDep;

    private static final int DEP_NUMBER = 10;
    private static final String LOCALHOST = "http://localhost:";
    private static final String DEP_URL = "transportation/departments";
    private final Random rand = new Random();

    @Before
    public void setUp() {
        url = LOCALHOST + port + DEP_URL;
        allDep = new ArrayList<>();
        for (int i = 0; i < DEP_NUMBER; i++) {
            Department d = new Department("Название " + i, "Адрес " + i);
            allDep.add(d);
        }
    }

    @Test
    public void whenDepartments_thenReturnJsonArray() throws Exception {
        // given
        given(departmentService.getDepartments()).willReturn(allDep);

        //when
        ResponseEntity<List<Department>> departmentResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Department>>() {
        });

        // then
        assertThat(departmentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(departmentResponse.getBody()).isEqualTo(allDep);
    }

    @Test
    public void whenFindById_thatDoesNotExist_shouldThrowExeption() throws Exception {
        // given
        long id = 0;
        Department emptyDepartment = new Department();
        given(departmentService.getDepartmentById(id)).willReturn(Optional.empty());

        // when
        ResponseEntity<Department> departmentResponse
                = restTemplate.getForEntity(url + "/" + id, Department.class);

        // then
        assertThat(departmentResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(departmentResponse.getBody()).isEqualTo(emptyDepartment);
    }

    @Test
    public void whenFindById_thatExist_thenReturnJsonDepartment() throws Exception {
        // given
        Department dep = allDep.get(rand.nextInt(DEP_NUMBER));
        long id = rand.nextLong();
        dep.setId(id);
        given(departmentService.getDepartmentById(id)).willReturn(Optional.of(dep));

        // when
        ResponseEntity<Department> departmentResponse
                = restTemplate.getForEntity(url + "/" + id, Department.class);

        // then
        assertThat(departmentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(departmentResponse.getBody()).isEqualTo(dep);
    }

    @Test
    public void whenDelById_thatExist_thenDepartmentCountDecrease() throws Exception {
        // given
        int index = rand.nextInt(DEP_NUMBER);
        Department dep = allDep.get(index);
        long id = rand.nextLong();
        dep.setId(id);
        //given(departmentService.removeDepartment(id)).
        //willAnswer(invocation -> this.)given(departmentService).
        willAnswer((iom) -> {
            allDep.remove(index);
            return null;
        })
                .given(departmentService).removeDepartment(id);

        // when
        /*
        ResponseEntity<Department> departmentResponse
                = restTemplate.getForEntity(url + "/" + id, Department.class);
        restTemplate.delete(url + "/" + id);
        */
        //when
        ResponseEntity<List<Department>> departmentResponse = restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<List<Department>>() {
        });

        // then
        assertThat(departmentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(departmentResponse.getBody()).isEqualTo(allDep);        
        
        
        // then
        // then
        //assertThat(departmentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(departmentResponse.getBody()).isEqualTo(allDep);
        assertThat(allDep.size()).isEqualTo(DEP_NUMBER - 1);
    }
    /*
    пример тестирования void метода или метода с сайд эффектами
    willAnswer((iom) -> {
            allDep.remove(index);
            return null; //To change body of generated lambdas, choose Tools | Templates.
        })
                .given(departmentService).removeDepartment(id);
     */

}
