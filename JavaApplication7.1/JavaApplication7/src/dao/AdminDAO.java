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
import model.AdminModel;
import connectionDB.ConnectionDatabase;

/**
 *
 * @author dava9
 */
public class AdminDAO {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public AdminModel findAdmin(String username, String password, String role){
        try{
                con = ConnectionDatabase.getConnection();
                String query = "";
                if(role.equalsIgnoreCase("System Administrator")){
                    query = "select * from amministratore where usernamesa=? and passwordsa=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        String user = rs.getString("usernamesa");
                        String pass = rs.getString("passwordsa");
                        AdminModel a = new AdminModel(user,pass);
                        return a;
                    }
                }
                }catch(SQLException ex){
                System.out.println(""+ex);
            }
        return null;
    }
}
