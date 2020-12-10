/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class MaintenanceActivityModelTest {
    
    public MaintenanceActivityModelTest() {
    }
    
    

    /**
     * Test of getWeekNum method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetWeekNum() {
        System.out.println("getWeekNum");
        MaintenanceActivityModel instance = new MaintenanceActivityModel(3,0,"","","","",0,"");
        int expResult = 3;
        assertEquals(expResult, instance.getWeekNum());
        
    }

    /**
     * Test of setWeekNum method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetWeekNum() {
        System.out.println("setWeekNum");
        int WeekNum = 0;
        int expResult= WeekNum;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setWeekNum(WeekNum);
        assertEquals(expResult,instance.getWeekNum());
    }

    /**
     * Test of isInteruptible method, of class MaintenanceActivityModel.
     */
    @Test
    public void testIsInteruptible() {
        System.out.println("isInteruptible");
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",1,"",null,null,"","");
        boolean expResult = false;
        assertEquals(expResult,instance.isInteruptible());
        
    }

    /**
     * Test of setInteruptible method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetInteruptible() {
        System.out.println("setInteruptible");
        boolean interuptible = false;
        boolean expResult = interuptible;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setInteruptible(interuptible);
        assertEquals(expResult,instance.isInteruptible());
    }

    /**
     * Test of getId_Activity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetId_Activity() {
        System.out.println("getId_Activity");
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",1,"",null,null,"","");
        int expResult = 1;
        assertEquals(expResult, instance.getId_Activity());
    }

    /**
     * Test of setId_Activity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetId_Activity() {
        System.out.println("setId_Activity");
        int id_Activity = 0;
        int expResult = id_Activity;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setId_Activity(id_Activity);
        assertEquals(expResult, instance.getId_Activity());
    }

    /**
     * Test of getDescription method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String descrizione = "Questa è la descrizione";
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,descrizione,"","",1,"",null,null,"","");
        String expResult = descrizione;  
        assertEquals(expResult, instance.getDescription());
       
    }

    /**
     * Test of setDescription method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Questa è la descrizione";
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of getTipology method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetTipology() {
        System.out.println("getTipology");
        String tipology="meccanica";
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"",tipology,"",1,"",null,null,"","");
        String expResult = tipology;
        assertEquals(expResult, instance.getTipology());
       
    }

    /**
     * Test of setTipology method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetTipology() {
        System.out.println("setTipology");
        String tipology = "meccanica";
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setTipology(tipology);
        assertEquals(tipology,instance.getTipology());
    }

    /**
     * Test of getType method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String type = "pianificata";
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","",type,1,"",null,null,"","");
        String expResult = type;
        assertEquals(expResult,instance.getType() );
        
    }

    /**
     * Test of setType method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "pianificata";
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setType(type);
        assertEquals(type, instance.getType());
    }

    /**
     * Test of getEstimatedTime method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetEstimatedTime() {
        System.out.println("getEstimatedTime");
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",120,"",null,null,"","");
        int expResult = 120;
        assertEquals(expResult,instance.getEstimatedTime());
        
    }

    /**
     * Test of setEstimatedTime method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetEstimatedTime() {
        System.out.println("setEstimatedTime");
        int EstimatedTime = 120;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setEstimatedTime(EstimatedTime);
        assertEquals(EstimatedTime,instance.getEstimatedTime());
    }

    /**
     * Test of getWorkspaceNotes method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetWorkspaceNotes() {
        System.out.println("getWorkspaceNotes");
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",1,"Queste sono note lavoro.",null,null,"","");
        String expResult = "Queste sono note lavoro.";
        assertEquals(expResult,instance.getWorkspaceNotes());   
    }

    /**
     * Test of setWorkspaceNotes method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetWorkspaceNotes() {
        System.out.println("setWorkspaceNotes");
        String workspaceNotes = "Queste sono note lavoro";
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setWorkspaceNotes(workspaceNotes);
        assertEquals(workspaceNotes, instance.getWorkspaceNotes());
    }

    /**
     * Test of getProcedura method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetProcedura() {
        System.out.println("getProcedura");
        Procedure p = new Procedure();
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",1,"",p,null,"","");
        Procedure expResult = p;
        assertEquals(expResult,instance.getProcedura());
        
    }

    /**
     * Test of setProcedura method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetProcedura() {
        System.out.println("setProcedura");
        Procedure procedura = new Procedure();
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setProcedura(procedura);
        assertEquals(procedura, instance.getProcedura());
        
    }

    /**
     * Test of getMateriali method, of class MaintenanceActivityModel.
     * 
     */
    
    @Test
    public void testGetMateriali() {
        System.out.println("getMateriali"); 
        List<Materiali> lm = new ArrayList<>();
        lm.add(new Materiali());
        lm.add(new Materiali());
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",1,"",null,lm,"","");  
        assertEquals(lm, instance.getMateriali());
        
    }

    /**
     * Test of setMateriali method, of class MaintenanceActivityModel.
     */
    
    @Test
    public void testSetMateriali() {
        System.out.println("setMateriali");
        List<Materiali> materiali = new ArrayList<>();
        materiali.add(new Materiali());
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setMateriali(materiali);
        assertEquals(materiali, instance.getMateriali());
    }

    /**
     * Test of getArea method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        String area = "Unisa";
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",1,"",null,null,area,"");
        String expResult = area;
        assertEquals(expResult, instance.getArea());
        
    }

    /**
     * Test of setArea method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetArea() {
        System.out.println("setArea");
        String area = "Unisa";
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setArea(area);
        assertEquals(area, instance.getArea());
    }

    
     /**
     * Test of getFabbrica method, of class MaintenanceActivityModel.
     */
    @Test
    public void testGetFabbrica() {
        System.out.println("getFabbrica");
        String fabbrica = "Ingegneria";
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,1,"","","",1,"",null,null,"",fabbrica);
        String expResult = fabbrica;
        assertEquals(expResult, instance.getFabbrica());
        
    }
    
    
    /**
     * Test of setFabbrica method, of class MaintenanceActivityModel.
     */
    @Test
    public void testSetFabbrica() {
        System.out.println("setFabbrica");
        String fabbrica = "Nusco";
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        instance.setFabbrica(fabbrica);
        assertEquals(fabbrica, instance.getFabbrica());
    }
    /**
     * Test of deleteActivity method, of class MaintenanceActivityModel.
     */
    
    //caso in cui l'attivitÃ  da eliminare non esiste
    @Test
    public void testDeleteActivity1() {
        System.out.println("deleteActivity");
        int id = 5;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        boolean expResult = false;
        boolean result = instance.deleteActivity(id);
        assertEquals(expResult, result); 
    }

    //caso in cui l'attivitÃ  da eliminare  esiste
    @Test
    public void testDeleteActivity2() {
        System.out.println("deleteActivity");
        int id = 8;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
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
        MaintenanceActivityModel instance = new MaintenanceActivityModel();     
        MaintenanceActivityModel expResult = null; 
        MaintenanceActivityModel result = instance.viewActivity(id);
        assertEquals(expResult, result);

    }
    
    //Caso in cui l'attivitÃ  con id=3  esiste 
    @Test
    public void testViewActivity2() {
        System.out.println("viewActivity");
        int id = 3;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();     
        MaintenanceActivityModel expResult = null; 
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
        int id = 0;
        MaintenanceActivityModel instance = new MaintenanceActivityModel(1,false,id,"","","",1,note,null,null,"","");
        instance.aggiornaNote(note, id);
        assertEquals(note, instance.getWorkspaceNotes());
    }

 
    /**
     * Test of getAllActivity method, of class MaintenanceActivityModel.
     */
    
    @Test
    public void testGetAllActivity() {
        System.out.println("getAllActivity");
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        List<MaintenanceActivityModel> expResult = null;
        List<MaintenanceActivityModel> result = instance.getAllActivity();
        assertNotEquals(expResult, result);
        
    }
    
    /**
     * Test of insertActivity method, of class MaintenanceActivityModel.
     */
    @Test
    public void testInsertActivity(){
        System.out.println("insertActivity");
        int numberWeek = 10;
        String workNotes = "";
        String type = "pianificata";
        String factory = "Fisciano";
        String area = "Carpenteria";
        String tipology = "elettronica";
        int time = 10;
        String description = "Conoscenza del robot XP";
        boolean expResult = true;
        MaintenanceActivityModel instance = new MaintenanceActivityModel();
        boolean result = instance.insertActivity(numberWeek, workNotes, type, factory, tipology, time, description, area);
        assertEquals(expResult,result);
    }
    
}

