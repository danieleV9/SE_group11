/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.UserDAO;
import java.sql.Connection;

/**
 *
 * @author jenni
 */
public abstract class UserModel {

    private String username;
    private String password;
    UserDAO dao;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract UserModel findUser(String username, String password, String role) throws Exception;

    @Override
    public String toString() {
        return "UserModel{" + "username=" + username + ", password=" + password + '}';
    }

    public abstract Connection getConnection();

    public abstract void closeConnection();

}
