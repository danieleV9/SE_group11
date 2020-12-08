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
public class AdminModel extends UserModel {

    public AdminModel(String username, String password) {
        super(username, password);
    }

    @Override
    public UserModel findUser(String username, String password, String role) throws Exception {
        AdminDAO dao = new AdminDAO();
        AdminModel ad = dao.findUser(username, password, role);
        return ad;
    }

}
