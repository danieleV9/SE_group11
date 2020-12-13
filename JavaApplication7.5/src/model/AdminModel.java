/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AdminDAO;
import java.sql.Connection;

/**
 *
 * @author dava9
 */
public class AdminModel extends UserModel {

    private AdminDAO dao;

    public AdminModel(String username, String password) {
        super(username, password);
        dao = new AdminDAO();
    }

    @Override
    public UserModel findUser(String username, String password, String role) throws Exception {
        AdminModel ad = dao.findUser(username, password, role);
        return ad;
    }

    @Override
    public Connection getConnection() {
        return dao.getConnection();
    }

    @Override
    public void closeConnection() {
        dao.closeConnection();
    }
}
