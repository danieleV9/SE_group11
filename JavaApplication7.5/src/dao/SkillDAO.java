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

    private static Connection con = ConnectionDatabase.getConnection();
    private static ResultSet rs;
    private static Statement st;
    private static PreparedStatement pst;
    private static SkillModel skill;

    public static List<SkillModel> listSkills() {
        List<SkillModel> list = new ArrayList<>();
        try {
            String query = "select * from competenze";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idSkill = rs.getInt(1);
                String description = rs.getString("descrizione");
                skill = new SkillModel(idSkill, description);
                list.add(skill);
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return list;
    }

    public static boolean deleteSkill(int idSkill) {
        if (idSkill == 0) {
            return false;
        }
        try {
            String query = "delete from competenze where idcompetenza=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, idSkill);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public static boolean modifySkill(int idSkill, String description) {
        try {
            String query = "update competenze set descrizione=? where idcompetenza=?";
            pst = con.prepareStatement(query);
            pst.setInt(2, idSkill);
            pst.setString(1, description);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public static boolean insertSkill(String description) {
        try {
            String query = "INSERT INTO COMPETENZE (idcompetenza,descrizione) values ((NEXTVAL(idcompetenza)+1),?)";
            pst = con.prepareStatement(query);
            pst.setString(1, description);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Errore nell'inserimento della competenza");
            return false;
        }
        return true;
    }
    //RESTITUISCE LA LISTA DI SKILL DI UN DETERMINATO MAINTAINER

    public static List<SkillModel> listSkillsMA(String username) {
        List<SkillModel> list = new ArrayList<>();
        try {
            String query = "select * from competenze_ma where usernamema=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            while (rs.next()) {
                int idComp = rs.getInt("idcompetenza");
                String description = skill.findSkill(idComp).getDescription();
                list.add(new SkillModel(idComp, description));
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return list;
    }

    public static SkillModel findSkill(int id) {
        if (id != 0) {
            try {
                String query = "select * from competenze where idcompetenza=?";
                pst = con.prepareStatement(query);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String description = rs.getString("descrizione");
                    skill = new SkillModel(id, description);
                }
                return skill;
            } catch (SQLException ex) {
                return skill;
            }
        } else {
            return skill;
        }
    }

    public static SkillModel findSkill(String description) {
        if (description != null) {
            try {
                String query = "select * from competenze where descrizione=?";
                pst = con.prepareStatement(query);
                pst.setString(1, description);
                rs = pst.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("idcompetenza");
                    skill = new SkillModel(id, description);
                }
                return skill;
            } catch (SQLException ex) {
                return skill;
            }
        } else {
            return skill;
        }
    }

    public static Connection getConnection() {
        return con = ConnectionDatabase.getConnection();
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("CHIUSURA DEL DATABASE FALLITA.");
            System.err.println(e.getMessage());
        }
    }
}
