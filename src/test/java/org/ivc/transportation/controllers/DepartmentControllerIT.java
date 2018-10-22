/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.services.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author alextim
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentService service;

    private List<Department> allDep;

    private static final int DEP_NUMBER = 100;
    private final Random rand = new Random();

    @Before
    public void setUp() {
        allDep = new ArrayList<>();
        for (int i = 0; i < DEP_NUMBER; i++) {
            Department d = new Department("Название " + i, "Адрес " + i);
            allDep.add(d);
        }
    }

    @Test
    public void whenDepartments_thenReturnJsonArray() throws Exception {

        given(service.listDepartments()).willReturn(allDep);

        int index = rand.nextInt(100);

        mvc.perform(get("/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(DEP_NUMBER)))
                .andExpect(jsonPath("$[" + index + "].name"
                        , is(allDep.get(index).getName())))
                .andExpect(jsonPath("$[" + index + "].addres"
                        , is(allDep.get(index).getAddres())));

    }
    /*
    @Test
    public void whenDepartmentsID_thenReturnJsonDepartment(){
        Department dep = allDep.get(rand.nextInt(100));
        given(service.getDepartmentById(Optional.of(dep.getId())))
                .willReturn(dep);
        
        
    }

    /*public DepartmentControllerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    
    @After
    public void tearDown() {
    }
     */
}
