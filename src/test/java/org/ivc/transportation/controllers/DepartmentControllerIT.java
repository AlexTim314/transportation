/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.controllers;

import java.util.Collection;
import org.ivc.transportation.entities.Department;
import org.ivc.transportation.repositories.DepartmentRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author alextim
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentControllerIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

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
    /**
     * Test of getDepartments method, of class DepartmentController.
     */
    @Test
    public void testGetDepartments() {
        System.out.println("getDepartments");
        DepartmentController instance = new DepartmentController();
        Collection<Department> expResult = null;
        Collection<Department> result = instance.getDepartments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   /* @Test
    public void whenGetDepartments_thenReturnDepartmentsList() {
        // given
        Department dep = new Department("Отделение1", "адрес 1");
        entityManager.persist(dep);
        entityManager.flush();

        // when
        Department found = departmentRepository.findByName(alex.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(alex.getName());
    }*/

    /**
     * Test of getDepartment method, of class DepartmentController.
     */
    @Test
    public void testGetDepartment() {
        System.out.println("getDepartment");
        long id = 0L;
        DepartmentController instance = new DepartmentController();
        Department expResult = null;
        Department result = instance.getDepartment(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delDepartment method, of class DepartmentController.
     */
    @Test
    public void testDelDepartment() {
        System.out.println("delDepartment");
        long id = 0L;
        DepartmentController instance = new DepartmentController();
        instance.delDepartment(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDepartment method, of class DepartmentController.
     */
    @Test
    public void testAddDepartment() {
        System.out.println("addDepartment");
        Department department = null;
        DepartmentController instance = new DepartmentController();
        instance.addDepartment(department);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStudent method, of class DepartmentController.
     */
    @Test
    public void testUpdateStudent() {
        System.out.println("updateStudent");
        Department dep = null;
        long id = 0L;
        DepartmentController instance = new DepartmentController();
        instance.updateStudent(dep, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
