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
import java.util.ArrayList;
import java.util.List;
import model.PlannerModel;

/**
 *
 * @author dava9
 */
public class PlannerDAO {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
    public PlannerModel findPlanner(String username, String password, String role){
        if(role.equalsIgnoreCase("Planner") && !username.equals("") && !password.equals("")){
            try{
                con = ConnectionDatabase.getConnection();
                String query = "";

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
                    }else 
                        return null;

            }catch(SQLException ex){
                System.out.println(""+ex);
                return null;
            }catch(Exception ex){
                System.out.println(""+ex);
                return null;
            }
        }else 
            return null;
    }
    
    public PlannerModel findPlanner(String username){
        if(!username.equals("")){
            try{
                con = ConnectionDatabase.getConnection();
                String query = "";

                    query = "select * from pianificatore where usernamepl=?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, username);
                    rs = pst.executeQuery();
                    if(rs.next()){
                        String user = rs.getString("usernamepl");
                        String pass = rs.getString("passwordpl");
                        PlannerModel pl = new PlannerModel(user,pass);
                        return pl;
                    }

            }catch(SQLException ex){
                System.out.println(""+ex);
                return null;
            }
        }
        return null;
    }


    public boolean createPlanner(String username, String password){
        if(username.equals("") || password.equals(""))
           // "Username e Password non possono essere vuoti";
            return false;
        try{
            con = ConnectionDatabase.getConnection();
            String query = "";
            if(!usernameExists(username)){ //se username non è già utilizzato
                query = "insert into pianificatore(usernamepl, passwordpl) values (?,?)";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.execute();
               
                PlannerModel pl = new PlannerModel(username,password);
                return true;// "Planner creato con successo";
            }else{
                return false; //"Username già utilizzato";
            }
        }catch(SQLException ex){
            System.out.println(""+ex);
            return false;
        }
    }
    
    public boolean updatePlannerPassword(String username, String password){
        if(username.equals(""))
            return false;
        if(!usernameExists(username))
            return false;
        try{
            con = ConnectionDatabase.getConnection();
            String query = "update pianificatore set passwordpl=? where usernamepl=?";
                pst = con.prepareStatement(query);
                pst.setString(2, username);
                pst.setString(1, password);
                pst.execute();
                return true;
        }catch(SQLException ex){
            System.out.println(""+ex);
            return false;
        }
    }
    
    public boolean usernameExists(String username){// in usernames ci sono tutti gli username utilizzati
        try{
            con= ConnectionDatabase.getConnection();
            String query = "select count(*) from usernames where username=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            int risultato=0;
            if(rs.next()){
                risultato=rs.getInt(1);
                
                if(risultato==0){//query va a buon fine ma non trova niente =>username non è utilizzato.
                    System.out.println("Username non c'è");
                    return false;
                }
                else { //risultato!=0
                    System.out.println("risultato trovato:"+rs.getInt(1)+"corrispondenza");
                    return true;
                } //query va a buon fine e trova username
            }else 
                return true;
        }catch(SQLException ex){
             System.out.println(ex);
             return true;
        }
        
    }
    
    public List<PlannerModel> listPlanners(){
        List<PlannerModel> list = new ArrayList<>();
        try{
            con = ConnectionDatabase.getConnection();
            String query = "select * from pianificatore";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                String username = rs.getString("usernamepl");
                String password = rs.getString("passwordpl");
                PlannerModel planner = new PlannerModel(username,password);
                list.add(planner);
            }
        }catch(Exception ex){
            System.out.println(""+ex);
        }
        return list;
    }

    public boolean deletePlanner(String username){
        if(username.equals(""))
            return false;
        if(!usernameExists(username))
            return false;
        try{
            con = ConnectionDatabase.getConnection();
            String query = "delete from pianificatore where usernamepl=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.executeUpdate();
            return true;
        }catch(SQLException ex){
           System.out.println(""+ex);
           return false;
        }
    }
}
