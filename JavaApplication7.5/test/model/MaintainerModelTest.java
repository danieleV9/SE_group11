/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


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
    MaintainerModel instance = null;
        
  
    public MaintainerModelTest() {
        
    }
    @Before
    public void newMM(){
        instance= new MaintainerModel("","");
    }
    
   /* @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }*/

    
    public void addMM(){
       MaintainerModel res= new MaintainerModel("","");
       res= instance.findMaintainer("tizio");
       if(res == null)
             instance.createMaintainer("tizio", "tizio");  
       else 
       {
           delMM();
           instance.createMaintainer("tizio", "tizio");
       }
    }
    
    public void delMM(){
        instance.deleteMaintainer("tizio");
    }
    /**
     * Test of getUsername method, of class MaintainerModel.
     */
    @Test
    public void testGetUsername() { 
        System.out.println("getUsername");
        instance= new MaintainerModel("anna","");
        String expResult = "anna";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        
    }


    /**
     * Test of getPassword method, of class MaintainerModel.
     */
    @Test 
    public void testGetPassword() {
        System.out.println("getPassword");
        instance= new MaintainerModel("","pass");
        String expResult = "pass";
        String result = instance.getPassword();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setUsername method, of class MaintainerModel.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "user1";
        instance.setUsername(username);
        assertEquals("user1",instance.getUsername());
    }

    /**
     * Test of setPassword method, of class MaintainerModel.
     */
    @Test(expected=AssertionError.class)
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        instance= new MaintainerModel("","pass");
        instance.setPassword(password);
        assertEquals("pass",instance.getPassword()); //mi aspetto che la password sia pass nel caso in cui il metodo fallisca
    }

    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testFindMaintainer_3args() { //supponendo che esista
        System.out.println("findMaintainer_3args");
        String username = "tizio";
        String password = "tizio";
        addMM();
        String role = "Maintainer";
        MaintainerModel expResult = new MaintainerModel(username,password);
        MaintainerModel result = null;
        try {
            result = instance.findMaintainer(username, password, role);
            assertEquals("Maintainer non trovato",expResult.toString(),result.toString()); //deve stampare messaggio se test fallisce
        } catch (Exception ex) {
     
        }
    }
    
    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test (expected=AssertionError.class)
    public void testFindMaintainer_3args1() {
        System.out.println("findMaintainer_3args1");
        String username = "";
        String password = "maintainer6";
        String role = "Maintainer";
        MaintainerModel expResult = new MaintainerModel(username,password);
        System.out.println(expResult.toString());
        MaintainerModel result = null;
        try {
            result = instance.findMaintainer(username, password, role);
        } catch (Exception ex) {
            Logger.getLogger(MaintainerModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult.toString(),result); 
        
       
    }

    /**
     * Test of findMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testFindMaintainer_String() { //un username vuoto non può esserci nel DB quindi non avrò nessun riscontro
        System.out.println("findMaintainer");
        String username = "";
        //MaintainerModel expResult = null;
        MaintainerModel result = instance.findMaintainer(username); 
        assertNull(result);  
    }

    /**
     * Test of listMaintainers method, of class MaintainerModel.
     */
    @Test
    public void testListMaintainers() { //supponendo che ci sia almeno un maintainer nella tabella 
        System.out.println("listMaintainers");
        List<MaintainerModel> expResult = new ArrayList<>();
        expResult.add(new MaintainerModel("tizio","tizio"));
        addMM();
        List<MaintainerModel> result = instance.listMaintainers();
        assertEquals(expResult.isEmpty(),result.isEmpty()); // le due liste non sono vuote
        
    }

    /**
     * Test of deleteMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testDeleteMaintainer() { //mi aspetto true se ho cancellato
        System.out.println("deleteMaintainer");
        String username = "";
        boolean expResult = false; //perchè non posso avere un username vuoto
        boolean result = instance.deleteMaintainer(username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of deleteMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testDeleteMaintainer1() { //mi aspetto true se ho cancellato
        System.out.println("deleteMaintainer1");
        delMM();
        String username = "tizio";
        boolean result = instance.deleteMaintainer(username);
        assertFalse(result);
    }
    
    /**
     * Test of deleteMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testDeleteMaintainer2() { //mi aspetto true se ho cancellato
        System.out.println("deleteMaintainer2");
        addMM();
        String username = "tizio";
        boolean result = instance.deleteMaintainer(username);
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
        boolean result = instance.createMaintainer(username, password);
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
        boolean result = instance.createMaintainer(username, password);
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
        boolean result = instance.createMaintainer(username, password);
        assertFalse(result);
    }
    
     /**
     * Test of createMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testCreateMaintainer3() {
        System.out.println("createMaintainer3");
        this.addMM();
        String username = "tizio";
        String password = "rizio";
        boolean result = instance.createMaintainer(username, password);
        assertFalse(result);
    }
    
     /**
     * Test of createMaintainer method, of class MaintainerModel.
     */
    @Test
    public void testCreateMaintainer4() {
        System.out.println("createMaintainer4");
        this.delMM();
        String username = "tizio";
        String password = "tizio";
        boolean result = instance.createMaintainer(username, password);
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
        boolean result = instance.updateMainatainerPassword(username, newpass);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of updateMainatainerPassword method, of class MaintainerModel.
     */
    @Test
    public void testUpdateMainatainerPassword1() {
        System.out.println("updateMainatainerPassword1");
        this.addMM();
        String username = "tizio";
        String newpass = "cfgvhjnk";
        boolean result = instance.updateMainatainerPassword(username, newpass);
        assertTrue(result);
        
    }
    
}