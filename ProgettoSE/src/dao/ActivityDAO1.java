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
import connectionDB.ConnectionSingleton;
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

    private Connection conn;
    private static PreparedStatement pst;
    private static ResultSet rs;
    private static Statement st;

    public ActivityDAO1() {
    }

    public List<MaintenanceActivityModel> getAllActivity() {
        List<MaintenanceActivityModel> list = new ArrayList<>();
        try {
            conn =ConnectionSingleton.getInstance();
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
                String path = "";
                ProcedureModel proc = new ProcedureModel("", "");
                if (nomeproc != null) {
                    path = proc.getPath(nomeproc);
                } else {
                    nomeproc = "";
                }
                list.add(new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica, new ProcedureModel(nomeproc, path)));
            }
        } catch (SQLException ex) {
            System.out.println("errore");
            System.out.println(ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                st.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
        return list;
    }

    public List<MaintenanceActivityModel> getAllActivity(int numWeek) {
        if (numWeek <= 0 || numWeek > 52) {
            return null;
        }
        List<MaintenanceActivityModel> list = new ArrayList<>();
        try {
            conn =ConnectionSingleton.getInstance();
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
                ProcedureModel proc = new ProcedureModel("", "");
                String path = proc.getPath(nomeproc);
                list.add(new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica, new ProcedureModel(nomeproc, path)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                pst.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
        return list;
    }

    public int insertActivity(int numberWeek, String workNotes, String type, String factory, String tipology, int time, String description, String area, boolean interruptible, ProcedureModel proc) {
        try {
            conn = ConnectionSingleton.getInstance();
            int maxId;
            String query = "select max(idattivita) from attivita_manutenzione";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt(1);
            } else {
                maxId = 0;
            }
            String query1 = "INSERT INTO ATTIVITA_MANUTENZIONE (settimana,notelavoro,tipoattivita,interrompibile,"
                    + "idattivita,fabbrica,area,usernamema,tipotipologia,nomeprocedura,tempostimato,descrizione,"
                    + "dataattivita,statoticket) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(query1);
            pst.setInt(1, numberWeek);
            pst.setString(2, workNotes);
            pst.setString(3, type);
            pst.setBoolean(4, interruptible);
            pst.setInt(5, maxId + 1);
            pst.setString(6, factory);
            pst.setString(7, area);
            pst.setNull(8, Types.NULL);
            pst.setString(9, tipology);
            pst.setString(10, proc.getNomeProc());
            pst.setInt(11, time);
            pst.setString(12, description);
            pst.setNull(13, Types.NULL);
            pst.setNull(14, Types.NULL);
            int res = pst.executeUpdate();
            if (res == 1) {
                return maxId + 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            //System.out.println("Errore nell'inserimento dell'attività");
            System.out.println(ex.getMessage());
            return 0;
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
            }
        }
    }

    public MaintenanceActivityModel viewActivity(int id) {
        String query = "select * from attivita_manutenzione where idattivita=?";
        try {
            conn =ConnectionSingleton.getInstance();
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
                ProcedureModel proc = new ProcedureModel("", "");
                String path = proc.getPath(nomeproc);
                return new MaintenanceActivityModel(settimana, id, tipo, descrizione, notelavoro, area, tempostimato, fabbrica, new ProcedureModel(nomeproc, path));
            }
        } catch (SQLException ex) {
            System.out.println("Errore");
            System.out.println(ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                pst.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
        return null;
    }

    public String findProcedura(int id) {
        String query = "select nomeprocedura from attivita_manutenzione where idattivita=?";
        try {
            conn =ConnectionSingleton.getInstance();
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
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                pst.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
        return null;
    }

    public boolean aggiornaNote(String note, int id) {
        if (note == null || note.equals("")) {
            return false;
        }
        if (this.viewActivity(id) == null) {
            return false;
        }
        String query = "update attivita_manutenzione set notelavoro=? where idattivita=?";
        try {
            conn =ConnectionSingleton.getInstance();
            pst = conn.prepareStatement(query);
            pst.setString(1, note);
            pst.setInt(2, id);
            int res = pst.executeUpdate();
            if (res == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Errore nell'aggiornamento delle note");
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
    }

    public boolean deleteActivity(int id) {
        String query = "delete from attivita_manutenzione where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            }
            conn =ConnectionSingleton.getInstance();
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            int res = pst.executeUpdate();
            if (res == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Errore nell'eliminazione");
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
    }

    public boolean assignedActivity(int id) {
        String query = "select usernamema from attivita_manutenzione where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            } else {
                conn =ConnectionSingleton.getInstance();
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
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            try {
                pst.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
        return false;
    }

    public boolean assignNewActivity(int id, String username, String data) {
        if (username.equals("") || data.equals("")) {
            return false;
        }
        String query = "update attivita_manutenzione set usernamema=?,dataattivita=? where idattivita=?";
        try {
            if (this.viewActivity(id) == null) {
                return false;
            } else {
                conn =ConnectionSingleton.getInstance();
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, data);
                pst.setInt(3, id);
                int res = pst.executeUpdate();
                if (res == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
            }
            //try { conn.close(); } catch (SQLException e) { }
        }
    }

}
