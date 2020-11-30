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
import view.PlannerActivityView;
//import view.PlannerCreateView;
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
       // this.view.CreateActivityListener(new CreateActivityListener());
       // this.view.ExitListener(new ExitListener()); 
       // this.view.ViewListener(new ViewListener());
    }
    
    public class CreateActivityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
        //PlannerCreateView ad = new PlannerCreateView();  
        //ad.setVisible(true);
        view.setVisible(false);
        }
    }
    
    public class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);            
        }
    }
    
    
    public void assegnaGestore(){
    ActionListener viewListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
        PlannerActivityView ad1 = new PlannerActivityView();
        MaintenanceActivityModel ma = new MaintenanceActivityModel();
        PlannerActivityViewController pv = new PlannerActivityViewController(ma,ad1);
        pv.populateTable();
        pv.assegnaGestori();
        ad1.setVisible(true);
        view.setVisible(false);
        }
    };
    
    view.getView().addActionListener(viewListener);
    }
    
    
    
}
