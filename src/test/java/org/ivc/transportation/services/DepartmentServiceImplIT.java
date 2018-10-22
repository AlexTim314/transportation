/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.services;

import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.DepartmentRepository;
import static org.junit.Assert.*;
import org.junit.Test;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author alextim
 */
@RunWith(SpringRunner.class)
public class DepartmentServiceImplIT {

    private int depListSize;
    private List<Department> returnList;

    @Before
    public void setUp() {
        Department dep1 = new Department("Отделение1", "адрес 1");
        Department dep2 = new Department("Отделение2", "адрес 2");
        returnList = new ArrayList<>();
        returnList.add(dep1);
        returnList.add(dep2);
        depListSize = 2;

        Mockito.when(departmentRepository.findAll())
                .thenReturn(returnList);

    }

    @TestConfiguration
    static class DepartmentServiceImplTestContextConfiguration {

        @Bean
        public DepartmentService departmentService() {
            return new DepartmentServiceImpl();
        }
    }

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void whenCallListDepartments_thenCorrectCollectionShouldBeFound() {
        Collection<Department> resultCollection = departmentService.listDepartments();
        assertThat(resultCollection.size()).isEqualTo(depListSize);
        assertTrue(resultCollection.containsAll(returnList));
    }

    /**
     * Test of addDepartment method, of class DepartmentServiceImpl.
     */
    @Test
    public void testAdd100Department() {
        int addDepNumber = 100;
        String name = "Новое подразделение";
        String address = "Новый адрес";
        for (int i = 0; i < addDepNumber; i++) {
            Department d = new Department(name + " " + i, address + " " + i);
            departmentService.addDepartment(d);
            returnList.add(d);
        }        
        Collection<Department > resultCollection = departmentService.listDepartments();
        assertThat(resultCollection.size()).isEqualTo(depListSize + addDepNumber);
        assertTrue(resultCollection.containsAll(returnList));
    }

}
