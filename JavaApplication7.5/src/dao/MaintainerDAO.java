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

    private Connection con = getConnection();
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Statement st;
    private String query;

    @Override
    public UserModel findUser(String username, String password, String role) {
        if (!usernameExists(username)) {
            return null; //se l'username non esiste
        }
        try {
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
                    //con.close();
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
        if (!username.equals("")) {
            try {
                query = "select * from manutentore where usernamema=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamema");
                    String pass = rs.getString("passwordma");
                    MaintainerModel ma = new MaintainerModel(user, pass);
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
        if (username.equals("") || password.equals("")) { // "Username e Password non possono essere vuoti";
            return false;
        }
        try {
            if (!usernameExists(username)) { //se username non è già utilizzato
                query = "insert into manutentore(usernamema, passwordma) values (?,?)";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.execute();
                //con.close();
                return true; // "Maintainer creato con successo";
            } else {
                return false;//"Username già utilizzato";
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    @Override
    public boolean updateUserPassword(String username, String password
    ) {
        if (username.equals("")) {
            return false;
        }
        if (!usernameExists(username)) {
            return false;
        }
        try {
            if (!usernameExists(username)) {
                return false;
            } else {
                query = "update manutentore set passwordma=? where usernamema=?";
                pst = con.prepareStatement(query);
                pst.setString(2, username);
                pst.setString(1, password);
                pst.execute();
                //con.close();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    @Override
    public boolean usernameExists(String username
    ) {// in usernames ci sono tutti gli username utilizzati
        if (username.equals("")) {
            return false;
        }
        try {
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
            return true;
        }
    }

    public List<MaintainerModel> listMaintainers() {
        List<MaintainerModel> list = new ArrayList<>();
        try {
            query = "select * from manutentore";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("usernamema");
                String password = rs.getString("passwordma");
                MaintainerModel maintainer = new MaintainerModel(username, password);
                list.add(maintainer);
            }
            //con.close();
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
            query = "delete from manutentore where usernamema=?";
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

    public boolean addCompetence(String username, int id) {
        MaintainerModel m = new MaintainerModel("","");
        if(m.hasCompetences(username, id)==false){
        try {
            query = "insert into competenze_ma(usernamema,idcompetenza) values(?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setInt(2, id);
            pst.executeUpdate();
            //con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
         }
        } else return false;
    }

    public boolean removeCompetence(String username, int id) {
        MaintainerModel m = new MaintainerModel("","");
        if(m.hasCompetences(username, id)){
        try {
            query = "delete from competenze_ma where usernamema=? and idcompetenza=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setInt(2, id);
            pst.executeUpdate();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
        } else return false;
    }

    public boolean hasCompetences(String username, int id) {
        try {
            query = "select count(*) from competenze_ma where usernamema=? and idcompetenza=?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setInt(2, id);
            rs = pst.executeQuery();
            int risultato = 0;
            if (rs.next()) {
                risultato = rs.getInt(1);
                //con.close();

                if (risultato == 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                //con.close();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
            return false;
        }
    }

    public List<MaintainerModel> listMaintainersDisponibili() {
        List<MaintainerModel> list = new ArrayList<>();
        try {
            query = "select distinct manutentore from disponibilita_giorno";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("manutentore");
                MaintainerModel maintainer = new MaintainerModel(username, "");
                list.add(maintainer);
            }
            //con.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
            return list;
        }
        return list;
    }

    public String getDisponibilitaGiorno(String username, int week, int day) { //restituisce string di minuti in fasce orarie per quel giorno della settimana
        String fasce = "";
        if (usernameExists(username) && week > 0 && week < 53 && day > 0 && day < 8) { //se username valido e giorno valido e settimana valida
            try {
                query = "select fasce_orarie from disponibilita_giorno where manutentore=? and week=? and day=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setInt(2, week);
                pst.setInt(3, day);
                rs = pst.executeQuery();
                if (rs.next()) {
                    fasce = rs.getString("fasce_orarie");
                    //con.close();
                } else {
                    fasce = "";
                    //con.close();
                }
                return fasce;
            } catch (SQLException ex) {
                System.out.println(ex);
                return fasce;
            }
        } else {
            return fasce;
        }
    }

    public int getNumGiorno(String username, int week, int day) { //restituisce string di minuti in fasce orarie per quel giorno della settimana
        int numGiorno = 0;
        if (usernameExists(username) && week > 0 && week < 53 && day > 0 && day < 8) { //se username valido e giorno valido e settimana valida
            try {
                query = "select numgiorno from disponibilita_giorno where manutentore=? and week=? and day=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setInt(2, week);
                pst.setInt(3, day);
                rs = pst.executeQuery();
                if (rs.next()) {
                    numGiorno = rs.getInt("numgiorno");
                    //con.close();
                } else {
                    numGiorno = 0;
                    //con.close();
                }
                return numGiorno;
            } catch (SQLException ex) {
                System.out.println(ex);
                return numGiorno;
            }
        } else {
            return numGiorno;
        }
    }

    @Override
    public Connection getConnection() {
        return con = ConnectionDatabase.getConnection();
    }

    @Override
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("CHIUSURA DEL DATABASE FALLITA.");
            System.err.println(e.getMessage());
        }
    }
}
