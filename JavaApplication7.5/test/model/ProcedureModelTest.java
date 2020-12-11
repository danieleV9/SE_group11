/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class ProcedureModelTest {
    private ProcedureModel instance;

    
    public ProcedureModelTest() {
     instance = new ProcedureModel("","");
    }
    
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

    /**
     * Test of getAllProcedure method, of class ProcedureModel.
     */
    @Test
    public void testGetAllProcedure() {
        System.out.println("getAllProcedure");
        List<ProcedureModel> expResult = null;
        List<ProcedureModel> result = instance.getAllProcedure();
        assertNotEquals(expResult, result);
       
    }

    /**
     * Test of getProcedureSkill method, of class ProcedureModel.
     */
    @Test
    public void testGetProcedureSkill() {
        System.out.println("getProcedureSkill");
        String nomeprocedura = "Procedura_non_esistente";
        List<SkillModel> expResult = new ArrayList();
        List<SkillModel> result = instance.getProcedureSkill(nomeprocedura);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of addCompetence method, of class ProcedureModel.
     */
    @Test
    public void testAddCompetence() {
        System.out.println("addCompetence");
        String nomeprocedura = "procedura1334592";
        int id = 909090;
        boolean result = instance.addCompetence(nomeprocedura, id);
        assertEquals(true, result);
    }

    /**
     * Test of removeCompetence method, of class ProcedureModel.
     */
    @Test
    public void testRemoveCompetence() {
        System.out.println("removeCompetence");
        String nomeprocedura = "proceduraABABA";
        int id = 787878;
        boolean result = instance.removeCompetence(nomeprocedura, id);
        assertEquals(true, result);
       
    }

    /**
     * Test of createProcedure method, of class ProcedureModel.
     */
    @Test
    public void testCreateProcedure() {
        System.out.println("createProcedure");
        String nomeprocedura = "proc2"; //è già nel db !!!
        String path="";
        boolean result = instance.createProcedure(nomeprocedura,path);
        assertEquals(false, result);
        
    }

    /**
     * Test of deleteProcedure method, of class ProcedureModel.
     */
    @Test
    public void testDeleteProcedure() {
        System.out.println("deleteProcedure");
        String nomeprocedura = "ProceduraAZAZAZA";
        boolean result = instance.deleteProcedure(nomeprocedura);
        assertEquals(true, result);
        
    }


    /**
     * Test of getNomeProc method, of class ProcedureModel.
     */
    @Test
    public void testGetNomeProc() {
        System.out.println("getNomeProc");
        String expResult = "";
        assertEquals(expResult, instance.getNomeProc());
    }



    /**
     * Test of getPath method, of class ProcedureModel.
     */
    @Test
    public void testGetPath_0args() {
        System.out.println("getPath");
        String expResult = "";
        assertEquals(expResult, instance.getPath());
        
    }



    /**
     * Test of getPath method, of class ProcedureModel.
     */
    @Test
    public void testGetPath_String() {
        System.out.println("getPath");
        String name = "";
        String expResult = null;
        String result = instance.getPath(name);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of proceduraExists method, of class ProcedureModel.
     */
    @Test
    public void testProceduraExists() {
        System.out.println("proceduraExists");
        String name = "proceduranonesistente";
        boolean expResult = false;
        boolean result = instance.proceduraExists(name);
        assertEquals(expResult, result);
        
    }
    
}
