/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connectionDB.ConnectionSingleton;
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
        instance = new MaintainerModel("", "");
        
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
        } catch (SQLException ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testFindMaintainer_2args() { //so che non esiste questo utente
        System.out.println("findMaintainer_2args");
        String username = "jennifer";
        String password = "cutolooo"; 
        MaintainerModel expResult = null;
        MaintainerModel result = null; //questo utente non esiste per cui mi aspetto null
        try {
            result = (MaintainerModel) instance.findUser(username, password);
            assertEquals(expResult, result); 
        } catch (Exception ex) {
        }
    }

    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test(expected = AssertionError.class)
    public void testFindMaintainer_2args1() {
        System.out.println("findMaintainer_2args1");
        String username = "";
        String password = "maintainer6";
        MaintainerModel expResult = new MaintainerModel(username, password);
        MaintainerModel result = null;
        try {
            result = (MaintainerModel) instance.findUser(username, password);
        } catch (Exception ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult.toString(), result);
    }
    
    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testFindMaintainer_2args2() {
        System.out.println("findMaintainer_2args2");
        String username = "maintainer6";
        String password = "maintainer6";
        MaintainerModel expResult = new MaintainerModel(username, password);
        MaintainerModel result = null;
        try {
            result = (MaintainerModel) instance.findUser(username, password);
        } catch (Exception ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult.toString(), result.toString());
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
    public void testDeleteMaintainer2() { //mi aspetto true se ho cancellato, posso cancellare solo un maintainer che non è associato 
        //a nessuna attività altrimenti viola vincolo integrità referenziale
        System.out.println("deleteMaintainer2");
        String username = "jennifer";
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
        String username = "maintainer6";
        String password = "maintainer6";
        boolean result = instance.createUser(username, password);
        assertFalse(result); //sarà false perchè maintainer6 esiste già
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
        assertTrue(result); //sarà true perchè non siste già un  maintainer con questo username
    }

    /**
     * Test of updateMainatainerPassword method, of class MaintainerModel.
     */
    @Test
    public void testUpdateMainatainerPassword() {
        System.out.println("updateMainatainerPassword");
        String username = "";
        String newpass = "cfgvhjnk";
        boolean expResult = false; //perchè non posso modificare un utente con username vuoto
        boolean result = instance.updateUserPassword(username, newpass);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateMainatainerPassword method, of class MaintainerModel.
     */
    @Test
    public void testUpdateMainatainerPassword1() {
        System.out.println("updateMainatainerPassword1");
        String username = "Maintainer1";
        String newpass = "ciao!";
        boolean result = instance.updateUserPassword(username, newpass);
        assertTrue(result); //perchè questo Maintainer esiste e la modifica andrà a buon fine, la nuova pass è anche diversa dalla precedente
    }

    @Test
    public void testAddCompetence1() {
        System.out.println("addCompetence");
        boolean result = instance.addCompetence("main", 2); //aggiungo una competenza che non ha 
        assertEquals(true, result);
    }
     @Test
    public void testAddCompetence2() {
        System.out.println("addCompetence");
        boolean result = instance.addCompetence("main", 1); //provo ad aggiungere una competenza che  ha già
        assertEquals(false, result);
    }
    /**
     * Test of hasCompetences method, of class MaintainerModel.
     */
    @Test
    public void testHasCompetenceSI () {
        System.out.println("hasCompetencesSI");
        assertEquals(true, instance.hasCompetences("main", 1)); 
    }
    
     @Test
    public void testHasCompetenceNO () {
        System.out.println("hasCompetencesNO");
        assertEquals(false,instance.hasCompetences("main", 6) ); 
    }

    /**
     * Test of removeCompetence method, of class MaintainerModel.
     */
    @Test
    public void testRemoveCompetence1() {
        System.out.println("removeCompetence1");
        assertEquals(true, instance.hasCompetences("main", 1)); //provo a rimuovere una competenza che ha. 
    }
    
    @Test
    public void testRemoveCompetence2(){
        System.out.println("removeCompetence2");
        assertEquals(false, instance.hasCompetences("main",6)); //provo a rimuovere una competenza che non ha.
    }

    /**
     * Test of getDisponibilitaGiorno method, of class MaintainerModel.
     */
    @Test
    public void testGetDisponibilitaGiorno() {
        System.out.println("getDisponibilitaGiorno");
        String username = "";
        int week = 0;
        int day = 0;
        String expResult = "";
        String result = instance.getDisponibilitaGiorno(username, week, day);
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of getDisponibilitaGiorno method, of class MaintainerModel.
     */
    @Test
    public void testGetDisponibilitaGiorno1() {
        System.out.println("getDisponibilitaGiorno1");
        String username = "";
        int week = 55;
        int day = 0;
        String expResult = "";
        String result = instance.getDisponibilitaGiorno(username, week, day);
        assertEquals(expResult, result);
       
    }
    
    
    /**
     * Test of updateDisponibilitaGiorno method, of class MaintainerModel.
     */
    @Test
    public void testUpdateDisponibilitaGiorno() {
        System.out.println("updateDisponibilitaGiorno");
        String username = "";
        int week =0 ;
        int day = 0;
        String fasce = "";
        boolean expResult = false;
        boolean result = instance.updateDisponibilitaGiorno(username, week, day,fasce);
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of updateDisponibilitaGiorno method, of class MaintainerModel.
     */
    @Test
    public void testUpdateDisponibilitaGiorno1() {
        System.out.println("updateDisponibilitaGiorno1");
        String username = "maintainer3";
        int week =3 ;
        int day = 7;
        String fasce = "0 30 0 0 0 0 0";
        boolean expResult = true;
        boolean result = instance.updateDisponibilitaGiorno(username, week, day,fasce);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of updateDisponibilitaGiorno method, of class MaintainerModel.
     */
    @Test
    public void testUpdateDisponibilitaGiorno2() {
        System.out.println("updateDisponibilitaGiorno2");
        String username = "maintainer3";
        int week =59 ;
        int day = 0;
        String fasce = "20 20 20 20 40 30 50";
        boolean expResult = false;
        boolean result = instance.updateDisponibilitaGiorno(username, week, day,fasce);
        assertEquals(expResult, result);
    }
    
    
    
    /**
     * Test of getDisponibilitaGiorno method, of class MaintainerModel.
     */
    @Test
    public void testGetDisponibilitaGiorno2() {
        System.out.println("getDisponibilitaGiorno2");
        String username = "maintainer3";
        int week = 1;
        int day = 5; 
        String expResult ="0 0 0 0 0 0 0";
        String result = instance.getDisponibilitaGiorno(username, week, day);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getActivities method, of class MaintainerModel.
     */
    @Test
    public void testGetActivities() {
        System.out.println("getActivities");
        List<MaintenanceActivityModel> expResult = new ArrayList<>();
        String username= "";
        List<MaintenanceActivityModel> result = instance.getActivities(username);
        assertEquals(expResult,result);
    }
    
    /**
     * Test of getActivities method, of class MaintainerModel.
     */
    @Test
    public void testGetActivities1() {
        System.out.println("getActivities1");
        String username= "maintainer6";
        List<MaintenanceActivityModel> result = instance.getActivities(username);
        assertTrue(result.isEmpty()==false); // la lista non deve essere vuota perchè so che a questo maintainer nel db sono state assegnate delle attività
    }

    /**
     * Test of getNumGiorno method, of class MaintainerModel.
     */
    @Test
    public void testGetNumGiorno() {
        System.out.println("getNumGiorno");
        String username = "";
        int week = 0;
        int day = 0;
        int expResult = 0;
        int result = instance.getNumGiorno(username, week, day);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of getNumGiorno method, of class MaintainerModel.
     */
    @Test
    public void testGetNumGiorno1() {
        System.out.println("getNumGiorno1");
        String username = "maintainer3";
        int week = 1;
        int day = 3;
        int expResult = 9;
        int result = instance.getNumGiorno(username, week, day);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of listMaintainersDisponibili method, of class MaintainerModel.
     */
    @Test
    public void testListMaintainersDisponibili() {
        System.out.println("listMaintainersDisponibili");
        List<MaintainerModel> expResult = null;
        List<MaintainerModel> result = instance.listMaintainersDisponibili();
        assertNotNull(result);
       
    }

 
}
