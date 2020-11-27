/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AdminModel;
import view.AdminHomeView;
import view.LoginView;

/**
 *
 * @author dava9
 */
public class LoginController{
    
    private final LoginView view;
    private final AdminModel model;

    public LoginController(LoginView view, AdminModel model) {
        this.view = view;
        this.model = model;
        this.view.addLoginListener(new LoginListener());
        this.view.addCancelListener(new CancelListener());  
    }
    
    public class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    
    public class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String username = "";
            String password = "";  
            String role = "";
            try{
                username = view.getUsername();
                password = view.getPassowrd();
                role = view.getRole();
                AdminModel ad = model.findAdmin(username, password, role);
                if(ad == null){
                    view.displayErrorMessage("Username or password not matched");
                    System.out.println("query return null");
                }
                else{
                    AdminHomeView adHome = new AdminHomeView(username);
                    adHome.setVisible(true);
                    view.setVisible(false);
                }
            }catch(Exception ex){
                System.out.println(""+ex);
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }
    
    
}
