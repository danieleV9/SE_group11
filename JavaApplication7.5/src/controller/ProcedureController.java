/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import model.ProcedureModel;
import model.SkillModel;
import view.AdminHomeView;
import view.ProcedureView;

/**
 *
 * @author HP
 */
public class ProcedureController {

    ProcedureView view;
    ProcedureModel proc;
    private final AdminHomeView prevView;//pagina precedente

    public ProcedureController(AdminHomeView prev, ProcedureView view, ProcedureModel proc) {
        this.prevView = prev;
        this.view = view;
        this.proc = proc;
        this.view.addCompetenceListener(new AddCompetenceListener());
        this.view.deleteCompetenceListener(new DeleteCompetenceListener());
        this.view.createProcedureListener(new CreateListener());
        this.view.deleteProcedureListener(new DeleteListener());
        this.view.openProcedureListener(new OpenListener());
        this.view.uploadListener(new UploadListener());
        this.view.resetListener(new ResetListener());
        this.view.backHomeListener(new BackHomeListener());
        populateTable();
        populateSkill();
    }

    public void populateTable() {
        List<ProcedureModel> list;
        list = proc.getAllProcedure();
        String competenze = "";
        for (int i = 0; i < list.size(); i++) {
            ProcedureModel p = list.get(i);
            String nomeprocedura = p.getNomeProc();
            List<SkillModel> listSkill = p.getProcedureSkill(nomeprocedura);
            for (SkillModel skill : listSkill) {
                competenze = competenze + skill.getDescription() + " / ";
            }
            String[] row = {nomeprocedura, competenze};
            competenze = "";
            view.getModelTab().addRow(row);
        }
    }

    public void populateSkill() {
        SkillModel skill = new SkillModel(0, "");
        List<SkillModel> list = skill.listSkills();
        for (int i = 0; i < list.size(); i++) {
            String competenza = list.get(i).toString();
            view.getjComboBox1().addItem(competenza);
            view.getjComboBox2().addItem(competenza);

        }
    }

    //GESTISCE L'INSERIMENTO DI UNA NUOVA COMPETENZA
    public class AddCompetenceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getjTable1();
            int selezionato = table.getSelectedRow();
            if (selezionato != -1) {
                aggiornaLista(selezionato, table);
            } else {
                view.displayErrorMessage("Select a procedure!");
            }
        }
    }

    //GESTISCE LA CANCELLAZIONE DI UNA NUOVA COMPETENZA
    public class DeleteCompetenceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getjTable1();
            int selezionato = table.getSelectedRow();
            if (selezionato != -1) {
                aggiornaLista2(selezionato, table);
            } else {
                view.displayErrorMessage("Select a procedure!");
            }
        }
    }

    public void aggiornaLista(int selezionato, JTable table) {

        SkillModel skill = new SkillModel(0, "");
        String description = view.getjComboBox1().getSelectedItem().toString();
        System.out.println(description);
        if ("Select".equals(description)) {
            view.displayErrorMessage("Select a skill !");
        } else {
            int id = skill.findSkill(description).getIdSkill();
            String nomeprocedura = table.getValueAt(selezionato, 0).toString();
            if (proc.addCompetence(nomeprocedura, id)) {
                for (int i = view.getModelTab().getRowCount(); i > 0; i--) {
                    view.getModelTab().removeRow(0);
                }
                populateTable();
                view.getjComboBox1().setSelectedIndex(0);
            }
        }
    }

    public void aggiornaLista2(int selezionato, JTable table) {

        SkillModel skill = new SkillModel(0, "");
        String description = view.getjComboBox2().getSelectedItem().toString();
        System.out.println(description);
        if ("Select".equals(description)) {
            view.displayErrorMessage("Select a skill !");
        } else {
            int id = skill.findSkill(description).getIdSkill();
            String nomeprocedura = table.getValueAt(selezionato, 0).toString();
            if (proc.removeCompetence(nomeprocedura, id)) {
                for (int i = view.getModelTab().getRowCount(); i > 0; i--) {
                    view.getModelTab().removeRow(0);
                }
                populateTable();
                view.getjComboBox2().setSelectedIndex(0); //riporto a 0 l'indice della select
            }
        }
    }

    //GESTISCE LA CREAZIONE DI UNA NUOVA PROCEDURA
    public class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameProc().getText();
            String path = view.getMessageLabel().getText();
            ProcedureModel m = new ProcedureModel("","");
            if (m.proceduraExists(name) || "".equals(name)) {
                view.displayErrorMessage("Name not valid (empty or already used)");
            } else if ("".equals(path) || "No file choosen".equals(path)) {
                view.displayErrorMessage("Upload a file! ");
            } else {
                proc.createProcedure(name, path);
                String[] row = {name};
                view.getModelTab().addRow(row);
                view.displayErrorMessage("Procedure created succesfully!");
            }
        }
    }

    public class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMessageLabel().setText("");
            view.getNameProc().setText("");
        }
    }

    public class BackHomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prevView.setVisible(true);
            view.setVisible(false);
        }
    }

    //GESTISCE L'ELIMINAZIONE DI UNA PROCEDURA
    public class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getjTable1();
            int selezionato = table.getSelectedRow();
            if (selezionato != -1) {
                String procedura = view.getModelTab().getValueAt(selezionato, 0).toString();

                if (proc.deleteProcedure(procedura)) {
                    view.getModelTab().removeRow(selezionato);
                }
            }
        }
    }

    public class OpenListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProcedureModel m = new ProcedureModel("","");
            int row = view.getjTable1().getSelectedRow();
            if (row != -1) {
                String proc = (view.getjTable1().getModel().getValueAt(row, 0)).toString();
                System.out.println(proc);
                String path = m.getPath(proc);
                System.out.println(path);
                try {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + path);
                } catch (Exception ex) {
                    System.out.println("errore nell'apertura file");
                }
            } else {
                view.displayErrorMessage("Select a procedure!");
            }
        }
    }

    public class UploadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int returnValue = view.getOpenFileChooser().showOpenDialog(view);
            File f = view.getOpenFileChooser().getSelectedFile();
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String filename = f.getAbsolutePath();
                view.getMessageLabel().setText(filename);
            } else {
                view.getMessageLabel().setText("No file choosen");
            }
        }

    }
}
