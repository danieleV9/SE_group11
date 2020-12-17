/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connectionDB.ConnectionSingleton;
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

    private Connection conn;
    private ResultSet rs;
    private Statement st;
    private PreparedStatement pst;
    private MaterialModel material;

    public MaterialDAO() {
       
    }

    public boolean deleteMaterial(String nomeMaterial) {
        if (nomeMaterial.equals("")) {
            return false;
        }
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "delete from materiali where nomemateriale=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, nomeMaterial);
            int res=pst.executeUpdate();
            if(res==1)
                return true;
            else 
                return false;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            return false;
        }
        finally {
            //try {rs.close();} catch (SQLException e) {}
            try {pst.close();} catch (SQLException e) {}
            //try {conn.close();} catch (SQLException e) {}
        }
    }

public boolean insertMaterial(String materialName,int idattivita) {
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "INSERT INTO materiali_per_attivita(idattivita, nomemateriale) values (?,?)";
            pst = conn.prepareStatement(query);
            pst.setInt(1, idattivita);
            pst.setString(2, materialName);
            int res=pst.executeUpdate();
            if(res==1)
                return true;
            else 
                return false;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }finally {
            //try {rs.close();} catch (SQLException e) {}
            try {pst.close();} catch (SQLException e) {}
            //try {conn.close();} catch (SQLException e) {}
        }
    }

    public List<MaterialModel> getAllMaterials() {
        List<MaterialModel> list = new ArrayList<>();
        try {
            conn =ConnectionSingleton.getInstance();
            st = conn.createStatement();
            String query = "select * from materiali";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String materialName = rs.getString("nomemateriale");
                list.add( new MaterialModel(materialName));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try {rs.close();} catch (SQLException e) {}
            //try {pst.close();} catch (SQLException e) {}
            //try {conn.close();} catch (SQLException e) {}
        }
        return list;
    }

}
