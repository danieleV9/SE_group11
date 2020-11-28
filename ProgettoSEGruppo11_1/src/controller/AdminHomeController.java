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
import view.UsersListView;

/**
 *
 * @author lyuba
 */
public class AdminHomeController {
    private AdminHomeView view;
    private AdminModel model;

    public AdminHomeController(AdminHomeView view, AdminModel model) {
        this.view = view;
        this.model = model;
        this.view.addUserListener(new UserListener());
        this.view.addAccessListener(new AccessListener());
        this.view.addMaterialListener(new MaterialListener());
        this.view.addSitesListener(new SitesListener());
        this.view.addProceduresListener(new ProceduresListener());
        this.view.addCompetencesListener(new CompetencesListener());
        this.view.addMaintenanceListener(new MaintenanceListener());
    }
    
    public class UserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            UsersListView usersview = new UsersListView();
            UsersListController controllerUsers = new UsersListController(usersview,model);
            usersview.setVisible(true);
            System.out.println("ciao");
            view.setVisible(false);
        }
    }
    
    public class AccessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("ciao");
        }
    }
        
    public class MaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }
    
    public class SitesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    public class ProceduresListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    public class CompetencesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    public class MaintenanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
}
