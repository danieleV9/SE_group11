/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connectionDB.ConnectionSingleton;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author lyuba
 */
public class AdminModelTest {

    private static AdminModel instance;
    private static Connection connection;

    public AdminModelTest() {
    }
    
    @AfterClass
    public static void afterclass(){
        try {
           
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void beforeclass(){
        connection = ConnectionSingleton.getInstance();
    }
    
    @Before
    public void setUp() {
        instance = new AdminModel("", "");
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
        } catch (SQLException ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of findAdmin method, of class AdminModel.
     */
    @Test
    public void testFindAdmin() throws Exception {
        System.out.println("findAdmin");
        String username = "";
        String password = "";
        AdminModel expResult = null;
        AdminModel result = (AdminModel) instance.findUser(username, password);
        assertEquals(expResult, result);

    }

    /**
     * Test of findAdmin method, of class AdminModel.
     */
    @Test
    public void testFindAdmin1() throws Exception {
        System.out.println("findAdmin1");
        String username = "";
        String password = "carlox";
        AdminModel expResult = null;
        AdminModel result = (AdminModel) instance.findUser(username, password);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of findAdmin method, of class AdminModel.
     */
    @Test
    public void testFindAdmin2() throws Exception {
        System.out.println("findAdmin2");
        String username = "admin1";
        String password = "admin1";
        AdminModel expResult = new AdminModel("admin1","admin1");
        AdminModel result = (AdminModel) instance.findUser(username, password);
        assertEquals(expResult.toString(), result.toString());
    }

}