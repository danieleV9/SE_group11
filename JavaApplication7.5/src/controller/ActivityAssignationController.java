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
            int col = view.getSelectedColumn();
            int row = view.getSelectedRow();
            if (row != -1) {
                if (col != -1 && col != 0 && col != 1) {// se sto cliccando su uno slot temporale
                    String val = (String) view.getTable().getValueAt(row, col);
                    String username = (String) view.getTable().getValueAt(row, 0);
                    String day = view.getDay();
                    System.out.println("valore della cella " + ":" + val);
                    String[] splitString = val.split(" ");
                    int st_val = Integer.parseInt(splitString[0]);
                    if (st_val == 0) {
                        view.displayErrorMessage("Maintainer " + username + " is not available in this slot of time, \nchoose another one in which availability is not 0 min!");
                    } else { //orario va bene quindi posso assegnare l'attività al maintainer 
                        int numDay = view.getNumDay();
                        String data = day + " " + String.valueOf(numDay);
                        boolean result = modelact.assignNewActivity(modelact.getId_Activity(), username, data);
                        if (result == true) {
                            view.displayErrorMessage("Activity assigned succesfully to the Maintainer " + username + ", on " + day + " " + numDay);
                            view.setSendEnabled(false); //disattivo il pulsante se ho già assegnato l'attività
                        } else {
                            view.displayErrorMessage("Error, cannot assign correctly the activity, try again.\n If the problem persists contact the System Administrator!");
                        }
                    }
                } else if (col == 0 || col == 1) {
                    view.displayErrorMessage("Select a temporal slot to assign the activity to the Maintainer!");
                }
            } else // se non ho selzionato nulla
            {
                view.displayErrorMessage("Select a slot to assign the activity to the Maintainer!");
            }
        }

    }

    public class SelectedCellListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("Ho cliccato sulla tabella delle disponibilità");
            int r = view.getSelectedRow();
            System.out.println("riga" + r);
            int c = view.getSelectedColumn();
            System.out.println("colonna" + c);
            String nomeCol = view.getTable().getColumnName(c);
            String val = (String) view.getTable().getValueAt(r, c);
            System.out.println("valore della cella " + nomeCol + ":" + val);

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

    public void populateView() {
        String ora = "";
        String note = modelact.getWorkspaceNotes();
        view.setNoteArea(note);
        ProcedureModel procmodel = new ProcedureModel("","");
        String username = modelma.getUsername();
        SkillModel skill = new SkillModel(0, "");
        int id = modelact.getId_Activity();
        int week = modelact.getWeekNum(); //quella che prendo da attività selezionata
        view.setWeek(week);
        view.setActivityInfo(id + " - " + modelact.getFabbrica() + " - " + modelact.getArea() + " - " + modelact.getTipology() + " - " + String.valueOf(modelact.getEstimatedTime()) + " min");
        int numDay = modelma.getNumGiorno(username, week, this.giorno);
        System.out.println(numDay);
        if (numDay != 0) {
            view.setNumDayText(numDay);
        }
        String dayString = this.translateDay(this.giorno);
        view.setDayText(dayString);
        view.setMaintainerText(username);
        String fasce = modelma.getDisponibilitaGiorno(username, week, this.giorno);
        int percent = this.calcoloPercentualeDisponibilita(fasce);
        view.setPercenText(String.valueOf(percent) + "%");
        String procedura = modelact.findProcedura(id); //procedura associata ad attività
        List<SkillModel> comp = procmodel.getProcedureSkill(procedura);//lista di competenze associate alla procedura
        int numCompProc = comp.size(); //numero di competenze richieste
        int countCommon = 0; //contatore delle competenze comuni
        List<SkillModel> skillMa = skill.listSkillsMA(username);//lista di comptenze associate al maintainer
        for (SkillModel ski : skillMa) { //conto le competeze in comune 
            if (comp.contains(ski)) {
                countCommon++;
            }
        }
        String row[] = new String[9];
        row[0] = username; //prima colonna contiene username del maintainer selezionato
        String comuni = countCommon + "/" + numCompProc; //seconda colonna contiene le skill in comune con quelle richieste.
        row[1] = comuni;
        String[] ore = scriviFasce(fasce); // se mi restituisce array vuoto allora nel db non c'è nulla per la settimana cercata e il maintainer selezionato
        int contatore = 2;
        System.out.println(ore.length);
        if (ore.length != 0) {//se array non è vuoto
            for (String x : ore) { //sette colonne, una per ogni giorno, devono contenere la percentuale di disponibilità giornaliera
                ora = x;
                row[contatore] = ora + " min";
                contatore++;
            }
        } else {
            for (contatore = 2; contatore < 9; contatore++) {
                row[contatore] = 0 + " min";
            }
        }

        view.getTable().addRow(row);
        colourTable();
    }

    class MyTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Color getBackground() {
            return super.getBackground();
        }
    }

    public void changeTable(JTable table, int column_index) { //coloro le celle in base alla disponibilità oraria in minuti
        table.getColumnModel().getColumn(column_index).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String getVAL = table.getValueAt(row, column).toString();
                String[] splitString = getVAL.split(" ");
                int st_val = Integer.parseInt(splitString[0]);
                if (st_val == 0) {
                    c.setBackground(Color.RED);
                } else if (st_val > 0 && st_val < 30) {
                    c.setBackground(Color.ORANGE);
                } else if (st_val == 30) {
                    c.setBackground(Color.YELLOW);
                } else if (st_val > 30 && st_val < 60) {
                    c.setBackground(Color.GREEN);
                } else if (st_val == 60) {
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

    public String translateDay(int day) {
        String dayString = "";
        switch (day) {
            case 1:
                dayString = "Monday";
                break;
            case 2:
                dayString = "Tuesday";
                break;
            case 3:
                dayString = "Wednesday";
                break;
            case 4:
                dayString = "Thursday";
                break;
            case 5:
                dayString = "Friday";
                break;
            case 6:
                dayString = "Saturday";
                break;
            case 7:
                dayString = "Sunday";
                break;
            default:
                break;
        }
        return dayString;
    }

    public String[] scriviFasce(String fasce) {

        if (!fasce.equals("")) {
            String[] disp = fasce.split(" ");
            return disp;
        } else {
            String[] disp = {};
            return disp;
        }
    }

}
