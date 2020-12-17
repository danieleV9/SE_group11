/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connectionDB.ConnectionSingleton;
import java.sql.Connection;
import java.sql.SQLException;
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

    private MaintenanceActivityModel instance;
    private static Connection connection;

    public MaintenanceActivityModelTest() {
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
        ProcedureModel proceduras = new ProcedureModel("","");
        instance = new MaintenanceActivityModel(0, 0, "", "", "", "", 0, "",proceduras);
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
     * Test of deleteActivity method, of class MaintenanceActivityModel.
     */
    //caso in cui l'attività  da eliminare non esiste
    @Test
    public void testDeleteActivity1() {
        System.out.println("deleteActivity");
        int id = 500; // questo id non c'è nel db
        boolean expResult = false;
        boolean result = instance.deleteActivity(id);
        assertEquals(expResult, result);
    }

    //caso in cui l'attività  da eliminare  esiste
    @Test
    public void testDeleteActivity2() {
        System.out.println("deleteActivity");
        int id = 2; // questo id c'è nel db
        boolean expResult = true;
        boolean result = instance.deleteActivity(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of viewActivity method, of class MaintenanceActivityModel.
     */
    //caso in cui l'attività  con id=5 non esiste
    @Test
    public void testViewActivity1() {
        System.out.println("viewActivity");
        int id = 5;
        MaintenanceActivityModel expResult = null;
        MaintenanceActivityModel result = instance.viewActivity(id);
        assertEquals(expResult, result);

    }

    //Caso in cui l'attività  con id=2002  esiste nel db
    @Test
    public void testViewActivity2() {
        System.out.println("viewActivity");
        int id = 2002;
        MaintenanceActivityModel expResult = new MaintenanceActivityModel(3,2002, "elettronica","Restorarion of old construction" , "start at 9 am", "Carpenteria", 10, "Fisciano",new ProcedureModel("proc2","path"));
        MaintenanceActivityModel result = instance.viewActivity(id);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of aggiornaNote method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAggiornaNote1() {
        System.out.println("aggiornaNote1");
        String note = "Queste sono note lavoro";
        boolean result=instance.aggiornaNote(note, 2002);
        assertEquals(true,result);
    }
    
    /**
     * Test of aggiornaNote method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAggiornaNote2() {
        System.out.println("aggiornaNote2");
        String note = "";
        boolean result=instance.aggiornaNote(note, 2002);
        assertEquals(false,result);
    }
    
    /**
     * Test of aggiornaNote method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAggiornaNote3() {
        System.out.println("aggiornaNote3");
        String note = "Queste sono note lavoro";
        boolean result=instance.aggiornaNote(note, 0);
        assertEquals(false,result);
    }

    /**
     * Test of getAllActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetAllActivity_0args() { 
       
        System.out.println("getAllActivity");
        List<MaintenanceActivityModel> expResult = null;
        List<MaintenanceActivityModel> result = instance.getAllActivity();
        assertNotEquals(expResult, result);
    }
    
    /**
     * Test of getAllActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetAllActivity1_0args() { 
       System.out.println("getAllActivity1");
        List<MaintenanceActivityModel> first=instance.getAllActivity();
        int firstSize = first.size();
        int id = 2;
        instance.deleteActivity(id);
        List<MaintenanceActivityModel> second = instance.getAllActivity();
        int secondSize = second.size();
        int expResult = firstSize - 1;
        assertEquals(expResult, secondSize);
        
    }

    /**
     * Test of getAllActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetAllActivity_int() { //prende num WEEK
        System.out.println("getAllActivityBYWEEK");
        assertEquals(null,instance.getAllActivity(59));
       
    }

    
    /**
     * Test of getAllActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetAllActivity_int1() { //prende num WEEK
        System.out.println("getAllActivityBYWEEK2");
        assertEquals(null,instance.getAllActivity(0));
    }

    /**
     * Test of findProcedura method, of class MaintenanceActivityModel.
     */
    @Test
    public void testFindProcedura() {
        System.out.println("findProcedura");
        int id = 0;
        String expResult = null; // perchè non esiste attività
        String result = instance.findProcedura(id);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of findProcedura method, of class MaintenanceActivityModel.
     */
    @Test
    public void testFindProcedura2() {
        System.out.println("findProcedura2");
        int id = 2002;
        String expResult = "proc2"; 
        String result = instance.findProcedura(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of assignedActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAssignedActivity() {
        System.out.println("assignedActivity");
        int id = 0; // non esiste questa attività
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        boolean expResult = false;
        boolean result = instance.assignedActivity(id);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of assignedActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAssignedActivity2() {
        System.out.println("assignedActivity2");
        int id = 12; // questa attività è stata assegnata a un maintainer
        boolean expResult = true;
        boolean result = instance.assignedActivity(id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of assignNewActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAssignNewActivity() {
        System.out.println("assignNewActivity");
        int id = 0; //non esiste questa attività
        String username = "";
        String data = "";
        boolean expResult = false;
        boolean result = instance.assignNewActivity(id, username, data);
        assertEquals(expResult, result);
    }

    /**
     * Test of assignNewActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testAssignNewActivity2() {
        System.out.println("assignNewActivity2");
        int id = 2; 
        String username = ""; //non posso assegnare un maintainer con username vuoto
        String data = "";
        boolean expResult = false;
        boolean result = instance.assignNewActivity(id, username, data);
        assertEquals(expResult, result);
    }

}
