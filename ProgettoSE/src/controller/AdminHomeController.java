/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AdminModel;
import model.PlannerModel;
import model.ProcedureModel;
import view.AdminHomeView;
import view.AdminSkillView;
import view.LoginView;
import view.ProcedureView;
import view.UsersListView;

/**
 *
 * @author lyuba
 */
public class AdminHomeController {

    private final AdminHomeView view;
    private final AdminModel model;


    public AdminHomeController(AdminHomeView view, AdminModel model) {
        this.view = view;
        this.model = model;
        this.view.setLabel1(model.getUsername());
        this.view.addUserListener(new UserListener());
        this.view.addAccessListener(new AccessListener());
        this.view.addMaterialListener(new MaterialListener());
        this.view.addSitesListener(new SitesListener());
        this.view.addProceduresListener(new ProceduresListener());
        this.view.addCompetencesListener(new CompetencesListener());
        this.view.addMaintenanceListener(new MaintenanceListener());
        this.view.addLogoutListener(new LogoutListener());
    }

    public class UserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UsersListView usersview = new UsersListView(new PlannerModel("ProvaPlanner",""));
            UsersListController controllerUsers = new UsersListController(view, usersview, model);
            controllerUsers.populateTables();
            usersview.setVisible(true);
            view.setVisible(false);
        }
    }

    public class AccessListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }

    public class MaterialListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class SitesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ProceduresListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProcedureView procView = new ProcedureView();
            ProcedureModel model = new ProcedureModel("","");
            ProcedureController pc = new ProcedureController(view, procView, model);
            procView.setVisible(true);
            view.setVisible(false);
        }
    }

    public class CompetencesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminSkillView skillview = new AdminSkillView();
            AdminSkillController controllerUsers = new AdminSkillController(view, skillview, model);
            controllerUsers.populateTables();
            skillview.setVisible(true);
            view.setVisible(false);

        }
    }

    public class MaintenanceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView login= new LoginView();
            LoginController loginController= new LoginController(login);
            /*quando creiamo la nuova view dobbiamo istanziare anche il relativo controller per mantenere
            il riferimento alla view appena creata*/
            view.setVisible(false);
        }
    }

}
