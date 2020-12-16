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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author HP
 */
public class ProcedureModelTest {

    private ProcedureModel instance;
    private static Connection connection;
    
   

    public ProcedureModelTest() {
        
    }


    @Before
    public void setUp() {
        instance = new ProcedureModel("", "");
        connection = instance.getDaoConnection();
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
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlannerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Test of getProcedureSkill method, of class ProcedureModel.
     */
    @Test
    public void testGetProcedureSkill1() {
        System.out.println("getProcedureSkill1");
        String nomeprocedura = "proc2";
        List<SkillModel> expResult = new ArrayList();
        expResult.add(new SkillModel(1,"competenza1"));
        List<SkillModel> result = instance.getProcedureSkill(nomeprocedura);
        assertEquals(expResult, result);

    }

    /**
     * Test of addCompetence method, of class ProcedureModel.
     */
    @Test
    public void testAddCompetence1() {
        System.out.println("AddCompetence1");
        assertEquals(true, instance.addCompetence("proc2", 2)); //proc2 non ha già la competenza con id 2
    }
    
    /**
     * Test of addCompetence method, of class ProcedureModel.
     */
    @Test
    public void addCompetence2(){
       System.out.println("AddCompetence2");
       assertEquals(false, instance.addCompetence("proc2",1));   //proc2 ha già la competenza con id 1
    }

    /**
     * Test of removeCompetence method, of class ProcedureModel.
     */
    @Test
    public void testRemoveCompetence() {
        System.out.println("removeCompetence");
        boolean result = instance.removeCompetence("proc2", 1); //la proc2 ha questa competenza
        assertEquals(true, result);
    }
    
    /**
     * Test of removeCompetence method, of class ProcedureModel.
     */
    @Test
    public void testRemoveCompetence1() {
        System.out.println("removeCompetence1");
        boolean result = instance.removeCompetence("proc2", 2); //la proc2 non ha questa competenza
        assertEquals(true, result);
    }

    /**
     * Test of createProcedure method, of class ProcedureModel.
     */
    @Test
    public void testCreateProcedure1() {
        System.out.println("createProcedure1");
        String nomeprocedura = "proc2"; //è già nel db !!!
        assertEquals(false, instance.createProcedure(nomeprocedura, ""));
    }
    
    /**
     * Test of createProcedure method, of class ProcedureModel.
     */
    
    @Test
    public void testCreateProcedure2() {
        System.out.println("createProcedure2");
        String nomeprocedura = "procedura9090"; //non è già nel db !!!
        String path ="";
        boolean result=instance.createProcedure(nomeprocedura, path);
        assertEquals(true,result);
    }
    
    /**
     * Test of deleteProcedure method, of class ProcedureModel.
     * provo ad aliminare una procedura che non esiste
     */
    @Test
    public void testDeleteProcedure1() {
        System.out.println("deleteProcedure1");
        String nomeprocedura = "Procedura_non_esistente";
        assertEquals(false, instance.deleteProcedure(nomeprocedura));
    }
      /**
     * Test of deleteProcedure method, of class ProcedureModel.
     * provo ad aliminare una procedura che  esiste
     */
    @Test
    public void testDeleteProcedure2(){ //devo cancellare una procedura che non sia assosciata ad un'attività
        //altrimenti violato vincolo di integrità referenziale
        System.out.println("deleteprocedure2");
        assertEquals(true, instance.deleteProcedure("proceduratest"));
    }


    /**
     * Test of getPath method, of class ProcedureModel.
     * caso in cui vogliamo il path di una procedura che non è nel db 
     */
    @Test
    public void testGetPath_String1() {
        System.out.println("getPath1");
        String name = "proc_non_presente";
        String result = instance.getPath(name);
        assertEquals(null, result);
    }
    
    /**
     * Test of getPath method, of class ProcedureModel.
     * caso in cui vogliamo il path di una procedura che è presente nel db 
    */
    @Test
    public void testGetPath_String2() {
        System.out.println("getPath2");
        String expResult = "path";
        String result = instance.getPath("proc2");
        assertEquals(expResult, result);

    }

    /**
     * Test of proceduraExists method, of class ProcedureModel (case: procedure not exists)
     */
    @Test
    public void testProceduraExistsNO() {
        System.out.println("proceduraExistsNO");
        String name = "proceduranonesistente";
        assertEquals(false, instance.proceduraExists(name));

    }
    
    /**
     * Test of proceduraExists method, of class ProcedureModel ( case : procedure exists)
     */
    @Test
    public void testProceduraExistsYES(){
        System.out.println("proceduraExistsYES");
        String name = "proc2";
        assertEquals(true, instance.proceduraExists(name));
        
    }

    /**
     * Test of hasCompetence method, of class ProcedureModel.
     */
    @Test
    public void testHasCompetenceYES() {
        System.out.println("hasCompetenceYES");
        assertEquals(true, instance.hasCompetence("proc2", 1));
       
    }
    @Test
    public void testHasCompetenceNO() {
        System.out.println("hasCompetenceNO");
        assertEquals(false, instance.hasCompetence("proc2",4));
       
    }

}
