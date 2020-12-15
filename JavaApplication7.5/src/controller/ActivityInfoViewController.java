/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.MaintenanceActivityModel;
import view.ActivityInfoView;
import view.MaintainerAvailabilityView;
import view.PlannerActivityView;
import java.util.List;
import model.ProcedureModel;
import model.SkillModel;

/**
 *
 * @author HP
 */
public class ActivityInfoViewController {

    private final MaintenanceActivityModel ma;
    private final ActivityInfoView view;
    private final PlannerActivityView prev;

    public ActivityInfoViewController(PlannerActivityView prev,MaintenanceActivityModel ma, ActivityInfoView view) {
        this.ma = ma;
        this.view = view;
        this.prev=prev;
        this.view.addBackListener(new BackListener());//tasto indietro
        this.view.addUpdateListener(new UpdateListener()); //tasto per aggiornare note
        this.view.addForwardListener(new ForwardListener()); //tasto per validare attività e andare avanti
        this.view.addOpenListener(new OpenListener());
        popolaInfo(ma);
    }

    public class ForwardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MaintainerAvailabilityView newView = new MaintainerAvailabilityView();
            MaintenanceActivityModel a = new MaintenanceActivityModel();
            int id = Integer.valueOf(view.getId().getText());
            a = a.viewActivity(id); //passo al controller l'attività con quell'id
            if (!a.assignedActivity(id)) {//se l'attività non è già stata assegnta 
                System.out.println(a.toString());
                MaintainerAvailabilityController controller = new MaintainerAvailabilityController(view, newView, a);
                controller.populateView();
                newView.setVisible(true);
                view.setVisible(false);
            } else // se l'attività è già stata assegnata
            {
                view.displayMessage("This activity has already been assigned to a Maintainer!\n Go back to assign a new activity.");
            }
        }
    }

    public class BackListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            prev.setVisible(true);
            view.setVisible(false);
        }
    }

    public class UpdateListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String note = view.getNotesArea().getText();
            int id = Integer.valueOf(view.getId().getText());
            ma.aggiornaNote(note, id);
            view.displayMessage("Notes updated succesfully");

        }
    }
    public class OpenListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProcedureModel m = new ProcedureModel("","");
            String nomeproc = view.getjTextField1().getText();
            String path= m.getPath(nomeproc);
                try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
                } catch (Exception ex) {
                    System.out.println("errore nell'apertura file");
                }
            } 
        }
    

    void popolaInfo(MaintenanceActivityModel a) {
        view.getActivityText().setText(a.getId_Activity() + " - " + a.getFabbrica() + " - " + a.getArea() + " - " + a.getTipology() + " - " + a.getEstimatedTime() + " min");
        view.getDescriptionArea().setText(a.getDescription());
        view.getjTextField1().setText(a.getProcedura().getNomeProc());
        view.getNotesArea().setText(a.getWorkspaceNotes());
        SkillModel skill = new SkillModel(0, "");
        ProcedureModel proc = new ProcedureModel("","");
        String procedura = a.findProcedura(a.getId_Activity());
        List<SkillModel> comp = proc.getProcedureSkill(procedura);//lista di competenze associate alla procedura
        view.setSkillArea(comp);
        view.getWeekText().setText(String.valueOf(a.getWeekNum()));
        view.getId().setText(String.valueOf(a.getId_Activity()));
    }

}
