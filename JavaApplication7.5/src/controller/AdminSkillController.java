/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.AdminModel;
import model.SkillModel;
import view.AdminHomeView;
import view.AdminSkillView;

/**
 *
 * @author jenni
 */
public class AdminSkillController {

    private final AdminSkillView view;
    private final AdminModel model;
    private final AdminHomeView prev; //pagina precedente
    private SkillModel skmodel = new SkillModel(0, "");

    public AdminSkillController(AdminHomeView prev, AdminSkillView view, AdminModel model) {
        this.view = view;
        this.model = model;
        this.prev = prev;
        this.view.InsertListener(new InsertListener());
        this.view.BackListener(new BackListener());
        this.view.ModifyListener(new ModifyListener());
        this.view.DeleteListener(new DeleteListener());
        this.view.ConfirmChangeListener(new ConfirmChangeListener());
        this.view.Back1Listener(new Back1Listener());
    }

    

    public void populateTables() {
        List<SkillModel> listsk = skmodel.listSkills();
        for (int i = 0; i < listsk.size(); i++) {
            skmodel = listsk.get(i);
            int idSkill = skmodel.getIdSkill();
            String id = Integer.toString(idSkill);
            String description = skmodel.getDescription();
            String array[] = {id, description};
            view.getTableModel().addRow(array);
        }
    }

    public class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<SkillModel> listsk = skmodel.listSkills();
            
            String description = "";
            try {
                description = view.getDescription();
                if (description.equals("")) {
                    view.displayErrorMessage("Insert a description!", "Attention");
                    
                } else if (listsk.contains(skmodel.findSkill(description))) {
                    view.displayErrorMessage("The description is already present!", "Attention");
                    
                } else {
                    skmodel.insertSkill(description);
                    view.displaySuccessfullyMessage("Skill Created Succesfully!");
                    
                    int idSkill = skmodel.getIdSkill();
                    String id = Integer.toString(idSkill);
                    String array[] = {id, description};
                    view.getTableModel().addRow(array);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }

    public class ModifyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.showAddComp(true);
        }
    }

    public class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prev.setVisible(true);
            view.setVisible(false);
        }
    }

    public class ConfirmChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idSkill = 0;
            boolean x = false;
            String vecchio = "";
            int selezionato = view.getSelectedRow();//riga selezionata della tabella competenza
            if (selezionato != -1) {
                int i = Integer.parseInt(view.getIdSelected(selezionato));
                vecchio = view.getDescriptionSelected(selezionato);
                
                idSkill = i;
                String descrizione = view.getDescription1();
                
                if (descrizione.equals("")) {
                    view.displayErrorMessage("The description can not be empty!");
                } else if (descrizione.equals(vecchio)) {
                    view.displayErrorMessage("New competence must be different from the previous");
                } else {
                    x = skmodel.modifySkill(idSkill, descrizione);

                    if (x) {
                        //view.showAddComp(true);
                        view.displayErrorMessage("Updated succesfully");
                        view.setDescriptionSelected(selezionato, 1, descrizione); //visualizza modificaaaaa!!!
                        view.setDescription1("");
                    } else {
                        view.displayErrorMessage("Can not update!");
                    }
                }

            } else { //se non ho selezionato un id competenza
                view.displayErrorMessage("Select a competence to modify!");
            }
        }
    }

    public class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selezionato = view.getSelectedRow();//riga selezionata della tabella competenza
            
            if (selezionato != -1) {
                
                String id = view.getIdSelected(selezionato); //id competenza selezionata
                int idSkill = Integer.parseInt(id);
                
                // remove selected row from the model
                if (skmodel.deleteSkill(idSkill)) {
                    view.removeRow(selezionato);
                }
            } else //se non ho selezionato un id competenza
            {
                view.displayErrorMessage("Seleziona una competenza da cancellare!");
            }
        }
    }

    public class Back1Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prev.setVisible(true);
            view.setVisible(false);
        }
    }
}
