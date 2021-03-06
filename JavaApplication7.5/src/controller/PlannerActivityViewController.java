/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
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

    private final MaintenanceActivityModel ma;
    private final PlannerActivityView view;
    private final PlannerHomeView prev;

    public PlannerActivityViewController(PlannerHomeView prev,MaintenanceActivityModel ma, PlannerActivityView view) {
        this.ma = ma;
        this.view = view;
        this.prev=prev;
        this.view.addBackListener(new BackListener()); //tasto indietro
        this.view.addDeleteListener(new DeleteListener()); //tasto delete
        this.view.addInfoListener(new InfoListener()); //tasto visualizzainfo
        this.view.addItemListener(new itemListener()); //cambiamento settimana selezionata
        populateTable();
    }

    //gestore cancellazione
    public class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getjTable1();
            int selezionato = table.getSelectedRow();
            if (selezionato != -1) {
                int id = Integer.valueOf(table.getValueAt(selezionato, 1).toString());//id dell'attività da rimuovere dal DB
                if (ma.deleteActivity(id)) {
                    DefaultTableModel model = view.getModelTab();
                    model.removeRow(selezionato);
                }
                else{
                    view.displayErrorMessage("Cannot delete selected activity");
                }
            }
            else{
                view.displayErrorMessage("Select an activity");
            }
        }
    }

    //Gestore visualizzazione Info 
    public class InfoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getjTable1();
            int selezionato = table.getSelectedRow();
            if (selezionato != -1) {
                int id = Integer.valueOf(table.getValueAt(selezionato, 1).toString());//id dell'attività da visualizzare
                MaintenanceActivityModel mo = ma.viewActivity(id);
                ActivityInfoView vi = new ActivityInfoView(); //passa a nuova interfaccia
                ActivityInfoViewController controller2 = new ActivityInfoViewController(view,mo, vi);
                vi.setVisible(true);
                view.setVisible(false);
            }
            else{
                view.displayErrorMessage("Select an activity");
            }
        }
    }

    //gestore Go Back
    public class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prev.setVisible(true);
            view.setVisible(false);
        }
    }

    public class itemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object item = event.getItem();
                int rows = view.getjTable1().getRowCount();
                int rows1 = view.getModelTab().getRowCount();
                for (int i = 0; i < rows1; i++) {
                    view.getModelTab().removeRow(0);
                }
                String item1 = item.toString();
                int numWeek = Integer.parseInt(item1);
                populateTable(numWeek);
            }
        }
    }

    public void populateTable() {
        List<MaintenanceActivityModel> list;
        list = ma.getAllActivity();
        for (int i = 0; i < list.size(); i++) {
            MaintenanceActivityModel m = list.get(i);
            int id = m.getId_Activity();
            String id2 = String.valueOf(id);
            int estimatedTime = m.getEstimatedTime();
            String time = String.valueOf(estimatedTime);
            String area = m.getFabbrica() + " - " + m.getArea();
            String tipology = m.getTipology();
            int week = m.getWeekNum();
            String week2 = String.valueOf(week);
            String[] row = {week2, id2, area, tipology, time};
            view.getModelTab().addRow(row);
        }
    }

    public void populateTable(int weekNum) {
        List<MaintenanceActivityModel> list;
        list = ma.getAllActivity(weekNum);
        for (int i = 0; i < list.size(); i++) {
            MaintenanceActivityModel m = list.get(i);
            int id = m.getId_Activity();
            String id2 = String.valueOf(id);
            int estimatedTime = m.getEstimatedTime();
            String time = String.valueOf(estimatedTime);
            String area = m.getFabbrica() + " - " + m.getArea();
            String tipology = m.getTipology();
            int week = m.getWeekNum();
            String week2 = String.valueOf(week);
            String[] row = {week2, id2, area, tipology, time};
            view.getModelTab().addRow(row);
        }
    }

}
