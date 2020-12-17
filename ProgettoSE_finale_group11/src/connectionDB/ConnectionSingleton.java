/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dava9
 */
public class ConnectionSingleton {
    // static members
    private static Connection instance = null;
    
    public synchronized static Connection getInstance() {
        try {
            if (instance== null || instance.isClosed()) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/applicazione", "root", ""); //database name,"db username","password"
                } catch (ClassNotFoundException | SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }
    
    // private constructor
    private ConnectionSingleton() {}
    
}
