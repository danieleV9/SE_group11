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
import connectionDB.ConnectionDatabase;

/**
 *
 * @author dava9
 */
public class AdminDAO implements UserDAO {

    private Connection con = getConnection();
    private static PreparedStatement pst;
    private static ResultSet rs;
    private String query;

    @Override
    public AdminModel findUser(String username, String password, String role) {
        if (role.equalsIgnoreCase("System Administrator") && !username.equals("") && !password.equals("")) {
            try {
                query = "select * from amministratore where usernamesa=? and passwordsa=?";
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String user = rs.getString("usernamesa");
                    String pass = rs.getString("passwordsa");
                    AdminModel a = new AdminModel(user, pass);
                    //con.close();
                    return a;
                }

            } catch (SQLException ex) {
                System.out.println("" + ex);
                return null;
            }
        }
        return null;
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
