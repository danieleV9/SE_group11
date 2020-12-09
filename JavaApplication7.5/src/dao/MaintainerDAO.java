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
import model.MaintainerModel;
import model.UserModel;

/**
 *
 * @author dava9
 */
public class MaintainerDAO implements EmployeeDAO {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;

    @Override
    public UserModel findUser(String username, String password, String role) {
        if (!usernameExists(username)) {
            return null; //se l'username non esiste
        }
        try {
            con = ConnectionDatabase.getConnection();
            String query = "";
            if (role.equalsIgnoreCase("Maintainer") && !username.equals("") && !password.equals("")) {
                query = "select * from manutentore where usernamema=? and passwordma=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamema");
                    String pass = rs.getString("passwordma");
                    MaintainerModel ma = new MaintainerModel(user, pass);
                    return ma;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return null;
        }

    }

    @Override
    public UserModel findUsername(String username) {
        if (!usernameExists(username)) {
            return null;
        }
        try {
            con = ConnectionDatabase.getConnection();
            String query = "";
            if (!username.equals("")) {
                query = "select * from manutentore where usernamema=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamema");
                    String pass = rs.getString("passwordma");
                    MaintainerModel ma = new MaintainerModel(user, pass);
                    return ma;
                } else {
                    return null;
                }
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("" + ex);
            return null;
        }

    }

    @Override
    public boolean createUser(String username, String password) {
        if (username.equals("") || password.equals("")) // "Username e Password non possono essere vuoti";
        {
            return false;
        }
        try {
            con = ConnectionDatabase.getConnection();
            String query = "";
            if (!usernameExists(username)) { //se username non è già utilizzato
                query = "insert into manutentore(usernamema, passwordma) values (?,?)";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.execute();

                return true;
                // "Maintainer creato con successo";
            } else {
                return false;//"Username già utilizzato";
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
            if (!usernameExists(username)) {
                return false;
            } else {
                String query = "update manutentore set passwordma=? where usernamema=?";
                pst = con.prepareStatement(query);
                pst.setString(2, username);
                pst.setString(1, password);
                pst.execute();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    @Override
    public boolean usernameExists(String username) {// in usernames ci sono tutti gli username utilizzati
        if (username.equals("")) {
            return false;
        }
        try {
            con = ConnectionDatabase.getConnection();
            String query = "select count(*) from usernames where username=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
            int risultato = 0;
            if (rs.next()) {
                risultato = rs.getInt(1);

                if (risultato == 0) {//query va a buon fine ma non trova niente =>username non è utilizzato.
                    System.out.println("Username non c'è");
                    return false;
                } else { //risultato!=0
                    System.out.println("risultato trovato:" + rs.getInt(1) + "corrispondenza");
                    return true;
                } //query va a buon fine e trova username
            } else {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return true;
        }
    }

    public List<MaintainerModel> listMaintainers() {
        List<MaintainerModel> list = new ArrayList<>();
        try {
            con = ConnectionDatabase.getConnection();
            String query = "select * from manutentore";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("usernamema");
                String password = rs.getString("passwordma");
                MaintainerModel maintainer = new MaintainerModel(username, password);
                list.add(maintainer);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
            return list;
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
            String query = "delete from manutentore where usernamema=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public boolean addCompetence(String username, String description) {
        try {
            con = ConnectionDatabase.getConnection();
            String query = "insert into competenze_ma(usernamema,descrizione) values(?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, description);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public boolean hasCompetences(String username, String description) {
        try {
            con = ConnectionDatabase.getConnection();
            String query = "select count(*) from competenze_ma where usernamema=? and descrizione=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, description);
            rs = pst.executeQuery();
            int risultato = 0;
            if (rs.next()) {
                risultato = rs.getInt(1);
                if (risultato == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
        return false;
    }
    
    public List<MaintainerModel> listMaintainersDisponibili(){
        List<MaintainerModel> list = new ArrayList<>();
        try{
            con = ConnectionDatabase.getConnection();
            String query = "select distinct manutentore from disponibilita_giorno";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                String username = rs.getString("manutentore");
                MaintainerModel maintainer = new MaintainerModel(username,"");
                list.add(maintainer);
            }
        }catch(Exception ex){
            System.out.println(""+ex);
            return list;
        }
        return list;
    }
    
    public String getDisponibilitaGiorno(String username,int week, int day){ //restituisce string di minuti in fasce orarie per quel giorno della settimana
        String fasce="";
        if(usernameExists(username) && week>0 && week<53 && day>0 && day<8){ //se username valido e giorno valido e settimana valida
            try{
                con= ConnectionDatabase.getConnection();
                String query = "select fasce_orarie from disponibilita_giorno where manutentore=? and week=? and day=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setInt(2, week);
                pst.setInt(3, day);
                rs = pst.executeQuery();
                if(rs.next()){
                   fasce= rs.getString("fasce_orarie"); 
                }else 
                   fasce="";
                return fasce;
            }catch(SQLException ex){
                 System.out.println(ex);
                 return fasce;
            }
        }else 
            return fasce;
    }

}
