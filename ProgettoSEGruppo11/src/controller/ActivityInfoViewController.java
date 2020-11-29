/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ActivityDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ActivityInfoView;
import view.PlannerActivityView;


/**
 *
 * @author HP
 */
public class ActivityInfoViewController {
    private ActivityDao dao = new ActivityDao();
    private ActivityInfoView view = new ActivityInfoView();

    public ActivityInfoViewController(ActivityDao dao, ActivityInfoView view) {
        this.dao=dao;
        this.view=view;
    }
    
     public void AssegnaGestori() {
        
    //Gestore aggiornamento note
    ActionListener updateNotesListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        String note = view.getNotesArea().getText();
        int id = Integer.valueOf(view.getId().getText());
        dao.aggiornaNote(note,id);
        }
    
     };
     
    //Gestore goBack 
    ActionListener goBackListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ActivityDao dao = new ActivityDao();
            PlannerActivityView listView = new PlannerActivityView();       
            PlannerActivityViewController controller1 = new PlannerActivityViewController(dao,listView);
            controller1.AssegnaGestori();
            listView.setVisible(true);
            view.setVisible(false);
        }
    
     };
    
    
    view.getGoBackButton().addActionListener(goBackListener);
    view.getUpdateNotesButton().addActionListener(updateNotesListener);
   }
    
    
}
