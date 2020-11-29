/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ActivityDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.MaintenanceActivityModel;
import view.ActivityInfoView;
import view.PlannerActivityView;
import view.PlannerHomeView;

/**
 *
 * @author HP
 */
public class PlannerActivityViewController {
    private ActivityDao dao = new ActivityDao();
    private PlannerActivityView view = new PlannerActivityView();

    public PlannerActivityViewController(ActivityDao dao, PlannerActivityView view) {
        this.dao=dao;
        this.view=view;
    }
    
 public void AssegnaGestori() {
     
    //Gestore eliminazione
    ActionListener deleteListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JTable table=view.getjTable1();
          int selezionato = table.getSelectedRow();
          if(selezionato != -1){
          int id =  Integer.valueOf(table.getValueAt(selezionato, 1).toString());//id dell'attività da rimuovere dal DB
          if(dao.deleteActivity(id)){
             DefaultTableModel model = view.getModelTab();
             model.removeRow(selezionato);
          }        
        }
    } 
    
    } ;
   //Gestore visualizzazione Info 
    ActionListener infoListener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
          JTable table=view.getjTable1();
          int selezionato = table.getSelectedRow();
          if(selezionato != -1){
            int id=  Integer.valueOf(table.getValueAt(selezionato, 1).toString());//id dell'attività da visualizzare
            MaintenanceActivityModel mo= dao.viewActivity(id);
            ActivityInfoView vi = new ActivityInfoView(mo); //passa a nuova interfaccia
            ActivityInfoViewController controller2 = new ActivityInfoViewController(dao,vi);
            controller2.AssegnaGestori();     
            vi.setVisible(true);
            view.setVisible(false); 
          }
        }
    };  
    
    
    ActionListener backButton = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
          new PlannerHomeView().setVisible(true);
          view.setVisible(false);
        }
    };
            

   //assegno ai bottoni gli Action Listener 
   view.getDeleteButton().addActionListener(deleteListener);
   view.getInfoButton().addActionListener(infoListener);
   view.getBackHomeButton().addActionListener(backButton);
   
   
 } 
 
 
 
 
    
}
