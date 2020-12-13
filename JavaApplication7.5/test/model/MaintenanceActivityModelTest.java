/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class MaintenanceActivityModelTest {

    private static MaintenanceActivityModel instance;
    private static Connection connection;

    public MaintenanceActivityModelTest() {
    }

    @Before
    public void setUp() {
        instance = new MaintenanceActivityModel(0, 0, "", "", "", "", 0, "");
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
     * Test of deleteActivity method, of class MaintenanceActivityModel.
     */
    //caso in cui l'attività  da eliminare non esiste
    @Test
    public void testDeleteActivity1() {
        System.out.println("deleteActivity");
        int id = 500;
        boolean expResult = false;
        boolean result = instance.deleteActivity(id);
        assertEquals(expResult, result);
    }

    //caso in cui l'attivitÃ  da eliminare  esiste
    @Test
    public void testDeleteActivity2() {
        System.out.println("deleteActivity");
        int id = 5028;
        boolean expResult = true;
        boolean result = instance.deleteActivity(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of viewActivity method, of class MaintenanceActivityModel.
     */
    //caso in cui l'attivitÃ  con id=5 non esiste
    @Test
    public void testViewActivity1() {
        System.out.println("viewActivity");
        int id = 5;
        MaintenanceActivityModel expResult = null;
        MaintenanceActivityModel result = instance.viewActivity(id);
        assertEquals(expResult, result);

    }

    //Caso in cui l'attivitÃ  con id=3  esiste 
    @Test
    public void testViewActivity2() {
        System.out.println("viewActivity");
        int id = 2010;
        int expResult = 9;
        MaintenanceActivityModel result = instance.viewActivity(id);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of aggiornaNote method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAggiornaNote1() {
        System.out.println("aggiornaNote");
        String note = "Queste sono note lavoro";
        /* da rivedere !! */
    }

    /**
     * Test of getAllActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetAllActivity() {
        System.out.println("getAllActivity");
        List<MaintenanceActivityModel> expResult = null;
        List<MaintenanceActivityModel> result = instance.getAllActivity();
        assertNotEquals(expResult, result);

    }

    /**
     * Test of insertActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testInsertActivity() {
        System.out.println("insertActivity");
        int numberWeek = 10;
        String workNotes = "";
        String type = "planned";
        String factory = "Fisciano";
        String area = "Carpenteria";
        String tipology = "elettronica";
        boolean interruptible = true;
        int time = 10;
        String description = "Conoscenza del robot XP";
        boolean expResult = true;
        boolean result = instance.insertActivity(numberWeek, workNotes, type, factory, tipology, time, description, area, interruptible);
        assertEquals(expResult, result);
    }

}
