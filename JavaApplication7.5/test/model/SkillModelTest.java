/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jenni
 */
public class SkillModelTest {
    SkillModel instance = null;
    
    public SkillModelTest() {
        instance = new SkillModel(0,"");
    }
    
    /*@BeforeAll //viene eseguito prima dell’esecuzione di ogni test, 
                 //utile per settare precondizioni comuni a più di un caso di test
    public static void setUpClass() {
    }
    
    @AfterAll //viene eseguito dopo ogni caso di test, utile per resettare le postcondizioni
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }*/

    /**
     * Test of getIdSkill method, of class SkillModel.
     */
    @Test
    public void testGetIdSkill() {
        System.out.println("getIdSkill");
        instance = new SkillModel(12,"");
        int expResult = 12;
        int result = instance.getIdSkill();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class SkillModel.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        instance = new SkillModel(0,"come stai?");
        String expResult = "come stai?";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listSkills method, of class SkillModel.
     */
    @Test
    public void testListSkills() {
        System.out.println("listSkills");
        SkillModel instance = null;
        List<SkillModel> expResult = null;
        List<SkillModel> result = instance.listSkills();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSkill method, of class SkillModel.
     */
    @Test
    public void testDeleteSkill() {
        System.out.println("deleteSkill");
        int idSkill = 0;
        instance = new SkillModel(1,"");
        boolean expResult = false;
        boolean result = instance.deleteSkill(idSkill);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of modifySkill method, of class SkillModel.
     */
    @Test
    public void testModifySkill() {
        System.out.println("modifySkill");
        int idSkill = 0;
        String descrizione = "";
        boolean expResult = false;
        boolean result = instance.modifySkill(idSkill, descrizione);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of insertSkill method, of class SkillModel.
     */
    @Test
    public void testInsertSkill() {
        System.out.println("insertSkill");
        String description = "pippo";
        instance = new SkillModel(0,"");
        instance.insertSkill(description);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
