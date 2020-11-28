
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
import connectionDB.ConnectionDatabase;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ActivityDAO  {
    private List<MaintenanceActivityModel> listActivity;
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    
    public List<MaintenanceActivityModel> getAllActivity() {
        List<MaintenanceActivityModel> list = new ArrayList<>();
        try {
            conn = ConnectionDatabase.getConnection();
            this.st= conn.createStatement();  
            String query = "select * from attivita_manutenzione order by settimana";
            rs= st.executeQuery(query);
            while(rs.next()== true){
                int id = rs.getInt("idattivita");
                String tipo = rs.getString("tipoattivita");
                int settimana = rs.getInt("settimana");
                String descrizione = rs.getString("descrizione");
                String notelavoro= rs.getString("notelavoro");
                list.add(new MaintenanceActivityModel(settimana,id,tipo,descrizione,notelavoro));
            }
        } catch (SQLException ex){
            System.out.println("errore");
        }
        return list;
    }

   
    public void insertActivity(String area,String tipology,String factory,String type,String workNotes,String description,String interruptible,int time, int numberWeek)throws Exception{
        conn = ConnectionDatabase.getConnection();
        String query = "";
        query = "INSERT INTO ATTIVITA_MANUTENZIONE (settimana,notelavoro,tipoattivita,interrompibile,"
                + "idattivita,fabbrica,area,usernamema,tipotipologia,nomeprocedura,tempostimato,descrizione,"
                + "dataattivita,statoticket) values (?,?,?,0,(NEXTVAL(idattivita)+1),?,?,?,?,?,?,?,?,?)";
        pst= conn.prepareStatement(query);
        pst.setInt(1,numberWeek);
        pst.setString(2,workNotes);
        pst.setString(3,type);
      //pst.setBoolean(4,true);
        pst.setString(4,factory);
        pst.setString(5,area);
        pst.setNull(6,Types.NULL);
        pst.setString(7,tipology);
        pst.setNull(8,Types.NULL);
        pst.setInt(9,time);
        pst.setString(10,description);
        pst.setNull(11,Types.NULL);
        pst.setNull(12,Types.NULL);
    
        pst.executeUpdate();
    }

    public MaintenanceActivityModel viewActivity(int id) {
        String tipo = null;
        int settimana = 0;
        String descrizione= null;
        String notelavoro=null;
        String query = "select * from attivita_manutenzione where idattivita="+id;
        try {
            rs= st.executeQuery(query);
            while(rs.next()){
                tipo = rs.getString("tipoattivita");
                settimana = rs.getInt("settimana");
                descrizione = rs.getString("descrizione");
                notelavoro=rs.getString("notelavoro");
    }
        }catch (SQLException ex) {
            System.out.println("Errore");
        }
        return new MaintenanceActivityModel(settimana,id,descrizione,tipo,notelavoro);
    }
   
    public void aggiornaNote( String note , int id) {
        try {
            conn = ConnectionDatabase.getConnection();
            String query = "update attivita_manutenzione set notelavoro=? where idattivita=?";
            pst= conn.prepareStatement(query); 
            pst.setString(1,note);
            pst.setInt(2,id);
            pst.executeUpdate();
        }catch (SQLException ex) {
            System.out.println("Errore nell'aggiornamento delle note");
        }
    }

    public  void deleteActivity(int id) {
        String query = "delete from attivita_manutenzione where idattivita=" +id;
        try {
            st.execute(query);
        }catch (SQLException ex) {
            System.out.println("Errore nell'eliminazione");
        }
    } 
}

