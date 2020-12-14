/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.MaintainerModel;
import model.PlannerModel;
import model.SkillModel;
import view.ModifyUserView;
import view.UsersListView;

/**
 *
 * @author lyuba
 */
public class UserModifyController {

    ModifyUserView view;
    UsersListView prev;
    PlannerModel modelpl;
    MaintainerModel modelma;
    private String username;
    private String role;

    public UserModifyController(ModifyUserView view, UsersListView prev, String username, String role) {
        this.view = view;
        this.prev = prev;
        this.username = username;
        this.role = role;
        this.modelpl =new PlannerModel("","");
        this.modelma= new MaintainerModel("","");
        if(getSelRole().equals("Planner")){
            
            modelpl=(PlannerModel) modelpl.findUsername(username);
            System.out.println(modelpl.toString());
        }else if(getSelRole().equals("Maintainer")){
            
            modelma = (MaintainerModel) modelma.findUsername(username);
            System.out.println(modelma.toString());
        }
        this.view.addModifyPassListener(new ModifyPassListener());//voglio modificare la pass
        this.view.addConfirmModListener(new ConfirmModListener()); //conferma modifica password
        this.view.addBackListener(new BackListener());//tasto indietro
        this.view.addDeleteCompListener(new DeleteCompListener()); //tasto cancella competenza
        this.view.addNewCompListener(new NewCompListener());//aggiungi competenze
        this.view.addConfirmCompListener(new ConfirmCompListener()); //conferma aggiunta competenze selezionate

    }
    
    public String getSelRole() {
        //System.out.println(this.role);
        return this.role;
    }

    public void fillTextField() {
        view.setUsername(this.username);
        if (getSelRole().equals("Planner")) {
            view.setPassword(this.modelpl.getPassword());
        } else if (getSelRole().equals("Maintainer")){ //se sei maintainer
            view.setPassword(this.modelma.getPassword());
            view.showMaintainerStuff(true);
        }
    }

    public class ModifyPassListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.showNewPassword(true);
        }
    }

    public class ConfirmModListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String newpass = view.getNewPassword();
            if (newpass.equals("")) {
                view.displayErrorMessage("Password can not be empty");
            } else if (newpass.equals(view.getPassword())) {
                view.displayErrorMessage("The new password must be different from the previous!");
            } else {
                if (getSelRole().equals("Planner")) {
                    if (modelpl.updateUserPassword(username, newpass)) {
                        view.showNewPassword(false);
                        view.displayErrorMessage("Password updated succesfully!");
                        view.setPassword(newpass);
                    }
                } else { //sei un maintainer
                    if (modelma.updateUserPassword(username, newpass)) {
                        view.showNewPassword(false);
                        view.displayErrorMessage("Password updated succesfully!");
                        view.setPassword(newpass);
                    }
                }
            }
        }
    }

    public class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            prev.setVisible(true);
        }
    }

    public class DeleteCompListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTable table = view.getjTable1();
            SkillModel skill = new SkillModel(0, "");
            MaintainerModel m = new MaintainerModel("", "");
            DefaultTableModel model = view.getModeltab();
            int selezionato = table.getSelectedRow();
            if (selezionato != -1) {
                String descrizione = table.getValueAt(selezionato, 0).toString();
                int id = skill.findSkill(descrizione).getIdSkill();
                if (m.removeCompetence(modelma.getUsername(), id)) {
                    model.removeRow(selezionato);
                }
            } else {
                view.displayErrorMessage("Select A Skill !");
            }
        }
    }

    public class NewCompListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.showAddComp(true);
            populateCompetences();
        }
    }

    public class ConfirmCompListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SkillModel skill = new SkillModel(0, "");
            String description = view.getjComboBox1().getSelectedItem().toString();
            int id = skill.findSkill(description).getIdSkill();
            if (description == "Select a skill") {
                view.displayErrorMessage("Select a skill!");
            } else if (modelma.hasCompetences(modelma.getUsername(), id)) {
                view.displayErrorMessage("The Maintainer has already this skill!");
            } else if (modelma.addCompetence(modelma.getUsername(), id)) {
                String[] newrow = {description};
                view.getModeltab().addRow(newrow);
                view.displayErrorMessage("Skill has been inserted succesfully!");
            }
        }
    }

    public void populateCompetences() {
        SkillModel skill = new SkillModel(0, "");
        List<SkillModel> list = skill.listSkills();
        for (int i = 0; i < list.size(); i++) {
            String competenza = list.get(i).toString();
            view.getjComboBox1().addItem(competenza);
            //System.out.println(competenza);
        }
    }

    public void populateCompetences(String username) {
        SkillModel skill = new SkillModel(0, "");
        List<SkillModel> list = skill.listSkillsMA(username);
        /*for (int i = 0; i < list.size(); i++) {
            String descrizioneCompetenza = list.get(i).getDescription();
            System.out.println(descrizioneCompetenza);
            String idcompetenza = String.valueOf(list.get(i).getIdSkill());
            String[] row = {descrizioneCompetenza};
            view.getModeltab().addRow(row);
            
        }*/
        for(SkillModel sk: list){
            String descr=sk.getDescription();
            String[] row = {descr};
            view.getModeltab().addRow(row);
            System.out.println(descr);
        }
    }
}
