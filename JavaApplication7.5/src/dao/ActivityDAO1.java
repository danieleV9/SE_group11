/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author HP
 */
import connectionDB.ConnectionDatabase;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import model.MaintenanceActivityModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HP
 */
public class ActivityDAO1 {

    private List<MaintenanceActivityModel> listActivity;
    private Connection conn = getConnection();
    ;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    private String query;

    public List<MaintenanceActivityModel> getAllActivity() {
        List<MaintenanceActivityModel> list = new ArrayList<>();
        try {
            st = conn.createStatement();
            query = "select * from attivita_manutenzione order by settimana";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("idattivita");
                String tipo = rs.getString("tipotipologia");
                int settimana = rs.getInt("settimana");
                String descrizione = rs.getString("descrizione");
                String notelavoro = rs.getString("notelavoro");
                String area = rs.getString("area");
                String fabbrica = rs.getString("fabbrica");
                int tempostimato = rs.getInt("tempostimato");
                list.add(new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica));
            }
        } catch (SQLException ex) {
            System.out.println("errore");
        }
        return list;
    }

    public List<MaintenanceActivityModel> getAllActivity(int numWeek) {
        List<MaintenanceActivityModel> list = new ArrayList<>();
        try {
            query = "select * from attivita_manutenzione where settimana=?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, numWeek);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idattivita");
                String tipo = rs.getString("tipotipologia");
                int settimana = rs.getInt("settimana");
                String descrizione = rs.getString("descrizione");
                String notelavoro = rs.getString("notelavoro");
                String area = rs.getString("area");
                String fabbrica = rs.getString("fabbrica");
                int tempostimato = rs.getInt("tempostimato");
                list.add(new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica));
            }
        } catch (SQLException ex) {
            System.out.println("errore");
        }
        return list;
    }

    public boolean insertActivity(int numberWeek, String workNotes, String type, String factory, String tipology, int time, String description, String area, boolean interruptible) {
        try {
            conn = ConnectionDatabase.getConnection();
            String query = "INSERT INTO ATTIVITA_MANUTENZIONE (settimana,notelavoro,tipoattivita,interrompibile,"
                    + "idattivita,fabbrica,area,usernamema,tipotipologia,nomeprocedura,tempostimato,descrizione,"
                    + "dataattivita,statoticket) values (?,?,?,?,(NEXTVAL(idattivita)+1),?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setInt(1, numberWeek);
            pst.setString(2, workNotes);
            pst.setString(3, type);
            pst.setBoolean(4, interruptible);
            pst.setString(5, factory);
            pst.setString(6, area);
            pst.setNull(7, Types.NULL);
            pst.setString(8, tipology);
            pst.setNull(9, Types.NULL);
            pst.setInt(10, time);
            pst.setString(11, description);
            pst.setNull(12, Types.NULL);
            pst.setNull(13, Types.NULL);

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Errore nell'inserimento dell'attività");
            return false;
        }
        return true;
    }

    public MaintenanceActivityModel viewActivity(int id) {
        query = "select * from attivita_manutenzione where idattivita=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("tipotipologia");
                int settimana = rs.getInt("settimana");
                String descrizione = rs.getString("descrizione");
                String notelavoro = rs.getString("notelavoro");
                String area = rs.getString("area");
                String fabbrica = rs.getString("fabbrica");
                int tempostimato = rs.getInt("tempostimato");
                //String procedura = rs.getString("nomeprocedura");
                return new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica);
            }
        } catch (SQLException ex) {
            System.out.println("Errore");
        }
        return null;
    }

    public String findProcedura(int id) {
        query = "select nomeprocedura from attivita_manutenzione where idattivita=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                String procedura = rs.getString("nomeprocedura");
                return procedura;
            }
        } catch (SQLException ex) {
            System.out.println("Errore");
        }
        return null;
    }

    public void aggiornaNote(String note, int id) {
        query = "update attivita_manutenzione set notelavoro=? where idattivita=?";
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, note);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Errore nell'aggiornamento delle note");
        }
    }

    public boolean deleteActivity(int id) {
        query = "delete from attivita_manutenzione where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            }
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Errore nell'eliminazione");
            return false;
        }
    }

    public boolean assignedActivity(int id) {
        query = "select usernamema from attivita_manutenzione where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            } else {
                pst = conn.prepareStatement(query);
                pst.setInt(1, id);
                pst.execute();
                rs = pst.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("usernamema");
                    if (username == null) //attività non assegnata se campo username null
                    {
                        return false;
                    } else if (username.equals("")) {
                        return false;
                    } else {
                        return true; //attività assegnata se c'è un username diverso da null
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return false;
    }

    public boolean assignNewActivity(int id, String username, String data) {
        query = "update attivita_manutenzione set usernamema=?,dataattivita=? where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            } else {
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, data);
                pst.setInt(3, id);
                pst.execute();
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public Connection getConnection() {
        return conn = ConnectionDatabase.getConnection();
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("CHIUSURA DEL DATABASE FALLITA.");
            System.err.println(e.getMessage());
        }
    }
}
