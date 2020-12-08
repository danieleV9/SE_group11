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
import model.MaintainerModel;
import model.MaintenanceActivityModel;
import view.ActivityInfoView;
import view.MaintainerAvailabilityView;

/**
 *
 * @author lyuba
 */
public class MaintainerAvailabilityController {
    private MaintainerAvailabilityView view;
    private MaintenanceActivityModel model; //la prendo dalla pagina precedente
    private MaintainerModel modelma;
    private ActivityInfoView prev;
    

    public MaintainerAvailabilityController(ActivityInfoView prev,MaintainerAvailabilityView view, MaintenanceActivityModel model, MaintainerModel modelma) {
        this.prev = prev;
        this.view = view;
        this.model = model;
        this.modelma = modelma;
        this.view.addBackListener(new BackListener());
        this.view.addSelectedCellListener(new SelectedCellListener());
    }
    
    public class  BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prev.setVisible(true);
            view.setVisible(false);
        }
    }
    
    public class SelectedCellListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
        
    }
    
    public void populateView(){
       System.out.println(model.toString());
       String username="";
       int id=model.getId_Activity();
       view.setId(id);
       int week=model.getWeekNum(); //quella che prendo da attività selezionata
       view.setWeek(week);
       view.setActivityInfo(id+" - " + model.getFabbrica()+" - "+ model.getArea()+" - "+model.getTipology()+" - "+String.valueOf(model.getEstimatedTime())+" min");
       
    }

    
}
