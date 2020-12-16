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
        conn = ConnectionDatabase.getConnection();
        
    }


    public List<SkillModel> listSkills() {
        List<SkillModel> list = new ArrayList<>();
        SkillModel skill=new SkillModel(0,"");
        try {
            String query = "select * from competenze";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idSkill = rs.getInt(1);
                String description = rs.getString("descrizione");
                skill = new SkillModel(idSkill, description);
                list.add(skill);
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
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
            String query = "delete from competenze where idcompetenza=?";
            
            pst = conn.prepareStatement(query);
            pst.setInt(1, idSkill);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public boolean modifySkill(int idSkill, String description) {
        try {
            String query = "update competenze set descrizione=? where idcompetenza=?";
            
            pst = conn.prepareStatement(query);
            pst.setInt(2, idSkill);
            pst.setString(1, description);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public boolean insertSkill(String description) {
        try {
            String query = "INSERT INTO COMPETENZE (idcompetenza,descrizione) values ((NEXTVAL(idcompetenza)),?)";
            
            pst = conn.prepareStatement(query);
            pst.setString(1, description);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Errore nell'inserimento della competenza");
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return true;
    }
    //RESTITUISCE LA LISTA DI SKILL DI UN DETERMINATO MAINTAINER

    public List<SkillModel> listSkillsMA(String username) {
        if(username.equals("") || username==null)
            return null;
        SkillModel skill=new SkillModel(0,"");
        List<SkillModel> list = new ArrayList<>();
        try {
            String query = "select * from competenze_ma where usernamema=?";
            
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            while (rs.next()) {
                int idComp = rs.getInt("idcompetenza");
                String description= skill.findSkill(idComp).getDescription();
                list.add(new SkillModel(idComp, description));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return list;
    }

    public SkillModel findSkill(int id) {
        SkillModel skill=new SkillModel(0,"");
        if (id != 0) {
            try {
                String query = "select * from competenze where idcompetenza=?";
                
                pst = conn.prepareStatement(query);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String description = rs.getString("descrizione");
                    skill = new SkillModel(id, description);
                }
                return skill;
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
        SkillModel skill=new SkillModel(0,"");
        if (description != null && !description.equals("")) {
            try {
                String query = "select * from competenze where descrizione=?";
                
                pst = conn.prepareStatement(query);
                pst.setString(1, description);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("idcompetenza");
                    skill = new SkillModel(id, description);
                }
                return skill;
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

    public Connection getConn() {
        return conn;
    }

   
}
