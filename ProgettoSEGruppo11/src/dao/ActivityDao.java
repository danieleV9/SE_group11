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
public class ActivityDao  {
    
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
    } catch (SQLException ex) {
      System.out.println("errore");
    } return list;
    }

   
    public void insertActivity(MaintenanceActivityModel a) {
      
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
      } catch (SQLException ex) {
        System.out.println("Errore");
     }  return new MaintenanceActivityModel(settimana,id,descrizione,tipo,notelavoro);
    }

   
    public void aggiornaNote( String note , int id) {
     try { 
     conn = ConnectionDatabase.getConnection();
     String query = "update attivita_manutenzione set notelavoro=? where idattivita=?"; 
     pst= conn.prepareStatement(query); 
     pst.setString(1,note);
     pst.setInt(2,id);
     pst.executeUpdate();  
   
        
    
    } catch (SQLException ex) {
      System.out.println("Errore nell'aggiornamento delle note");
    } 
    }

    
    

    public  void deleteActivity(int id) {   
      String query = "delete from attivita_manutenzione where idattivita=" +id;
      try {
        st.execute(query);
      } catch (SQLException ex) {
        System.out.println("Errore nell'eliminazione");
     }
    }

    
    
}

