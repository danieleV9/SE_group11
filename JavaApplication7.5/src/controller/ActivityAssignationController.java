/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import model.MaintainerModel;
import model.MaintenanceActivityModel;
import model.ProcedureModel;
import model.SkillModel;
import view.ActivityAssignationView;
import view.MaintainerAvailabilityView;

/**
 *
 * @author dava9
 */
public class ActivityAssignationController {
    private ActivityAssignationView view;
    private MaintainerAvailabilityView prev;
    private MaintenanceActivityModel modelact;
    private MaintainerModel modelma;
    private int giorno;

    public ActivityAssignationController(ActivityAssignationView view, MaintainerAvailabilityView prev, MaintenanceActivityModel modelact, MaintainerModel modelma, int giorno) {
        this.view = view;
        this.prev = prev;
        this.modelact = modelact;
        this.modelma = modelma;
        this.giorno = giorno;
        this.view.addBackListener(new BackListener());
        this.view.addSendListener(new SendListener());
        this.view.addSelectedCellListener(new SelectedCellListener());
        
    }
    
    public class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            prev.setVisible(true);
        }
        
    }
    
    public class SendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    
    public class SelectedCellListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
    
    public void populateView(){
       ProcedureModel procmodel = new ProcedureModel();
       String username= modelma.getUsername();
       SkillModel skill=new SkillModel();
       int id=modelact.getId_Activity();
       int week=modelact.getWeekNum(); //quella che prendo da attività selezionata
       view.setWeek(week);
       view.setActivityInfo(id+" - " + modelact.getFabbrica()+" - "+ modelact.getArea()+" - "+modelact.getTipology()+" - "+String.valueOf(modelact.getEstimatedTime())+" min");
       String procedura=modelact.findProcedura(id); //procedura associata ad attività
       List<SkillModel> comp= procmodel.getProcedureSkill(procedura);//lista di competenze associate alla procedura
    }
    
    
}
