/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import model.UserModel;

/**
 *
 * @author jenni
 */
public interface UserDAO {

    public abstract UserModel findUser(String username, String password, String role) throws Exception;

    public abstract Connection getConnection();

    public abstract void closeConnection() throws SQLException;
}
