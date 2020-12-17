/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connectionDB.ConnectionDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.MaterialModel;

/**
 *
 * @author jenni
 */
public class MaterialDAO {

    private Connection con;
    private ResultSet rs;
    private Statement st;
    private PreparedStatement pst;
    private String query;
    private MaterialModel material;

    public MaterialDAO() {
        con = ConnectionDatabase.getConnection();
        material = new MaterialModel("");
    }

    public boolean deleteMaterial(String nomeMaterial) {
        if (nomeMaterial.equals("")) {
            return false;
        }
        try {
            query = "delete from materiali where nomemateriale=?";
            pst = con.prepareStatement(query);
            pst.setString(1, nomeMaterial);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
        /*finally {
            try {
                pst.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }*/
    }

public boolean insertMaterial(String materialName,int idattivita) {
        try {
            query = "INSERT INTO materiali_per_attivita(idattivita, nomemateriale) values (?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, idattivita);
            pst.setString(2, materialName);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public List<MaterialModel> getAllMaterials() {
        List<MaterialModel> list = new ArrayList<>();
        try {
            con = ConnectionDatabase.getConnection();
            st = con.createStatement();
            String query = "select * from materiali_per_attivita";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String materialName = rs.getString("nomemateriale");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /*finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                pst.close();
            } catch (SQLException e) {
            }
            try {
                con.close();
            } catch (SQLException e) {
            }
        }*/
        return list;
    }

    public Connection getConn() {
        return con;
    }
}
