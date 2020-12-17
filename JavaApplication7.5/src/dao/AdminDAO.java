/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.AdminModel;
import connectionDB.ConnectionSingleton;

/**
 *
 * @author dava9
 */
public class AdminDAO implements UserDAO {

    private Connection conn;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public AdminDAO() {
        
    }
    

    @Override
    public AdminModel findUser(String username, String password) {
        if (!username.equals("") && !password.equals("")) {
            try {
                conn =ConnectionSingleton.getInstance();
                String query = "select * from amministratore where usernamesa=? and passwordsa=?";
                pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamesa");
                    String pass = rs.getString("passwordsa");
                    AdminModel a = new AdminModel(user, pass);
                    return a;
                }

            } catch (SQLException ex) {
                System.out.println("" + ex);
                return null;
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                //try { conn.close(); } catch (SQLException e) { }
            }
        }
        return null;
    }


}