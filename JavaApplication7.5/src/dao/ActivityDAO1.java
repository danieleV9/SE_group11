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
import model.ProcedureModel;

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
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;

    public List<MaintenanceActivityModel> getAllActivity() {
        List<MaintenanceActivityModel> list = new ArrayList<>();
        try {
            conn = ConnectionDatabase.getConnection();
            st = conn.createStatement();
            String query = "select * from attivita_manutenzione order by settimana";
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
                String nomeproc = rs.getString("nomeprocedura");
                ProcedureModel proc = new ProcedureModel("","");
                String path =proc.getPath(nomeproc);
                list.add(new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica,new ProcedureModel(nomeproc,path)));
            }
        } catch (SQLException ex) {
            System.out.println("errore");
            System.out.println(ex.getMessage());
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { st.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
        return list;
    }

    public List<MaintenanceActivityModel> getAllActivity(int numWeek) {
        List<MaintenanceActivityModel> list = new ArrayList<>();
        try {
            conn = ConnectionDatabase.getConnection();
            String query = "select * from attivita_manutenzione where settimana=?";
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
                String nomeproc = rs.getString("nomeprocedura");
                ProcedureModel proc = new ProcedureModel("","");
                String path =proc.getPath(nomeproc);
                list.add(new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica, new ProcedureModel(nomeproc,path)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
        return list;
    }

    public boolean insertActivity(int numberWeek, String workNotes, String type, String factory, String tipology, int time, String description, String area, boolean interruptible,ProcedureModel proc) {
        try {
            conn = ConnectionDatabase.getConnection();
            String query = "INSERT INTO ATTIVITA_MANUTENZIONE (settimana,notelavoro,tipoattivita,interrompibile,"
                    + "idattivita,fabbrica,area,usernamema,tipotipologia,nomeprocedura,tempostimato,descrizione,"
                    + "dataattivita,statoticket) values (?,?,?,?,(NEXTVAL(idattivita)),?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setInt(1, numberWeek);
            pst.setString(2, workNotes);
            pst.setString(3, type);
            pst.setBoolean(4, interruptible);
            pst.setString(5, factory);
            pst.setString(6, area);
            pst.setNull(7, Types.NULL);
            pst.setString(8, tipology);
            pst.setString(9,proc.getNomeProc() );
            pst.setInt(10, time);
            pst.setString(11, description);
            pst.setNull(12, Types.NULL);
            pst.setNull(13, Types.NULL);
            pst.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println("Errore nell'inserimento dell'attività");
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
        return true;
    }

    public MaintenanceActivityModel viewActivity(int id) {
        String query = "select * from attivita_manutenzione where idattivita=?";
        try {
            conn = ConnectionDatabase.getConnection();
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
                String nomeproc = rs.getString("nomeprocedura");
                ProcedureModel proc = new ProcedureModel("","");
                String path =proc.getPath(nomeproc);
                return new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica,new ProcedureModel(nomeproc,path));
            }
        } catch (SQLException ex) {
            System.out.println("Errore");
            System.out.println(ex.getMessage());
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
        return null;
    }

    public String findProcedura(int id) {
        String query = "select nomeprocedura from attivita_manutenzione where idattivita=?";
        try {
            conn = ConnectionDatabase.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                String procedura = rs.getString("nomeprocedura");
                return procedura;
            }
        } catch (SQLException ex) {
            System.out.println("Errore");
            System.out.println(ex.getMessage());
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
        return null;
    }

    public void aggiornaNote(String note, int id) {
        String query = "update attivita_manutenzione set notelavoro=? where idattivita=?";
        try {
            conn = ConnectionDatabase.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, note);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Errore nell'aggiornamento delle note");
            System.out.println(ex.getMessage());
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
    }

    public boolean deleteActivity(int id) {
        String query = "delete from attivita_manutenzione where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            }
            conn = ConnectionDatabase.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Errore nell'eliminazione");
            System.out.println(ex.getMessage());
            return false;
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
    }

    public boolean assignedActivity(int id) {
        String query = "select usernamema from attivita_manutenzione where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            } else {
                conn = ConnectionDatabase.getConnection();
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
        } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
        return false;
    }

    public boolean assignNewActivity(int id, String username, String data) {
        String query = "update attivita_manutenzione set usernamema=?,dataattivita=? where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            } else {
                conn = ConnectionDatabase.getConnection();
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
        } finally {
                try { pst.close(); } catch (SQLException e) { }
                try { conn.close(); } catch (SQLException e) { }
            }
    }

}