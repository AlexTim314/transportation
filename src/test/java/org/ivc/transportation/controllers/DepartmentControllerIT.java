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
import static org.hamcrest.Matchers.hasSize;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.services.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author alextim
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
//@ComponentScan({"org.ivc.transportation.repositories"})
public class DepartmentControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DepartmentService service;

    @Test
    public void whenDepartments_thenReturnJsonArray() throws Exception {
        Department dep = new Department();
        List<Department> allDep = new ArrayList<Department>(){{
            add(dep);
        }};
        
        given(service.listDepartments()).willReturn(allDep);
        
        mvc.perform(get("/departments")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)));
      //.andExpect(jsonPath("$[0].name", is(dep.getName())));

    }

    /*public DepartmentControllerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
     */
}
