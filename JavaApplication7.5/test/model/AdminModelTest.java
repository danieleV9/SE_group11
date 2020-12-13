/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    @Before
    public void setUp() {
        instance = new AdminModel("", "");
        connection = instance.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(AdminModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            instance.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(AdminModelTest.class.getName()).log(Level.SEVERE, null, ex);
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
        String role = "Maintainer";
        AdminModel expResult = null;
        AdminModel result = (AdminModel) instance.findUser(username, password, role);
        assertEquals(expResult, result);

    }

    /**
     * Test of findAdmin method, of class AdminModel.
     */
    @Test
    public void testFindAdmin1() throws Exception {
        System.out.println("findAdmin1");
        String username = "";
        String password = "";
        String role = "Planner";
        AdminModel expResult = null;
        AdminModel result = (AdminModel) instance.findUser(username, password, role);
        assertEquals(expResult, result);

    }

    /**
     * Test of findAdmin method, of class AdminModel.
     */
    @Test
    public void testFindAdmin2() throws Exception {
        System.out.println("findAdmin2");
        String username = "";
        String password = "carlox";
        String role = "System Administrator";
        AdminModel expResult = null;
        AdminModel result = (AdminModel) instance.findUser(username, password, role);
        assertEquals(expResult, result);

    }

    /**
     * Test of findAdmin method, of class AdminModel.
     */
    @Test
    public void testFindAdmin3() throws Exception {
        System.out.println("findAdmin3");
        String username = "";
        String password = "carlox";
        String role = "System Administrator";
        AdminModel expResult = null;
        AdminModel result = (AdminModel) instance.findUser(username, password, role);
        assertEquals(expResult, result);
    }
}