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

    private Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    @Override
    public AdminModel findUser(String username, String password) {
        if (!username.equals("") && !password.equals("")) {
            try {
                con = ConnectionDatabase.getConnection();
                String query = "select * from amministratore where usernamesa=? and passwordsa=?";
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
            } finally {
                try { rs.close(); } catch (SQLException e) { }
                try { pst.close(); } catch (SQLException e) { }
                try { con.close(); } catch (SQLException e) { }
            }
        }
        return null;
    }

}
