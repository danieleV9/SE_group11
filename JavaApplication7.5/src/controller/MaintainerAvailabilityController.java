/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.MaintainerModel;
import model.MaintenanceActivityModel;
import model.ProcedureModel;
import model.SkillModel;
import view.ActivityAssignationView;
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

    public MaintainerAvailabilityController(ActivityInfoView prev, MaintainerAvailabilityView view, MaintenanceActivityModel model, MaintainerModel modelma) {
        this.prev = prev;
        this.view = view;
        this.model = model;
        this.modelma = modelma;
        this.view.addBackListener(new BackListener());
        this.view.addSelectedCellListener(new SelectedCellListener());
        this.view.getjTable().setDefaultRenderer(Object.class, new MyTableCellRenderer());
    }

    public class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prev.setVisible(true);
            view.setVisible(false);
        }
    }

    public class SelectedCellListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Ho cliccato sulla tabella delle disponibilità");
            int r = view.getSelectedRow();
            System.out.println("riga" + r);
            int c = view.getSelectedColumn();
            System.out.println("colonna" + c);
            String nomeCol = view.getTable().getColumnName(c);
            String val = (String) view.getTable().getValueAt(r, c);
            System.out.println("valore della cella " + nomeCol + ":" + val);
            if (r != -1 && c != -1 && c != 0 && c != 1) {
                if (!model.assignedActivity(model.getId_Activity())) { // se l'attività non è già stata assegnata vado avanti, questo controllo serve quando clicco tasto back dalla schermata successiva dopo aver assegnato attività
                    int id = view.getId();
                    MaintenanceActivityModel m = model.viewActivity(id);
                    MaintainerModel sel = (MaintainerModel) modelma.findUsername((String) view.getTable().getValueAt(r, 0));
                    int giorno = c - 1;
                    ActivityAssignationView newview = new ActivityAssignationView();
                    newview.setVisible(true);
                    view.setVisible(false);
                    ActivityAssignationController newcontroller = new ActivityAssignationController(newview, view, m, sel, giorno);
                    newcontroller.populateView();
                } else {
                    view.displayErrorMessage("This activity has already been assigned to a Maintainer!\n Go back to assign a new activity.");
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public void populateView() {
        ProcedureModel procmodel = new ProcedureModel("","");
        List<MaintainerModel> list = modelma.listMaintainersDisponibili(); //lista di maintainer disponibili
        String username = "";
        String fasce = "";
        SkillModel skill = new SkillModel(0, "");
        int id = model.getId_Activity();
        view.setId(id);
        int week = model.getWeekNum(); //quella che prendo da attività selezionata
        view.setWeek(week);
        view.setActivityInfo(id + " - " + model.getFabbrica() + " - " + model.getArea() + " - " + model.getTipology() + " - " + String.valueOf(model.getEstimatedTime()) + " min");
        String procedura = model.findProcedura(id); //procedura associata ad attività
        List<SkillModel> comp = procmodel.getProcedureSkill(procedura);//lista di competenze associate alla procedura
        view.setSkillArea(comp);
        int numCompProc = comp.size();
        for (MaintainerModel m : list) {
            int countCommon = 0; //contatore delle competenze comuni
            List<SkillModel> skillMa = skill.listSkillsMA(m.getUsername());//lista di competenze associate al maintainer
            for (SkillModel ski : skillMa) { //conto le competeze in comune per ogni maintainer
                if (comp.contains(ski)) {
                    countCommon++;
                }
            }
            username = m.getUsername(); //prima colonna
            String comuni = countCommon + "/" + numCompProc; //seconda colonna
            String row[] = new String[9];
            row[0] = username;
            row[1] = comuni;
            for (int i = 1; i < 8; i++) { //sette colonne, una per ogni giorno, devono contenere la percentuale di disponibilità giornaliera
                fasce = modelma.getDisponibilitaGiorno(username, week, i);
                int perc = calcoloPercentualeDisponibilita(fasce);
                row[i + 1] = Integer.toString(perc) + " %";
            }
            view.getTable().addRow(row);
        }
        colourTable();
    }

    public int calcoloPercentualeDisponibilita(String disponibilita) {
        if (!disponibilita.equals("")) {
            String[] disp = disponibilita.split(" ");
            int somma = 0;
            int media = 0;
            int percentuale = 0;
            for (int i = 0; i < 7; i++) {
                somma = somma + Integer.valueOf(disp[i]);
            }
            media = somma / 7;
            percentuale = (100 * media) / 60;
            return percentuale;
        } else {
            return 0;
        }
    }

    class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Color getBackground() {
            return super.getBackground();
        }
    }

    public void changeTable(JTable table, int column_index) {
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String getVAL = table.getValueAt(row, column).toString();
                String[] splitString = getVAL.split(" ");
                int st_val = Integer.parseInt(splitString[0]);
                if (st_val == 0) {
                    c.setBackground(Color.RED);
                } else if (st_val > 0 && st_val < 50) {
                    c.setBackground(Color.ORANGE);
                } else if (st_val == 50) {
                    c.setBackground(Color.YELLOW);
                } else if (st_val > 50 && st_val < 100) {
                    c.setBackground(Color.GREEN);
                } else if (st_val == 100) {
                    c.setBackground(Color.CYAN);
                }

                return c;
            }
        });
    }

    public void colourTable() {
        int numrow = view.getTable().getRowCount();
        for (int i = 0; i < numrow; i++) {
            for (int j = 2; j < 9; j++) {
                changeTable(view.getjTable(), j);
            }
        }
    }

}
