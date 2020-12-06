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

    Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;

    //prepareStatement quando l'utente deve inserire qualcosa
    //Statement non posso mettere i punti interrogativi
    //createStatement
    public List<SkillModel> listSkills() {
        List<SkillModel> list = new ArrayList<>();
        try {
            con = ConnectionDatabase.getConnection();
            String query = "select * from competenze";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int idSkill = rs.getInt(1);
                String description = rs.getString("descrizione");
                SkillModel skill = new SkillModel(idSkill, description);
                list.add(skill);
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        return list;
    }

    public boolean deleteSkill(int idSkill) {
        if (idSkill == 0) {
            return false;
        }
        try {
            con = ConnectionDatabase.getConnection();
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

    public boolean modifySkill(int idSkill, String description) {
        try {
            con = ConnectionDatabase.getConnection();
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

    public void insertSkill(String description) {
        try {
            con = ConnectionDatabase.getConnection();
            String query = "INSERT INTO COMPETENZE (idcompetenza,descrizione) values ((NEXTVAL(idcompetenza)+1),?)";
            pst = con.prepareStatement(query);
            pst.setString(1, description);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Errore nell'inserimento della competenza");
        }
    }
}
