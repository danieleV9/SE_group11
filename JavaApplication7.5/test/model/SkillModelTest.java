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
 * @author jenni
 */
public class SkillModelTest {

    private static SkillModel instance;
    private static Connection connection;

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
        instance = new SkillModel(0, ""); 
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
     * Test of deleteSkill method, of class SkillModel.
     */
    @Test
    public void testDeleteSkill() {
        System.out.println("deleteSkill");
        int idSkill = 0;
        instance = new SkillModel(1, "");
        boolean expResult = false;
        boolean result = instance.deleteSkill(idSkill);
        assertEquals(expResult, result);
    }

    /**
     * Test of modifySkill method, of class SkillModel.
     */
    @Test
    public void testModifySkill() {
        System.out.println("modifySkill");
        int idSkill = 2016;
        String descrizione = "Capacità di intervento nel contesto";
        boolean expResult = true;
        boolean result = instance.modifySkill(idSkill, descrizione);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertSkill method, of class SkillModel.
     */
    @Test
    public void testInsertSkill() {
        System.out.println("insertSkill");
        String description = "Conoscenza del robot Xy-z";
        boolean expResult = true;
        boolean result = instance.insertSkill(description);
        assertEquals(expResult, result);
    }

    /**
     * Test of listSkills method, of class SkillModel.
     */
    @Test(expected=AssertionError.class)
    public void testListSkills() {
        System.out.println("listSkills");
        List<SkillModel> expResult = null;
        List<SkillModel> result = instance.listSkills();
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testListSkills1() {
        System.out.println("listSkills1");
        List<SkillModel> first = instance.listSkills();
        int firstSize = first.size();
        int id = 7019;
        instance.deleteSkill(id);
        List<SkillModel> second = instance.listSkills();
        int secondSize = second.size();
        int expResult = firstSize - 1;
        assertEquals(expResult, secondSize);
    }

    /**
     * Test of listSkillsMA method, of class SkillModel.
     */
    @Test
    public void testListSkillsMA() {
        System.out.println("listSkillsMA");
        String username = "";
        List<SkillModel> expResult = null;
        List<SkillModel> result = instance.listSkillsMA(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of findSkill method, of class SkillModel.
     */
    @Test
    public void testFindSkill_int() {
        int id=0;
        SkillModel expResult = null;
        SkillModel result = instance.findSkill(id);
        assertNull(result);
    }
    
    /**
    * Test of findSkill method, of class SkillModel.
    */
    @Test
    public void testFindSkill_int1(){
        System.out.println("findSkill1");
        int id = 1;
        SkillModel expResult = new SkillModel(1,"competenza1");
        SkillModel result = instance.findSkill(id);
        assertEquals(expResult.toString(), result.toString());
    }
    
    /**
    * Test of findSkill method, of class SkillModel.
    */
    @Test
    public void testFindSkill_String1 () {
    System.out.println("findSkill1");
    String description = "competenza1";
    SkillModel expResult = new SkillModel(1,"competenza1");
    SkillModel result = instance.findSkill(description);
    assertEquals(expResult.toString(), result.toString());
}

    /**
     * Test of findSkill method, of class SkillModel.
     */
    @Test
    public void testFindSkill_String() {
        System.out.println("findSkill");
        String description = "";
        SkillModel expResult = null;
        SkillModel result = instance.findSkill(description);
        assertNull(result);
        
    }

}
