/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AdminModel;
import model.factory.EmployeeFactory;
import model.PlannerModel;
import model.UserModel;
import model.factory.ManagerFactory;
import model.factory.UserFactory;
import view.AdminHomeView;
import view.LoginView;
import view.MaintainerHomeView;
import view.PlannerHomeView;

/**
 *
 * @author dava9
 */
public class LoginController {

    private final LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
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
                
                password = view.getPassowrd();
                
                role = view.getRole();
                
                switch (role) {
                    case "System Administrator": {
                        UserFactory managerFactory = new ManagerFactory();
                        UserModel managerAD = managerFactory.build(UserFactory.Role.ADMINISTRATOR, username, password);
                        managerAD =  managerAD.findUser(managerAD.getUsername(),managerAD.getPassword());
                        if (managerAD == null) {
                            view.displayErrorMessage("Username or password not matched");
                            
                        } else {
                            AdminHomeView adHome = new AdminHomeView();
                            /*quando creiamo la nuova view dobbiamo istanziare anche il relativo controller per mantenere
                                il riferimento alla view appena creata*/
                            AdminHomeController controllerHome = new AdminHomeController(adHome, (AdminModel) managerAD);
                            adHome.setVisible(true);
                            view.setVisible(false);
                        }
                        break;
                    }
                    case "Planner": {
                        UserFactory employeeFactory = new EmployeeFactory();
                        UserModel employeePL = employeeFactory.build(UserFactory.Role.PLANNER,username,password);
                        employeePL = employeePL.findUser(employeePL.getUsername(), employeePL.getPassword());
                        if (employeePL== null) {
                            view.displayErrorMessage("Username or password not matched");
                        }else {
                            PlannerHomeView plHome = new PlannerHomeView();
                            PlannerHomeController phc = new PlannerHomeController(plHome, (PlannerModel) employeePL);
                            plHome.setVisible(true);
                            view.setVisible(false);
                        }
                        break;
                    }
                    case "Maintainer": {
                        UserFactory employeeFactory = new EmployeeFactory();
                        UserModel employeeMA = employeeFactory.build(UserFactory.Role.MAINTAINER,username,password);
                        employeeMA =employeeMA.findUser(employeeMA.getUsername(), employeeMA.getPassword());
                        if (employeeMA == null) {
                            view.displayErrorMessage("Username or password not matched");
                            
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
                System.out.println(ex.getMessage());
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }

}
