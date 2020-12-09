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
        private Connection conn;
        private Statement st;
        private ResultSet rs;
        private PreparedStatement pst;


     public List<ProcedureModel> getAllProcedures() {      
        List<ProcedureModel> list = new ArrayList<>();
        try { 
            conn = ConnectionDatabase.getConnection();
            st= conn.createStatement();  
            String query = "select * from procedure_manutenzione";
            rs= st.executeQuery(query);
            while(rs.next()){
            String nomeprocedura = rs.getString("nomeprocedura");
            int id = rs.getInt("idfile");
            list.add(new ProcedureModel(id,nomeprocedura));
            }
       } catch (SQLException ex) {
            System.out.println("errore");
       } 
        return list;
    }
     
       public boolean addCompetence(String nomeprocedura, int id){
        try{
            conn = ConnectionDatabase.getConnection();
            String query = "insert into competenze_proc(idcompetenza,nomeprocedura) values(?,?)";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2,nomeprocedura);
            pst.executeUpdate();
            return true;
        }catch(SQLException ex){
           System.out.println("Errore nell'aggiunta nuova competenza"+ex);
           return false;
        }
    }
       
        public boolean removeCompetence(String nomeprocedura, int id){
        try{
            conn = ConnectionDatabase.getConnection();
            String query = "delete from competenze_proc where idcompetenza=? and nomeprocedura=?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2,nomeprocedura);
            pst.executeUpdate();
            return true;
        }catch(SQLException ex){
           System.out.println("Errore nell'eliminazione competenza"+ex);
           return false;
        }
    }
      
        
     
    public List<SkillModel> getProcedureSkill(String nomeprocedura) {      
        List<SkillModel> list = new ArrayList<>();
        SkillModel skill = new SkillModel();
        try { 
            conn = ConnectionDatabase.getConnection();
            String query = "select * from competenze_proc where nomeprocedura=?";
            pst= conn.prepareStatement(query);
            pst.setString(1, nomeprocedura);
            rs= pst.executeQuery();
            while(rs.next()){
            int id = rs.getInt("idcompetenza");
            String description = skill.getDescription(id);
            list.add(new SkillModel(id,description));
            }
         } catch (SQLException ex) {
            System.out.println("errore");
        } 
         return list;
    }
     
    public boolean createProcedure(String nomeprocedura){
      try{
         conn = ConnectionDatabase.getConnection();
         String query = "insert into procedure_manutenzione(nomeprocedura,nomefile,idfile) values(?,?,?)";
         pst = conn.prepareStatement(query);
         pst.setString(1,nomeprocedura);
         pst.setString(2, "");
         pst.setInt(3, 0);
         pst.executeUpdate();
         return true;
        } catch(SQLException ex){
          System.out.println("Errore nell'aggiunta nuova procedura ! "+ex);
          return false;
        }
    }
    
    

    public  boolean deleteProcedure(String name) {   
      String query = "delete from procedure_manutenzione where nomeprocedura=?";
      try {
        conn = ConnectionDatabase.getConnection();
        pst= conn.prepareStatement(query); 
        pst.setString(1,name);
        pst.execute();
        return true;     
      } catch (SQLException ex) {
        System.out.println("Errore nell'eliminazione");
        return false;
    }  
   }
}
