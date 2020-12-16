/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.MaintenanceActivityModel;
import model.PlannerModel;
import model.ProcedureModel;
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

    public PlannerCreateController(PlannerCreateView view, PlannerModel model) {
        this.view = view;
        this.model = model;
        this.mamodel = new MaintenanceActivityModel();
        this.view.CreateListener(new CreateListener());
        this.view.BackHomeListener(new BackHomeListener());
        this.view.InsertMaterialListener(new InsertMaterialListener());
        this.populateProcedures();
    }

    public class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String area;
            String tipology;
            String factory;
            String type;
            String workNotes;
            String description;
            boolean interruptible;
            String estimatedTime;
            String weekNumber;
            String procedure;
            try {
                type = view.TypeActivity();
                tipology = view.getTipology();
                factory = view.getFactory();
                area = view.getArea();
                workNotes = view.getWorkNotes();
                description = view.getDescription();
                estimatedTime = view.getEstimatedTime();
                weekNumber = view.getWeekNumber();
                interruptible = view.getInterruptible();
                procedure = view.getProcedure();

                if (weekNumber.equals("Select") || type.equals("Select") || procedure.equals("")||description.equals("") || factory.equals("") || tipology.equals("") || area.equals("") || estimatedTime.equals("")) {
                    view.displayErrorMessage("fill all fields!", "Attention!");
                } else {
                    int time = Integer.parseInt(estimatedTime);
                    int numberWeek = Integer.parseInt(weekNumber);
                    ProcedureModel p = new ProcedureModel("","");
                    String path= p.getPath(procedure);
                    mamodel.insertActivity(numberWeek, workNotes, type, factory, tipology, time, description, area, interruptible, new ProcedureModel(procedure,path));
                    view.displaySuccessfullyMessage("Activity Created Succesfully!");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
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
    
    public void populateProcedures(){
        ProcedureModel proc = new ProcedureModel("","");
        List<ProcedureModel> list = proc.getAllProcedure();
        for(int i=0; i<list.size();i++){
            String nomeproc= list.get(i).getNomeProc();
            view.getProcedureField().addItem(nomeproc);
        }
        
    }
}
