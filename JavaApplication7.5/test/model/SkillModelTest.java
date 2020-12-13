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
 * @author jenni
 */
public class SkillModelTest {

    private static SkillModel instance;
    private static Connection connection;

    @Before
    public void setUp() {
        instance = new SkillModel(0, "");
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
     * Test of listSkills method, of class SkillModel.
     */
    /*@Test
    public void testListSkills() {
        System.out.println("listSkills");
        SkillModel instance = null;
        List<SkillModel> expResult = null;
        List<SkillModel> result = instance.listSkills();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/
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

}
