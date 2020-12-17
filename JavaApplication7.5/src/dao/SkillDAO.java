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
import model.SkillModel;

/**
 *
 * @author jenni
 */
public class SkillDAO {
    private Connection conn ;
    boolean stateCon;
    private ResultSet rs;
    private Statement st;
    private PreparedStatement pst;

    public SkillDAO() {

    }


    public List<SkillModel> listSkills() {
        List<SkillModel> list = new ArrayList<>();
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "select * from competenze";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idSkill = rs.getInt(1);
                String description = rs.getString("descrizione");
                list.add(new SkillModel(idSkill, description));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { st.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return list;
    }

    public boolean deleteSkill(int idSkill) {
        if (idSkill == 0) {
            return false;
        }
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "delete from competenze where idcompetenza=?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, idSkill);
            int res = pst.executeUpdate();
            if(res == 1)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public boolean modifySkill(int idSkill, String description) {
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "update competenze set descrizione=? where idcompetenza=?";
            pst = conn.prepareStatement(query);
            pst.setInt(2, idSkill);
            pst.setString(1, description);
            int res = pst.executeUpdate();
            if(res == 1)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public boolean insertSkill(String description) {
        try {
            String query = "INSERT INTO COMPETENZE (idcompetenza,descrizione) values ((NEXTVAL(idcompetenza)),?)";
            conn =ConnectionSingleton.getInstance();
            pst = conn.prepareStatement(query);
            pst.setString(1, description);
            int res = pst.executeUpdate();
            if(res == 1)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }
    //RESTITUISCE LA LISTA DI SKILL DI UN DETERMINATO MAINTAINER

    public List<SkillModel> listSkillsMA(String username) {
        if(username.equals("") || username==null)
            return null;
        
        List<SkillModel> list = new ArrayList<>();
        try {
            String query = "select * from competenze_ma where usernamema=?";
            conn =ConnectionSingleton.getInstance();
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            while (rs.next()) {
                int idComp = rs.getInt("idcompetenza");
                String description= this.findSkill(idComp).getDescription();
                list.add(new SkillModel(idComp, description));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return list;
    }

    public SkillModel findSkill(int id) {
        
        if (id != 0) {
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select * from competenze where idcompetenza=?";
                String description="";
                pst = conn.prepareStatement(query);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    description = rs.getString("descrizione");
                }
                return new SkillModel(id, description);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        } else {
            return null;
        }
    }

    public SkillModel findSkill(String description) {
        
        if (description != null && !description.equals("")) {
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select * from competenze where descrizione=?";
                int id = 0;
                pst = conn.prepareStatement(query);
                pst.setString(1, description);
                rs = pst.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("idcompetenza");
                }
                return new SkillModel(id, description);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        } else {
            return null;
        }
    }

   
}
