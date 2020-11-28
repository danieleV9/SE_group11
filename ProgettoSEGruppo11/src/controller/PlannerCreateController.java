/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.PlannerModel;
import view.PlannerCreateView;
import view.PlannerHomeView;
import view.PlannerMaterialView;

/**
 *
 * @author jenni
 */
public class PlannerCreateController {
    private final PlannerCreateView view;
    private final PlannerModel model;

    public PlannerCreateController(PlannerCreateView view, PlannerModel model) {
        this.view = view;
        this.model = model;
        this.view.CreateListener(new CreateListener());
        this.view.BackHomeListener(new BackHomeListener()); 
        this.view.InsertMaterialListener(new InsertMaterialListener());
    }
    
    public class CreateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String area = "";
            String tipology = "";  
            String factory = "";
            String type = "";
            String workNotes = "";  
            String description = "";
            String interruptible;
            String estimatedTime;
            String weekNumber;
            try{
                area = view.getArea();
                tipology = view.getTipology();
                factory = view.getFactory();
                type = view.getArea();
                workNotes = view.getWorkNotes();
                description = view.getDescription();
                estimatedTime = view.getEstimatedTime();
                int time = Integer.parseInt(estimatedTime);
                weekNumber = view.getWeekNumber();
                int numberWeek = Integer.parseInt(weekNumber);
                view.getInterruptible();
                if(description.equals("") || factory.equals("") || type.equals("") || weekNumber.equals("") || area.equals("") || tipology.equals("") || estimatedTime.equals("")){
                    view.displayErrorMessage("Inserire tutti i campi!","Attenzione");
                    System.out.println("query return null");
                }
        }catch(Exception ex){
                System.out.println(""+ex);
                view.displayErrorMessage(ex.getMessage());
            }
            view.displaySuccessfullyMessage("Activity Created Succesfully!");
        }
    }
    
    public class BackHomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
        PlannerHomeView ad2 = new PlannerHomeView();
        ad2.setVisible(true);
        view.setVisible(false);
        }
    }
    
    public class InsertMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
        PlannerMaterialView ad1 = new PlannerMaterialView();  
        ad1.setVisible(true); 
        view.setVisible(true);
        }
    }
}
