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

    private Connection conn;
    private static Statement st;
    private static ResultSet rs;
    private static PreparedStatement pst;
    
    
    public ProcedureDao() {
        conn=ConnectionDatabase.getConnection();
    }
   

    public List<ProcedureModel> getAllProcedures() {
        List<ProcedureModel> list = new ArrayList<>();
        try {
            st = conn.createStatement();
            String query = "select * from procedure_manutenzione";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String nomeprocedura = rs.getString("nomeprocedura");
                String path = rs.getString("path");
                list.add(new ProcedureModel(nomeprocedura, path));
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

    public boolean addCompetence(String nomeprocedura, int id) {
      
      if(this.hasCompetence(nomeprocedura, id)){
          return false;
      }
      else {
            try {
            String query = "insert into competenze_proc(idcompetenza,nomeprocedura) values(?,?)";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, nomeprocedura);
            int res = pst.executeUpdate();
            if(res == 1)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println("Errore nell'aggiunta nuova competenza" + ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
      }      
            
    }

    public boolean removeCompetence(String nomeprocedura, int id) {
        try {
            
            String query = "delete from competenze_proc where idcompetenza=? and nomeprocedura=?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, nomeprocedura);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Errore nell'eliminazione competenza" + ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public List<SkillModel> getProcedureSkill(String nomeprocedura) {
        List<SkillModel> list = new ArrayList<>();
        SkillModel skill=new SkillModel(0,"");
        try {
            
            String query = "select * from competenze_proc where nomeprocedura=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, nomeprocedura);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idcompetenza");
                String description = skill.findSkill(id).getDescription();
                list.add(new SkillModel(id, description));
            }
        } catch (SQLException ex) {
            System.out.println("errore, "+ ex.getMessage());
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return list;
    }

    public boolean createProcedure(String nomeprocedura, String path) {
        if (this.proceduraExists(nomeprocedura)) {
            System.out.println("procedura già esistente");
            return false;
        } else {
            try {
                String query = "insert into procedure_manutenzione(nomeprocedura,path) values(?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, nomeprocedura);
                pst.setString(2, path);
                pst.executeUpdate();
                System.out.println("procedura creata con successo");
                return true;
            } catch (SQLException ex) {
                System.out.println("Errore nell'aggiunta nuova procedura ! " + ex.getMessage());
                return false;
            } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        }
    }

    public boolean deleteProcedure(String name) {
       
       String query = "delete from procedure_manutenzione where nomeprocedura=?";
       if (this.proceduraExists(name)==false) {
            System.out.println("procedura non esistente");
            return false;
        } else {
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
      }
    }

    public String getPath(String name) {
        if(name==null)
            return null;
        String path = null;
        try {
            String query = "select path from procedure_manutenzione where nomeprocedura=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()) {
                path = rs.getString("path");
            }
        } catch (SQLException ex) {
            System.out.println("errore in getPath");
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }

        return path;
    }

    public boolean proceduraExists(String name) {
        try {
            String query = "select count(*) from procedure_manutenzione where nomeprocedura=?";
            pst = conn.prepareStatement(query);
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
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return false;
    }

    public boolean hasCompetence(String nomeprocedura, int idcompetenza){
        String query="select count(*) from competenze_proc where nomeprocedura=? and idcompetenza=?";
        try{
            pst = conn.prepareStatement(query);
            pst.setString(1,nomeprocedura);
            pst.setInt(2,idcompetenza);
            rs=pst.executeQuery();
            if(rs.next()){
               if(rs.getInt(1)==0){
                   return false;
               }
               else return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return false;
    } 

    public Connection getConn() {
        return conn;
    }
    

}
