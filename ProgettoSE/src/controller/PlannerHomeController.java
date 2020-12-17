/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.MaintenanceActivityModel;
import model.PlannerModel;
import view.LoginView;
import view.PlannerActivityView;
import view.PlannerCreateView;
import view.PlannerHomeView;

/**
 *
 * @author jenni
 */
public class PlannerHomeController {

    private final PlannerHomeView view;
    private final PlannerModel model;

    public PlannerHomeController(PlannerHomeView view, PlannerModel model) {
        this.view = view;
        this.model = model;
        this.view.setLabel1(model.getUsername());
        this.view.CreateActivityListener(new CreateActivityListener());
        this.view.ExitListener(new ExitListener());
        this.view.ViewListener(new ViewListener());
    }

    public class CreateActivityListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PlannerCreateView ad = new PlannerCreateView();
            PlannerCreateController pcc = new PlannerCreateController(ad, model);
            ad.setVisible(true);
            view.setVisible(false);
        }
    }

    public class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView login = new LoginView();
            /*quando creiamo la nuova view dobbiamo istanziare anche il relativo controller per mantenere
            il riferimento alla view appena creata*/
            LoginController controllerLogin = new LoginController(login);
            view.setVisible(false);

        }
    }

    public class ViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PlannerActivityView ad1 = new PlannerActivityView();
            MaintenanceActivityModel ma = new MaintenanceActivityModel();
            PlannerActivityViewController pv = new PlannerActivityViewController(view,ma, ad1);
            ad1.setVisible(true);
            view.setVisible(false);
        }
    }

}
