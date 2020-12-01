/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ActivityDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.MaintenanceActivityModel;
import model.PlannerModel;
import view.ActivityInfoView;
import view.PlannerActivityView;
import view.PlannerHomeView;

/**
 *
 * @author HP
 */
public class PlannerActivityViewController {
    private MaintenanceActivityModel ma ;
    private PlannerActivityView view ;

    public PlannerActivityViewController(MaintenanceActivityModel ma, PlannerActivityView view) {
        this.ma=ma;
        this.view=view;
    }
    
 public void assegnaGestori() {
     
    //Gestore eliminazione
    ActionListener deleteListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JTable table=view.getjTable1();
          int selezionato = table.getSelectedRow();
          if(selezionato != -1){
          int id =  Integer.valueOf(table.getValueAt(selezionato, 1).toString());//id dell'attività da rimuovere dal DB
          if(ma.deleteActivity(id)){
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
            MaintenanceActivityModel mo= ma.viewActivity(id);
            ActivityInfoView vi = new ActivityInfoView(); //passa a nuova interfaccia
            ActivityInfoViewController controller2 = new ActivityInfoViewController(mo,vi);
            controller2.assegnaGestori();     
            controller2.popolaInfo(mo);
            vi.setVisible(true);
            view.setVisible(false); 
          }
        }
    };  
    
    
    ActionListener backButton = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
          PlannerHomeView pv = new PlannerHomeView();
          PlannerModel pm = new PlannerModel("","");
          PlannerHomeController phc = new PlannerHomeController(pv,pm);

          phc.assegnaGestore();

          pv.setVisible(true);
          view.setVisible(false);
          
        }
    };
            

   //assegno ai bottoni gli Action Listener 
   view.getDeleteButton().addActionListener(deleteListener);
   view.getInfoButton().addActionListener(infoListener);
   view.getBackHomeButton().addActionListener(backButton);
   
   
 } 
 
 public void populateTable(){
     List<MaintenanceActivityModel> list;
     list=ma.getAllActivity();
     for(int i=0; i<list.size(); i++){
            MaintenanceActivityModel m =list.get(i);
            int id = m.getId_Activity();
            String id2=String.valueOf(id);
            int estimatedTime= m.getEstimatedTime();
            String time=String.valueOf(estimatedTime);      
            String area = m.getFabbrica()+" - "+m.getArea();
            String tipology= m.getTipology();
            int week = m.getWeekNum();
            String week2=String.valueOf(week);
            String [] row = {week2,id2,area,tipology,time};
            /*String[] row2 = {null};
            if(i!=0 ){
             if(week != list.get(i-1).getWeekNum()){
             modelTab.addRow(row2);
             } 
            }
            else modelTab.addRow(row2);*/
            view.getModelTab().addRow(row);
        }
 }
 
 
}
