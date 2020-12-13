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
 * @author lyuba
 */
public class MaintainerModelTest {

    private static MaintainerModel instance;
    private static Connection connection;

    public MaintainerModelTest() {
    }

    @Before
    public void setUp() {
        instance = new MaintainerModel("", "");
        connection = instance.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            instance.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testFindMaintainer_3args() { //supponendo che esista
        System.out.println("findMaintainer_3args");
        String username = "jennifer";
        String password = "cutolo";
        String role = "Maintainer";
        MaintainerModel expResult = new MaintainerModel(username, password);
        MaintainerModel result = null;
        try {
            result = (MaintainerModel) instance.findUser(username, password, role);
            assertEquals("Maintainer non trovato", expResult.toString(), result.toString()); //deve stampare messaggio se test fallisce
        } catch (Exception ex) {
        }
    }

    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test(expected = AssertionError.class)
    public void testFindMaintainer_3args1() {
        System.out.println("findMaintainer_3args1");
        String username = "";
        String password = "maintainer6";
        String role = "Maintainer";
        MaintainerModel expResult = new MaintainerModel(username, password);
        System.out.println(expResult.toString());
        MaintainerModel result = null;
        try {
            result = (MaintainerModel) instance.findUser(username, password, role);
        } catch (Exception ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult.toString(), result);
    }

    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testFindMaintainer_String() { //un username vuoto non può esserci nel DB quindi non avrò nessun riscontro
        System.out.println("findMaintainer");
        String username = "";
        MaintainerModel result = (MaintainerModel) instance.findUsername(username);
        assertNull(result);
    }

    /**
     * Test of listMaintainers method, of class MaintainerModel.
     */
    @Test
    public void testListMaintainers() { //supponendo che ci sia almeno un maintainer nella tabella 
        System.out.println("listMaintainers");
        List<MaintainerModel> expResult = new ArrayList<>();
        expResult.add(new MaintainerModel("tizio", "tizio"));
        List<MaintainerModel> result = instance.listUsers();
        assertEquals(expResult.isEmpty(), result.isEmpty()); // le due liste non sono vuote
    }

    /**
     * Test of deleteMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testDeleteMaintainer() { //mi aspetto true se ho cancellato
        System.out.println("deleteMaintainer");
        String username = "";
        boolean expResult = false; //perchè non posso avere un username vuoto
        boolean result = instance.deleteUser(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testDeleteMaintainer1() { //mi aspetto true se ho cancellato
        System.out.println("deleteMaintainer1");
        String username = "tizio";
        boolean result = instance.deleteUser(username);
        assertFalse(result);
    }

    /**
     * Test of deleteMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testDeleteMaintainer2() { //mi aspetto true se ho cancellato
        System.out.println("deleteMaintainer2");
        String username = "maintainer6";
        boolean result = instance.deleteUser(username);
        assertTrue(result);
    }

    /**
     * Test of createMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testCreateMaintainer() {
        System.out.println("createMaintainer");
        String username = "";
        String password = "";
        boolean result = instance.createUser(username, password);
        assertFalse(result);
    }

    /**
     * Test of createMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testCreateMaintainer1() {
        System.out.println("createMaintainer1");
        String username = "";
        String password = "passGigi";
        boolean result = instance.createUser(username, password);
        assertFalse(result);
    }

    /**
     * Test of createMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testCreateMaintainer2() { //non posso creare maintainer con password vuota
        System.out.println("createMaintainer2");
        String username = "dfg";
        String password = "";
        boolean result = instance.createUser(username, password);
        assertFalse(result);
    }

    /**
     * Test of createMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testCreateMaintainer3() {
        System.out.println("createMaintainer3");
        String username = "tizio";
        String password = "rizio";
        boolean result = instance.createUser(username, password);
        assertFalse(result);
    }

    /**
     * Test of createMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testCreateMaintainer4() {
        System.out.println("createMaintainer4");
        String username = "io";
        String password = "no";
        boolean result = instance.createUser(username, password);
        assertTrue(result);
    }

    /**
     * Test of updateMainatainerPassword method, of class MaintainerModel.
     */
    @Test
    public void testUpdateMainatainerPassword() {
        System.out.println("updateMainatainerPassword");
        String username = "";
        String newpass = "cfgvhjnk";
        boolean expResult = false;
        boolean result = instance.updateUserPassword(username, newpass);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMainatainerPassword method, of class MaintainerModel.
     */
    @Test
    public void testUpdateMainatainerPassword1() {
        System.out.println("updateMainatainerPassword1");
        String username = "tizio";
        String newpass = "cfgvhjnk";
        boolean result = instance.updateUserPassword(username, newpass);
        assertTrue(result);
    }

    @Test
    public void testAddCompetence1() {
        System.out.println("addCompetence");
        boolean result = instance.addCompetence("main", 23); //aggiungo una competenza che non ha 
        assertEquals(true, result);
    }
     @Test
    public void testAddCompetence2() {
        System.out.println("addCompetence");
        boolean result = instance.addCompetence("main", 0); //provo ad aggiungere una competenza che  ha già
        assertEquals(false, result);
    }
    /**
     * Test of hasCompetences method, of class MaintainerModel.
     */
    @Test
    public void testHasCompetenceSI () {
        System.out.println("hasCompetencesSI");
        assertEquals(true, instance.hasCompetences("main", 0)); 
    }
    
     @Test
    public void testHasCompetenceNO () {
        System.out.println("hasCompetencesNO");
        assertEquals(false,instance.hasCompetences("main", 9) ); 
    }

    /**
     * Test of removeCompetence method, of class MaintainerModel.
     */
    @Test
    public void testRemoveCompetence1() {
        System.out.println("removeCompetence1");
        assertEquals(true, instance.hasCompetences("main", 0)); //provo a rimuovere una competenza che ha. 
    }
    
    @Test
    public void testRemoveCompetence2(){
        System.out.println("removeCompetence2");
        assertEquals(false, instance.hasCompetences("main",9)); //provo a rimuovere una competenza che non ha.
    }
}
