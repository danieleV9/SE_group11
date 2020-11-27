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

/**
 *
 * @author lyuba
 */
public class AdminHomeUserController {
    private final AdminHomeView view;
    private final AdminModel model;

    public AdminHomeUserController(AdminHomeView view, AdminModel model) {
        this.view = view;
        this.model = model;
        this.view.addUserListener(new userListener());
        this.view.addAccessListener(new accessListener());
        this.view.addMaterialListener(new materialListener());
        this.view.addSitesListener(new sitesListener());
        this.view.addProceduresListener(new proceduresListener());
        this.view.addCompetencesListener(new competencesListener());
        this.view.addMaintenanceListener(new maintenanceListener());
    }
    
    public class userListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }
    
    public class accessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }
        
    public class materialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }
    
    public class sitesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    public class proceduresListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    public class competencesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }

    public class maintenanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

        }
    }
}
