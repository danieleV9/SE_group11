/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author lyuba
 */
public class PlannerModelTest {

    private static PlannerModel instance;
    private static Connection connection;

    public PlannerModelTest() {
    }

    @Before
    public void setUp() {
        instance = new PlannerModel("", "");
        connection = instance.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(PlannerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            instance.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PlannerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        PlannerModel result = (PlannerModel) instance.findUser(username, password, role);
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
        PlannerModel result = (PlannerModel) instance.findUser(username, password, role);
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
        PlannerModel result = (PlannerModel) instance.findUser(username, password, role);
        assertNull(result);
    }

    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_3args3() throws Exception {
        System.out.println("findPlanner3");
        String username = "Gabriella";
        String password = "";
        String role = "Planner";
        PlannerModel expResult = null;
        PlannerModel result = (PlannerModel) instance.findUser(username, password, role);
        assertNull(result);
    }

    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test(expected = AssertionError.class)
    public void testFindPlanner_3args4() throws Exception {
        System.out.println("findPlanner4");
        String username = "";
        String password = "stabile95";
        String role = "Planner";
        PlannerModel expResult = null;
        PlannerModel result = (PlannerModel) instance.findUser(username, password, role);
        assertNotNull(result);
    }

    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test(expected = AssertionError.class)
    public void testFindPlanner_3args5() throws Exception {
        System.out.println("findPlanner5");
        //addPM();
        String username = "Benedetta";
        String password = "Russo98";
        String role = "Planner";
        PlannerModel expResult = new PlannerModel("Benedetta", "Russo98");
        PlannerModel result = (PlannerModel) instance.findUser(username, password, role);
        assertEquals(expResult, result);
    }

    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_String() {
        System.out.println("findPlanner");
        String username = "";
        PlannerModel expResult = null;
        PlannerModel result = (PlannerModel) instance.findUsername(username);
        assertNull(result);
    }

    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_String1() {
        System.out.println("findPlanner1");
        //delPM();
        String username = "Teresa";
        PlannerModel expResult = null;
        PlannerModel result = (PlannerModel) instance.findUsername(username);
        assertNull(result);
    }

    /**
     * Test of findPlanner method, of class PlannerModel.
     */
    @Test
    public void testFindPlanner_String2() {
        System.out.println("findPlanner2");
        String username = "Roberto";
        PlannerModel expResult = new PlannerModel("Roberto", "Buonora79");
        PlannerModel result = (PlannerModel) instance.findUsername(username);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of listPlanners method, of class PlannerModel.
     */
    @Test
    public void testListPlanners() {
        System.out.println("listPlanners");
        List<PlannerModel> first = instance.listUsers();
        int firstSize = first.size();
        String username = "Gianfranco";
        instance.deleteUser(username);
        List<PlannerModel> second = instance.listUsers();
        int secondSize = second.size();
        int expResult = firstSize - 1;
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
        boolean result = instance.createUser(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of createPlanner method, of class PlannerModel.
     */
    @Test
    public void testCreatePlanner1() {
        System.out.println("createPlanner1");
        String username = "Gabriele";
        String password = "Esposito97";
        boolean expResult = false; //mi aspetto false in quanto l'utente già esiste nel db
        boolean result = instance.createUser(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of createPlanner method, of class PlannerModel.
     */
    @Test
    public void testCreatePlanner2() {
        System.out.println("createPlanner2");
        String username = "Giorgio";
        String password = "giorgio80";
        boolean expResult = true;
        boolean result = instance.createUser(username, password);
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
        boolean result = instance.deleteUser(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of deletePlanner method, of class PlannerModel.
     */
    @Test
    public void testDeletePlanner1() {
        System.out.println("deletePlanner1");
        String username = "Valeria";
        boolean expResult = true;
        boolean result = instance.deleteUser(username);
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
        boolean result = instance.updateUserPassword(username, newpass);
        assertEquals(expResult, result);
    }

    /**
     * Test of updatePlannerPassword method, of class PlannerModel.
     */
    @Test
    public void testUpdatePlannerPassword1() {
        System.out.println("updatePlannerPassword1");
        String username = "Giu";
        String newpass = "";
        boolean expResult = false;
        boolean result = instance.updateUserPassword(username, newpass);
        assertEquals(expResult, result);
    }

    /**
     * Test of updatePlannerPassword method, of class PlannerModel.
     */
    @Test
    public void testUpdatePlannerPassword2() {
        System.out.println("updatePlannerPassword2");
        String username = "Valeria";
        String newpass = "newpass";
        boolean expResult = true;
        boolean result = instance.updateUserPassword(username, newpass);
        assertEquals(expResult, result);
    }
}
