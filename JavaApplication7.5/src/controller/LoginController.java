/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AdminModel;
import model.MaintainerModel;
import model.PlannerModel;
import view.AdminHomeView;
import view.LoginView;
import view.MaintainerHomeView;
import view.PlannerHomeView;

/**
 *
 * @author dava9
 */
public class LoginController {

    private LoginView view;
    private AdminModel modelA;
    private PlannerModel modelP;
    private MaintainerModel modelM;

    /* public LoginController(LoginView view, AdminModel modelA){
        this.view = view;
        this.modelA = modelA;
        this.view.addLoginListener(new LoginListener());
        this.view.addCancelListener(new CancelListener());
        view.setVisible(true);
    }*/

    public LoginController(LoginView view, AdminModel modelA, PlannerModel modelP, MaintainerModel modelM) {
        this.view = view;
        this.modelA = modelA;
        this.modelP = modelP;
        this.modelM = modelM;
        this.view.addLoginListener(new LoginListener());
        this.view.addCancelListener(new CancelListener());
        view.setVisible(true);
    }

    public class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = "";
            String password = "";
            String role = "";
            try {
                username = view.getUsername();
                System.out.println(username);
                password = view.getPassowrd();
                System.out.println(password);
                role = view.getRole();
                System.out.println(role);
                switch (role) {
                    case "System Administrator": {
                        AdminModel ad = (AdminModel) modelA.findUser(username, password, role);
                        if (ad == null) {
                            view.displayErrorMessage("Username or password not matched");
                            System.out.println("query return null");
                        } else {
                            AdminHomeView adHome = new AdminHomeView();
                            /*quando creiamo la nuova view dobbiamo istanziare anche il relativo controller per mantenere
                                il riferimento alla view appena creata*/
                            AdminHomeController controllerHome = new AdminHomeController(adHome, modelA, username);
                            adHome.setVisible(true);
                            view.setVisible(false);
                        }
                        break;
                    }
                    case "Planner": {
                        PlannerModel ad = (PlannerModel) modelP.findUser(username, password, role);
                        if (ad == null) {
                            view.displayErrorMessage("Username or password not matched");
                            System.out.println("query return null");
                        } else {
                            PlannerHomeView plHome = new PlannerHomeView(username);
                            PlannerHomeController phc = new PlannerHomeController(plHome, ad);
                            plHome.setVisible(true);
                            view.setVisible(false);
                        }
                        break;
                    }
                    case "Maintainer": {
                        MaintainerModel ad = (MaintainerModel) modelM.findUser(username, password, role);
                        if (ad == null) {
                            view.displayErrorMessage("Username or password not matched");
                            System.out.println("query return null");
                        } else {
                            MaintainerHomeView maHome = new MaintainerHomeView(username);
                            maHome.setVisible(true);
                            view.setVisible(false);
                        }
                        break;
                    }

                    default:
                        break;
                }
            } catch (Exception ex) {
                System.out.println("" + ex);
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }

}
