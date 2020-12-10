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
    private final MaintenanceActivityModel mamodel;

    private enum T {x, y};

    public PlannerCreateController(PlannerCreateView view, PlannerModel model) {
        this.view = view;
        this.model = model;
        this.mamodel = new MaintenanceActivityModel();
        this.view.CreateListener(new CreateListener());
        this.view.BackHomeListener(new BackHomeListener());
        this.view.InsertMaterialListener(new InsertMaterialListener());
    }

    public class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String area = "";
            T tipologi;
            tipologi = T.x;
            String tipology = "";
            String factory = "";
            String type = "";
            String workNotes = "";
            String description;
            String interruptible = "";
            String estimatedTime = "";
            String weekNumber = "";
            try {
                type = view.getTypeActivity();
                tipology = view.getTipology();
                factory = view.getFactory();
                area = view.getArea();
                workNotes = view.getWorkNotes();
                description = view.getDescription();
                estimatedTime = view.getEstimatedTime();
                weekNumber = view.getWeekNumber();
                interruptible = view.getInterruptible();
                if (description.equals("") || factory.equals("") || type.equals("") || weekNumber.equals("") || area.equals("") || tipologi.equals("") || estimatedTime.equals("")) {
                    view.displayErrorMessage("fill all fields!", "Attention!");
                    System.out.println("query return null");
                } else {
                    int time = Integer.parseInt(estimatedTime);
                    int numberWeek = Integer.parseInt(weekNumber);
                    mamodel.insertActivity(numberWeek, workNotes, type, factory, tipology, time, description, area);
                    view.displaySuccessfullyMessage("Activity Created Succesfully!");
                }
            } catch (Exception ex) {
                System.out.println("" + ex);
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }

    public class BackHomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PlannerHomeView ad2 = new PlannerHomeView();
            PlannerHomeController contr = new PlannerHomeController(ad2, model);
            ad2.setVisible(true);
            view.setVisible(false);
        }
    }

    public class InsertMaterialListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PlannerMaterialView ad1 = new PlannerMaterialView();
            ad1.setVisible(true);
            view.setVisible(true);
        }
    }
}
