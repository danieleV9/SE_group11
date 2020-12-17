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
import model.MaintainerModel;
import model.MaintenanceActivityModel;
import model.ProcedureModel;
import model.UserModel;

/**
 *
 * @author dava9
 */
public class MaintainerDAO implements EmployeeDAO {
    
    private Connection conn;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Statement st;

    public MaintainerDAO() {
      
    }

    @Override
    public UserModel findUser(String username, String password) {
        if (!usernameExists(username)) {
            return null; //se l'username non esiste
        }
        try {
            conn =ConnectionSingleton.getInstance();
            if (!username.equals("") && !password.equals("")) {
                String query = "select * from manutentore where usernamema=? and passwordma=?";
                pst = conn.prepareStatement(query);
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
            System.out.println(ex.getMessage());
            return null;
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
              //  try { conn.close(); } catch (SQLException e) { }
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
                String query = "select * from manutentore where usernamema=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamema");
                    String pass = rs.getString("passwordma");
                    MaintainerModel ma = new MaintainerModel(user, pass);
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
        if (username.equals("") || password.equals("")) { // "Username e Password non possono essere vuoti";
            return false;
        }
        try {
            conn =ConnectionSingleton.getInstance();
            if (!usernameExists(username)) { //se username non è già utilizzato
                String query = "insert into manutentore(usernamema, passwordma) values (?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                int res = pst.executeUpdate();
                if(res == 1)
                    return true;
                else
                    return false;
            } else {
                return false;//"Username già utilizzato";
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
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
            conn =ConnectionSingleton.getInstance();
            if (!usernameExists(username)) {
                return false;
            } else {
                String query = "update manutentore set passwordma=? where usernamema=?";
                pst = conn.prepareStatement(query);
                pst.setString(2, username);
                pst.setString(1, password);
                int res = pst.executeUpdate();
                if(res == 1)
                    return true;
                else
                    return false;
            }
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
        if (username.equals("")) {
            return false;
        }
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
            return true;
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public List<MaintainerModel> listMaintainers() {
        List<MaintainerModel> list = new ArrayList<>();
        try {
           conn =ConnectionSingleton.getInstance();
            String query = "select * from manutentore";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("usernamema");
                String password = rs.getString("passwordma");
                MaintainerModel maintainer = new MaintainerModel(username, password);
                list.add(maintainer);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return list;
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
            String query = "delete from manutentore where usernamema=?";
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
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public boolean addCompetence(String username, int id) {
        MaintainerModel m = new MaintainerModel("","");
        if(m.hasCompetences(username, id)==false){
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "insert into competenze_ma(usernamema,idcompetenza) values(?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setInt(2, id);
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
        } else return false;
    }

    public boolean removeCompetence(String username, int id) {
        MaintainerModel m = new MaintainerModel("","");
        if(m.hasCompetences(username, id)){
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "delete from competenze_ma where usernamema=? and idcompetenza=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setInt(2, id);
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
        } else return false;
    }

    public boolean hasCompetences(String username, int id) {
        try {
            conn =ConnectionSingleton.getInstance();
            String query = "select count(*) from competenze_ma where usernamema=? and idcompetenza=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setInt(2, id);
            rs = pst.executeQuery();
            int risultato = 0;
            if (rs.next()) {
                risultato = rs.getInt(1);
                if (risultato == 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
    }

    public List<MaintainerModel> listMaintainersDisponibili() {
        List<MaintainerModel> list = new ArrayList<>();
        try {
           conn =ConnectionSingleton.getInstance();
            String query = "select distinct manutentore from disponibilita_giorno";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("manutentore");
                MaintainerModel maintainer = new MaintainerModel(username, "");
                list.add(maintainer);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return list;
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { st.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        return list;
    }

    public String getDisponibilitaGiorno(String username, int week, int day) { //restituisce string di minuti in fasce orarie per quel giorno della settimana
        String fasce = "";
        if (usernameExists(username) && week > 0 && week < 53 && day > 0 && day < 8) { //se username valido e giorno valido e settimana valida
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select fasce_orarie from disponibilita_giorno where manutentore=? and week=? and day=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setInt(2, week);
                pst.setInt(3, day);
                rs = pst.executeQuery();
                if (rs.next()) {
                    fasce = rs.getString("fasce_orarie");
                } else {
                    fasce = "";
                }
                return fasce;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return fasce;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
               // try { conn.close(); } catch (SQLException e) { }
            }
        } else {
            return fasce;
        }
    }

    public boolean updateDisponibilitaGiorno(String username, int week, int day, String fasce) { //modifica minuti in fasce orarie per quel giorno della settimana
        if (usernameExists(username) && week > 0 && week < 53 && day > 0 && day < 8) { //se username valido e giorno valido e settimana valida
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "update disponibilita_giorno set fasce_orarie=? where manutentore=? and week=? and day=?";
                pst = conn.prepareStatement(query);
                pst.setString(2, username);
                pst.setInt(3, week);
                pst.setInt(4, day);
                pst.setString(1, fasce);
                int res = pst.executeUpdate();
                if(res == 1)
                    return true;
                else
                    return false;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
               // try { conn.close(); } catch (SQLException e) { }
            }
        } else {
            return false;
        }
    }
    
    public List<MaintenanceActivityModel> getActivities(String username) {
        List<MaintenanceActivityModel> list = new ArrayList<>();
        if (usernameExists(username)){
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select * from attivita_manutenzione where usernamema=?";
                pst = conn.prepareStatement(query);
                pst.setString(1,username);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String descrizione = rs.getString("descrizione");
                    int id = rs.getInt("idattivita");
                    String tipo = rs.getString("tipotipologia");
                    int settimana = rs.getInt("settimana");
                    String notelavoro = rs.getString("notelavoro");
                    String area = rs.getString("area");
                    String fabbrica = rs.getString("fabbrica");
                    int tempostimato = rs.getInt("tempostimato");
                    String nomeproc = rs.getString("nomeprocedura");
                    String data= rs.getString("dataattivita");
                    String path = "";
                    ProcedureModel proc = new ProcedureModel("", "");
                    if (nomeproc != null) {
                        path = proc.getPath(nomeproc);
                    } else {
                        nomeproc = "";
                    }
                    MaintenanceActivityModel activity = new MaintenanceActivityModel(settimana,true, id,descrizione, tipo,"",tempostimato,  notelavoro,new ProcedureModel(nomeproc, path), null,area, fabbrica, data);
                    list.add(activity);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return list;
            } finally {
                    try { rs.close(); } catch (SQLException e) { }
                    try { pst.close(); } catch (SQLException e) { }
                    //try { conn.close(); } catch (SQLException e) { }
            }
        }
            return list;
        }

    public int getNumGiorno(String username, int week, int day) { //restituisce string di minuti in fasce orarie per quel giorno della settimana
        int numGiorno = 0;
        if (usernameExists(username) && week > 0 && week < 53 && day > 0 && day < 8) { //se username valido e giorno valido e settimana valida
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select numgiorno from disponibilita_giorno where manutentore=? and week=? and day=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setInt(2, week);
                pst.setInt(3, day);
                rs = pst.executeQuery();
                if (rs.next()) {
                    numGiorno = rs.getInt("numgiorno");
                } else {
                    numGiorno = 0;
                }
                return numGiorno;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return numGiorno;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        } else {
            return numGiorno;
        }
    }


}