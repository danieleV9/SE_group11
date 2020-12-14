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
import model.ProcedureModel;
import model.SkillModel;

/**
 *
 * @author HP
 */
public class ProcedureDao {

    private List<ProcedureModel> listProcedure;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    private String query;
    private static SkillModel skill;

    public List<ProcedureModel> getAllProcedures() {
        List<ProcedureModel> list = new ArrayList<>();
        try {
            con=ConnectionDatabase.getConnection();
            st = con.createStatement();
            query = "select * from procedure_manutenzione";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String nomeprocedura = rs.getString("nomeprocedura");
                String path = rs.getString("path");
                list.add(new ProcedureModel(nomeprocedura, path));
            }
        } catch (SQLException ex) {
            System.out.println("errore");
        }
        return list;
    }

    public boolean addCompetence(String nomeprocedura, int id) {
        try {
            con=ConnectionDatabase.getConnection();
            query = "insert into competenze_proc(idcompetenza,nomeprocedura) values(?,?)";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, nomeprocedura);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Errore nell'aggiunta nuova competenza" + ex);
            return false;
        }
    }

    public boolean removeCompetence(String nomeprocedura, int id) {
        try {
            con=ConnectionDatabase.getConnection();
            query = "delete from competenze_proc where idcompetenza=? and nomeprocedura=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, nomeprocedura);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Errore nell'eliminazione competenza" + ex);
            return false;
        }
    }

    public List<SkillModel> getProcedureSkill(String nomeprocedura) {
        List<SkillModel> list = new ArrayList<>();
        skill=new SkillModel(0,"");
        try {
            con=ConnectionDatabase.getConnection();
            query = "select * from competenze_proc where nomeprocedura=?";
            pst = con.prepareStatement(query);
            pst.setString(1, nomeprocedura);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idcompetenza");
                String description = skill.findSkill(id).getDescription();
                list.add(new SkillModel(id, description));
            }
        } catch (SQLException ex) {
            System.out.println("errore");
        }
        return list;
    }

    public boolean createProcedure(String nomeprocedura, String path) {
        ProcedureModel m = new ProcedureModel("","");
        if (m.proceduraExists(nomeprocedura)) {
            System.out.println("procedura già esistente");
            return false;
        } else {
            try {
                con=ConnectionDatabase.getConnection();
                query = "insert into procedure_manutenzione(nomeprocedura,path) values(?,?)";
                pst = con.prepareStatement(query);
                pst.setString(1, nomeprocedura);
                pst.setString(2, path);
                pst.executeUpdate();
                System.out.println("procedura creata con successo");
                return true;
            } catch (SQLException ex) {
                System.out.println("Errore nell'aggiunta nuova procedura ! " + ex);
                return false;
            }
        }
    }

    public boolean deleteProcedure(String name) {
       ProcedureModel m = new ProcedureModel("","");
       query = "delete from procedure_manutenzione where nomeprocedura=?";
       if (m.proceduraExists(name)==false) {
            System.out.println("procedura non esistente");
            return false;
        } else {
        try {
            con=ConnectionDatabase.getConnection();
            pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Errore nell'eliminazione");
            return false;
        }
      }
    }

    public String getPath(String name) {
        String path = null;
        try {
            con=ConnectionDatabase.getConnection();
            query = "select path from procedure_manutenzione where nomeprocedura=?";
            pst = con.prepareStatement(query);
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()) {
                path = rs.getString("path");
            }
        } catch (SQLException ex) {
            System.out.println("errore in getPath");
        }

        return path;
    }

    public boolean proceduraExists(String name) {
        try {
            con=ConnectionDatabase.getConnection();
            query = "select count(*) from procedure_manutenzione where nomeprocedura=?";
            pst = con.prepareStatement(query);
            pst.setString(1, name);
            rs = pst.executeQuery();
            int risultato = 0;
            if (rs.next()) {
                risultato = rs.getInt(1);
                if (risultato == 0) {
                    System.out.println("procedura non trovata");
                    return false;   
                } else {
                    System.out.println("procedura trovata");
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
        return false;
    }


}
