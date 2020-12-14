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
import model.UserModel;

/**
 *
 * @author dava9
 */
public class PlannerDAO implements EmployeeDAO {

    private Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Statement st;
    private String query;

    @Override
    public UserModel findUser(String username, String password) {
        if (!usernameExists(username)) {
            return null; //se l'username non esiste
        }
        if (!username.equals("") && !password.equals("")) {
            try {
                con = ConnectionDatabase.getConnection();
                query = "select * from pianificatore where usernamepl=? and passwordpl=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamepl");
                    String pass = rs.getString("passwordpl");
                    PlannerModel pl = new PlannerModel(user, pass);
                   // con.close();
                    return pl;
                } else {
                   // con.close();
                    return null;
                }
            } catch (SQLException ex) {
                System.out.println("" + ex);
                return null;
            } catch (Exception ex) {
                System.out.println("" + ex);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public UserModel findUsername(String username) {
        if (!usernameExists(username)) {
            return null;
        }
        if (!username.equals("")) {
            try {
                con = ConnectionDatabase.getConnection();
                query = "select * from pianificatore where usernamepl=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamepl");
                    String pass = rs.getString("passwordpl");
                    PlannerModel ma = new PlannerModel(user, pass);
                    //con.close();
                    return ma;
                }
                //con.close();
            } catch (SQLException ex) {
                System.out.println("" + ex);
                return null;
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
            if (!usernameExists(username)) { //se username non è già utilizzato
                con = ConnectionDatabase.getConnection();
                query = "insert into pianificatore(usernamepl, passwordpl) values (?,?)";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.execute();

                PlannerModel pl = new PlannerModel(username, password);
                //con.close();
                return true;// "Planner creato con successo";
            } else {
                //con.close();
                return false; //"Username già utilizzato";
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
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
            con = ConnectionDatabase.getConnection();
            query = "update pianificatore set passwordpl=? where usernamepl=?";
            pst = con.prepareStatement(query);
            pst.setString(2, username);
            pst.setString(1, password);
            pst.execute();
            //con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    @Override
    public boolean usernameExists(String username) {// in usernames ci sono tutti gli username utilizzati
        try {
            con = ConnectionDatabase.getConnection();
            query = "select count(*) from usernames where username=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            int risultato = 0;
            if (rs.next()) {
                risultato = rs.getInt(1);
                //con.close();

                if (risultato == 0) {//query va a buon fine ma non trova niente =>username non è utilizzato.
                    System.out.println("Username non c'è");
                    return false;
                } else { //risultato!=0
                    System.out.println("risultato trovato:" + rs.getInt(1) + "corrispondenza");
                    return true;
                } //query va a buon fine e trova username
            } else {
                //con.close();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public List<PlannerModel> listPlanners() {
        List<PlannerModel> list = new ArrayList<>();
        try {
            con = ConnectionDatabase.getConnection();
            query = "select * from pianificatore";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("usernamepl");
                String password = rs.getString("passwordpl");
                PlannerModel planner = new PlannerModel(username, password);
                list.add(planner);
            }
            //con.close();
        } catch (SQLException ex) {
            System.out.println("" + ex);
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
            con = ConnectionDatabase.getConnection();
            query = "delete from pianificatore where usernamepl=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.executeUpdate();
            //con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }


}
