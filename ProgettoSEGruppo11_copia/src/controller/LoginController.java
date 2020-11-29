/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.AdminHomeView;
import model.AdminModel;

/**
 *
 * @author dava9
 */
public class LoginController{
    
    private AdminHomeView view;
    private AdminModel model;

    public LoginController(AdminHomeView view, AdminModel model) {
        this.view = view;
        this.model = model;
    }
    
    
    
}
