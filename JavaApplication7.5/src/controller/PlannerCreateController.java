/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import model.MaintenanceActivityModel;
import model.MaterialModel;
import model.PlannerModel;
import model.ProcedureModel;
import view.PlannerCreateView;
import view.PlannerHomeView;


/**
 *
 * @author jenni
 */
public class PlannerCreateController {

    private final PlannerCreateView view;
    private final PlannerModel model;
    private final MaintenanceActivityModel mamodel;
    private final MaterialModel mmodel;
    private LinkedList<MaterialModel> list;

    public PlannerCreateController(PlannerCreateView view, PlannerModel model) {
        this.view = view;
        this.model = model;
        this.mamodel = new MaintenanceActivityModel();
        this.view.CreateListener(new CreateListener());
        this.view.BackHomeListener(new BackHomeListener());
        this.view.InsertMaterialListener(new InsertMaterialListener());
        this.mmodel = new MaterialModel("");
        list = new LinkedList<>();
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
                    System.out.println("pippo");
                    int idattivita = mamodel.insertActivity(numberWeek, workNotes, type, factory, tipology, time, description, area, interruptible, new ProcedureModel(procedure,path));
                    System.out.println("pluto");
                    for (MaterialModel l:list) // lista dei materiali selezionati dutante creazione attività
                        mmodel.insertMaterial(l.getMaterialName(), idattivita);
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
            MaterialModel ma = view.displayMessage();
            if (ma!=null && !list.contains(ma)) {
                list.add(ma);
                System.out.println(list);
            }
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
