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
 * @author lyuba
 */
public class MaterialModelTest {

    private static MaterialModel instance;
    private static Connection connection;

    public MaterialModelTest() {
    }

    @AfterClass
    public static void afterclass() {
        try {

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void beforeclass() {
        connection = ConnectionSingleton.getInstance();
    }

    @Before
    public void setUp() {
        instance = new MaterialModel("");
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(MaterialModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(MaterialModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Wrong test of listSkills method, of class SkillModel.
     */
    @Test(expected = AssertionError.class)
    public void testListMaterials() {
        System.out.println("listMaterials");
        List<MaterialModel> expResult = null;
        List<MaterialModel> result = instance.listMaterials();
        assertEquals(expResult, result);
    }

    /**
     * Test of insertMaterial method, of class MaterialModel.
     */
    @Test
    public void testInsertMaterial() {
        System.out.println("insertMaterial");
        String materialName = "rame";
        int idattivita = 12;
        boolean expResult = true;
        boolean result = instance.insertMaterial(materialName, idattivita);
        assertEquals(expResult, result);
    }

    /**
     * Wrong test of insertMaterial method, of class MaterialModel.
     */
    @Test
    public void testInsertMaterial1() {
        System.out.println("insertMaterial1");
        String materialName = "ferro";
        int idattivita = 7020; // QUESTO ID NON ESISTE
        boolean expResult = false;
        boolean result = instance.insertMaterial(materialName, idattivita);
        assertEquals(expResult, result);
    }
}
