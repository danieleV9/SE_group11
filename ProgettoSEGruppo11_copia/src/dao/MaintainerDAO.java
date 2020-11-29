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
import model.MaintainerModel;

/**
 *
 * @author dava9
 */
public class MaintainerDAO {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public MaintainerModel findMaintainer(String username, String password, String role){
        try{
            con = ConnectionDatabase.getConnection();
            String query = "";
            if(role.equalsIgnoreCase("Maintainer")){
                query = "select * from manutentore where usernamema=? and passwordma=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if(rs.next()){
                    String user = rs.getString("usernamema");
                    String pass = rs.getString("passwordma");
                    MaintainerModel ma = new MaintainerModel(user,pass);
                    return ma;
                }
            }     
        }catch(SQLException ex){
            System.out.println(""+ex);
        }
        return null;
    }
}
