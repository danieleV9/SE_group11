/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ActivityDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.MaintenanceActivityModel;
import view.ActivityInfoView;
import view.PlannerActivityView;


/**
 *
 * @author HP
 */
public class ActivityInfoViewController {
    private MaintenanceActivityModel ma;
    private ActivityInfoView view;

    public ActivityInfoViewController(MaintenanceActivityModel ma, ActivityInfoView view) {
        this.ma=ma;
        this.view=view;
        this.view.addBackListener(new BackListener());//tasto indietro
        this.view.addUpdateListener(new UpdateListener()); //tasto per aggiornare note
        popolaInfo(ma);
    }
   

   public class BackListener implements ActionListener {
       public void actionPerformed(ActionEvent e){
         view.setVisible(false);
         PlannerActivityView listView = new PlannerActivityView();       
         PlannerActivityViewController controller1 = new PlannerActivityViewController(ma,listView);
         listView.setVisible(true);
         view.setVisible(false);  
        }
    } 

    public class UpdateListener implements ActionListener {
         public void actionPerformed(ActionEvent e){
         String note = view.getNotesArea().getText();
         int id = Integer.valueOf(view.getId().getText());
         ma.aggiornaNote(note,id);
         view.displayMessage("Notes updated succesfully");
         
        }
    } 
      
    
    void popolaInfo(MaintenanceActivityModel a) {   
        view.getActivityText().setText( a.getId_Activity()+" - " + a.getFabbrica()+" - "+ a.getArea()+" - "+a.getTipology()+" - "+a.getEstimatedTime()+" min");
        view.getDescriptionArea().setText(a.getDescription());
        view.getNotesArea().setText(a.getWorkspaceNotes());
        view.getWeekText().setText(String.valueOf(a.getWeekNum()));
        view.getId().setText(String.valueOf(a.getId_Activity()));
    }
    
    
}
