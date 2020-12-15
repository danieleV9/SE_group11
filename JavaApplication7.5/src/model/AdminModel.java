/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AdminDAO;

/**
 *
 * @author dava9
 */
public class AdminModel extends ManagerModel {

    private AdminDAO dao;

    public AdminModel(String username, String password) {
        super(username, password);
        dao = new AdminDAO();
    }

    @Override
    public UserModel findUser(String username, String password) throws Exception {
        AdminModel ad = dao.findUser(username, password);
        return ad;
    }

    @Override
    public String toString() {
        return "AdminModel{" + super.toString() + '}';
    }

}
