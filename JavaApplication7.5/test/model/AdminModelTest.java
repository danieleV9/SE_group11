/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author lyuba
 */
public class AdminModelTest {
    
    public AdminModelTest() {
    }
    /*
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }*/

    /**
     * Test of getUsername method, of class AdminModel.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        AdminModel instance = null;
        instance = new AdminModel("admin1","");
        String expResult = "admin1";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setUsername method, of class AdminModel.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "admin1";
        AdminModel instance = null;
        instance= new AdminModel("","");
        String expResult="admin1";
        instance.setUsername(username);
        assertEquals(expResult,instance.getUsername());
    }

    /**
     * Test of getPassword method, of class AdminModel.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        AdminModel instance = null;
        instance= new AdminModel("","antonio123");
        String expResult = "antonio123";
        String result = instance.getPassword();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setPassword method, of class AdminModel.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "pass1";
        AdminModel instance = null;
        instance = new AdminModel("","");
        instance.setPassword(password);
        String expResult="pass1";
        assertEquals(expResult,instance.getPassword());
    }

    /**
     * Test of findAdmin method, of class AdminModel.
     */
    @Test
    public void testFindAdmin() {
        System.out.println("findAdmin");
        String username = "";
        String password = "";
        String role = "Maintainer";
        AdminModel instance = null;
        instance= new AdminModel("","");
        AdminModel expResult = null;
        AdminModel result = instance.findAdmin(username, password, role);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of findAdmin method, of class AdminModel.
     */
       @Test
    public void testFindAdmin1() {
        System.out.println("findAdmin1");
        String username = "";
        String password = "";
        String role = "Planner";
        AdminModel instance = null;
        instance= new AdminModel("","");
        instance= new AdminModel("","");
        AdminModel expResult = null;
        AdminModel result = instance.findAdmin(username, password, role);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of findAdmin method, of class AdminModel.
     */
       @Test
    public void testFindAdmin2() {
        System.out.println("findAdmin2");
        String username = "";
        String password = "carlox";
        String role = "System Administrator";
        AdminModel instance = null;
        instance= new AdminModel("","");
        AdminModel expResult = null;
        AdminModel result = instance.findAdmin(username, password, role);
        assertEquals(expResult, result);
        
    }
    
   /**
     * Test of findAdmin method, of class AdminModel.
     */
     @Test
    public void testFindAdmin3() {
        System.out.println("findAdmin3");
        String username = "";
        String password = "carlox";
        String role = "System Administrator";
        AdminModel instance = null;
        instance= new AdminModel("","");
        AdminModel expResult = null;
        AdminModel result = instance.findAdmin(username, password, role);
        assertEquals(expResult, result);
        
    }

}
