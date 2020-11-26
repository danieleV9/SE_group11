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
}
