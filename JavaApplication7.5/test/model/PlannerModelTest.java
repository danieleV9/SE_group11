/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author lyuba
 */
public class PlannerModelTest {
    PlannerModel instance=null;
    
    public PlannerModelTest() {
    }
    
    @Before
    public void newPM(){
        instance=new PlannerModel("","");
    }
    
     public void addPM(){
         PlannerModel res= new PlannerModel("","");
       res= instance.findPlanner("caio");
       if(res == null)
             instance.createPlanner("caio", "caio");  
       else 
       {
           delPM();
           instance.createPlanner("caio", "caio");
       }
    }
    
    public void delPM(){
        instance.deletePlanner("caio");
    }
    /*
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
     * Test of getUsername method, of class PlannerModel.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        instance = new PlannerModel("planner1","tcygvhj");
        String expResult = "planner1";
        String result = instance.getUsername();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setUsername method, of class PlannerModel.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "planner2";
        instance= new PlannerModel("","");
        instance.setUsername(username);
        String expResult= "planner2";
        assertEquals(expResult,instance.getUsername());
    }

    /**
     * Test of getPassword method, of class PlannerModel.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        instance= new PlannerModel("anna","123456");
        String expResult = "123456";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class PlannerModel.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        instance= new PlannerModel("","haqqoo");
        instance.setPassword(password);
        String expResult="";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_3args() throws Exception {
        System.out.println("findPlanner");
        String username = "";
        String password = "";
        String role = "";
        PlannerModel expResult = null;
        PlannerModel result = instance.findPlanner(username, password, role);
        assertNull(result);
    }
    
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_3args1() throws Exception {
        System.out.println("findPlanner1");
        String username = "";
        String password = "";
        String role = "System Administrator";
        PlannerModel expResult = null;
        PlannerModel result = instance.findPlanner(username, password, role);
        assertNull(result);
    }
    
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_3args2() throws Exception {
        System.out.println("findPlanner2");
        String username = "";
        String password = "";
        String role = "Maintainer";
        PlannerModel expResult = null;
        PlannerModel result = instance.findPlanner(username, password, role);
        assertNull(result);
    }
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_3args3() throws Exception {
        System.out.println("findPlanner3");
        String username = "erfghj";
        String password = "";
        String role = "Planner";
        PlannerModel expResult = null;
        PlannerModel result = instance.findPlanner(username, password, role);
        assertNull(result);
    }
    
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test(expected=AssertionError.class)
    public void testFindPlanner_3args4() throws Exception{
        System.out.println("findPlanner4");
        String username = "";
        String password = "tdygfhuj";
        String role = "Planner";
        PlannerModel expResult = null;
        PlannerModel result = null;
        result=instance.findPlanner(username, password, role);
        assertNotNull(result);
    }
    
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test(expected=AssertionError.class)
    public void testFindPlanner_3args5() throws Exception{
        System.out.println("findPlanner5");
        addPM();
        String username = "caio";
        String password = "caio";
        String role = "Planner";
        PlannerModel expResult = new PlannerModel("caio","caio");
        PlannerModel result = null;
        result=instance.findPlanner(username, password, role);
        assertEquals(expResult,result);
    }
    
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_String() {
        System.out.println("findPlanner");
        String username = "";
        PlannerModel expResult = null;
        PlannerModel result = instance.findPlanner(username);
        assertNull(result);
    }
    
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_String1() {
        System.out.println("findPlanner1");
        delPM();
        String username = "caio";
        PlannerModel expResult = null;
        PlannerModel result = instance.findPlanner(username);
        assertNull(result);
    }
    
    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_String2() {
        System.out.println("findPlanner2");
        addPM();
        String username = "caio";
        PlannerModel expResult = new PlannerModel("caio","caio");
        PlannerModel result = instance.findPlanner(username);
        assertEquals(expResult.toString(),result.toString());
    }
    

    /**
     * Test of listPlanners method, of class PlannerModel.
     */
    @Test
    public void testListPlanners() {
        System.out.println("listPlanners");
        addPM();
        List<PlannerModel> first = instance.listPlanners();
        int firstSize= first.size();
        delPM();
        List<PlannerModel> second = instance.listPlanners();
        int secondSize= second.size();
        int expResult=firstSize-1;
        assertEquals(expResult, secondSize);
        
    }

    /**
     * Test of createPlanner method, of class PlannerModel.
     */
    @Test
    public void testCreatePlanner() {
        System.out.println("createPlanner");
        String username = "";
        String password = "";
        boolean expResult = false;
        boolean result = instance.createPlanner(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of createPlanner method, of class PlannerModel.
     */
    @Test
    public void testCreatePlanner1() {
        System.out.println("createPlanner1");
        addPM();
        String username = "caio";
        String password = "password";
        boolean expResult = false;
        boolean result = instance.createPlanner(username, password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createPlanner method, of class PlannerModel.
     */
    @Test
    public void testCreatePlanner2() {
        System.out.println("createPlanner1");
        delPM();
        String username = "caio";
        String password = "password";
        boolean expResult = true;
        boolean result = instance.createPlanner(username, password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deletePlanner method, of class PlannerModel.
     */
    @Test
    public void testDeletePlanner() {
        System.out.println("deletePlanner");
        String username = "";
        boolean expResult = false;
        boolean result = instance.deletePlanner(username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deletePlanner method, of class PlannerModel.
     */
    @Test
    public void testDeletePlanner1() {
        System.out.println("deletePlanner1");
        addPM();
        String username = "caio";
        boolean expResult = true;
        boolean result = instance.deletePlanner(username);
        assertEquals(expResult, result);
    }
    

    /**
     * Test of updatePlannerPassword method, of class PlannerModel.
     */
    @Test
    public void testUpdatePlannerPassword() {
        System.out.println("updatePlannerPassword");
        String username = "";
        String newpass = "";
        boolean expResult = false;
        boolean result = instance.updatePlannerPassword(username, newpass);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of updatePlannerPassword method, of class PlannerModel.
     */
    @Test
    public void testUpdatePlannerPassword1() {
        System.out.println("updatePlannerPassword1");
        delPM();
        String username = "caio";
        String newpass = "newpass";
        boolean expResult = false;
        boolean result = instance.updatePlannerPassword(username, newpass);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of updatePlannerPassword method, of class PlannerModel.
     */
    @Test
    public void testUpdatePlannerPassword2() {
        System.out.println("updatePlannerPassword2");
        addPM();
        String username = "caio";
        String newpass = "newpass";
        boolean expResult = true;
        boolean result = instance.updatePlannerPassword(username, newpass);
        assertEquals(expResult, result);
    }
    
}

