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
import connectionDB.ConnectionSingleton;
import java.util.ArrayList;
import java.util.List;
import model.EmployeeModel;
import model.PlannerModel;
import model.UserModel;

/**
 *
 * @author dava9
 */
public class PlannerDAO implements EmployeeDAO {

    private Connection conn;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Statement st;
    
    public PlannerDAO() {
        
    }


    @Override
    public UserModel findUser(String username, String password) {
        if (!usernameExists(username)) {
            return null; //se l'username non esiste
        }
        if (!username.equals("") && !password.equals("")) {
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select * from pianificatore where usernamepl=? and passwordpl=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamepl");
                    String pass = rs.getString("passwordpl");
                    PlannerModel pl = new PlannerModel(user, pass);
                    return pl;
                } else {
                    return null;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
               // try { conn.close(); } catch (SQLException e) { }
            }
        } else {
            return null;
        }
    }

    @Override
    public EmployeeModel findUsername(String username) {
        if (!usernameExists(username)) {
            return null;
        }
        if (!username.equals("")) {
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select * from pianificatore where usernamepl=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamepl");
                    String pass = rs.getString("passwordpl");
                    PlannerModel ma = new PlannerModel(user, pass);
                    return ma;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return null;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        }
        return null;
    }

    @Override
    public boolean createUser(String username, String password) {
        if (username.equals("") || password.equals("")) // "Username e Password non possono essere vuoti";
        {
            return false;
        }
        try {
            conn =ConnectionSingleton.getInstance();
            if (!usernameExists(username)) { //se username non è già utilizzato
                String query = "insert into pianificatore(usernamepl, passwordpl) values (?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                int res = pst.executeUpdate();
                if(res == 1)
                    return true;// "Planner creato conn successo";
                else
                    return false;
            } else {
                return false; //"Username già utilizzato";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
               // try { conn.close(); } catch (SQLException e) { }
            }
    }

    @Override
    public boolean updateUserPassword(String username, String password) {
        if (username.equals("")) {
            return false;
        }
        if (!usernameExists(username)) {
            return false;
        }
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "update pianificatore set passwordpl=? where usernamepl=?";
            pst = conn.prepareStatement(query);
            pst.setString(2, username);
            pst.setString(1, password);
            int res = pst.executeUpdate();
            if(res == 1)
                return true;
            else
                return false;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    @Override
    public boolean usernameExists(String username) {// in usernames ci sono tutti gli username utilizzati
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "select count(*) from usernames where username=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            int risultato = 0;
            if (rs.next()) {
                risultato = rs.getInt(1);
                if (risultato == 0) {//query va a buon fine ma non trova niente =>username non è utilizzato.
                    //System.out.println("Username non c'è");
                    return false;
                } else { //risultato!=0
                    //System.out.println("risultato trovato:" + rs.getInt(1) + "corrispondenza");
                    return true;
                } //query va a buon fine e trova username
            } else {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
               // try { conn.close(); } catch (SQLException e) { }
            }
    }

    public List<PlannerModel> listPlanners() {
        List<PlannerModel> list = new ArrayList<>();
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "select * from pianificatore";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("usernamepl");
                String password = rs.getString("passwordpl");
                PlannerModel planner = new PlannerModel(username, password);
                list.add(planner);
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

    @Override
    public boolean deleteUser(String username) {
        if (username.equals("")) {
            return false;
        }
        if (!usernameExists(username)) {
            return false;
        }
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "delete from pianificatore where usernamepl=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            int res = pst.executeUpdate();
            if(res == 1)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
               // try { conn.close(); } catch (SQLException e) { }
            }
    }


}
