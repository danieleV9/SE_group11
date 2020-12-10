/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dava9
 */
public class ConnectionDatabase {

    static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/applicazione", "root", ""); //database name,"db username","password"
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return con;
    }
    
    public static void closeConnection() throws SQLException{
        con.close();
    }
}
