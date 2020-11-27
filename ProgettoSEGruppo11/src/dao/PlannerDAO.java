/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connectionDB.ConnectionDatabase;
import model.PlannerModel;

/**
 *
 * @author dava9
 */
public class PlannerDAO {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public PlannerModel findPlanner(String username, String password, String role){
        try{
            con = ConnectionDatabase.getConnection();
            String query = "";
            if(role.equalsIgnoreCase("Maintainer")){
                query = "select * from pianificatore where usernamepl=? and passwordpl=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if(rs.next()){
                    String user = rs.getString("usernamepl");
                    String pass = rs.getString("passwordpl");
                    PlannerModel pl = new PlannerModel(user,pass);
                    return pl;
                }
            }     
        }catch(SQLException ex){
            System.out.println(""+ex);
        }
        return null;
    }

    public PlannerModel createPlanner(String username, String password){
        try{
            con = ConnectionDatabase.getConnection();
            String query = "";
            if(!usernameExists(username)){ //se username non è già utilizzato
                query = "insert into pianificatore(usernamepl, passwordpl) values ('?','?')";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.execute();
               
                PlannerModel pl = new PlannerModel(username,password);
                return pl;
            }     
        }catch(SQLException ex){
            System.out.println(""+ex);
        }
        return null;
    }
    
    public void updatePlannerPassword(PlannerModel pl, String password){ 
        try{
            con = ConnectionDatabase.getConnection();
            String query = "update pianificatore set passwordpl='?' where usernamepl=?";
                pst = con.prepareStatement(query);
                pst.setString(1, pl.getUsername());
                pst.setString(2, password);
                pst.execute();   
        }catch(SQLException ex){
            System.out.println(""+ex);
        }
    }
    
    public boolean usernameExists(String username){// in usernames ci sono tutti gli username utilizzati
        try{
            con = ConnectionDatabase.getConnection();
            String query = "select * from usernames where username=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next()){
                if(username==rs.getString("username"))
                return true; //l'username è già utilizzato!
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
        }
        return false; //username non trovato, può essere utilizzato
    }
}
