/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connectionDB.ConnectionDatabase;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.MaintainerModel;

/**
 *
 * @author dava9
 */
public class MaintainerDAO {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
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

    
        public MaintainerModel createMaintainer(String username, String password){
        try{
            con = ConnectionDatabase.getConnection();
            String query = "";
            if(!usernameExists(username)){ //se username non è già utilizzato
                query = "insert into manutentore(disponibilitaore, disponibilitagiorno, usernamema, passwordma) values ('?','?',?','?')";
                pst = con.prepareStatement(query);
                pst.setNull(1, Types.NULL);
                pst.setNull(2, Types.NULL);
                pst.setString(3, username);
                pst.setString(4, password);
                pst.execute();
               
                MaintainerModel ma = new MaintainerModel(username,password);
                return ma;
            }     
        }catch(SQLException ex){
            System.out.println(""+ex);
        }
        return null;
    }
        
    public void updateMaintainerPassword(MaintainerModel ma, String password){ 
        try{
            con = ConnectionDatabase.getConnection();
            String query = "update manutentore set passwordma='?' where usernamema=?";
                pst = con.prepareStatement(query);
                pst.setString(1, ma.getUsername());
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
    
    public List<MaintainerModel> listMaintainers(){
        List<MaintainerModel> list = new ArrayList<>();
        try{
            con = ConnectionDatabase.getConnection();
            String query = "select * from manutentore";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                String username = rs.getString("usernamema");
                String password = rs.getString("passwordma");
                MaintainerModel maintainer = new MaintainerModel(username,password);
                list.add(maintainer);
            }
        }catch(Exception ex){
            System.out.println(""+ex);
        }
        return list;
    }

}
