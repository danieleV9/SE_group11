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
        this.view.addSelectedRowListener(new ClickOnTableListener());
        this.view.Back1Listener(new Back1Listener());
    }

    public class ClickOnTableListener implements MouseListener {

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("Ho cliccato sulla tabella competenze");
            int r = view.getSelectedRow();
            System.out.println("indice"+r);
            int i = Integer.parseInt(view.getIdSelected(r));
            System.out.println("id"+i);
            String descrizione1= view.getDescriptionSelected(r);
             System.out.println("descrizione"+descrizione1);

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
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
            String description = "";
            try {
                description = view.getDescription();
                if (description.equals("")) {
                    view.displayErrorMessage("Insert a description!", "Attention");
                    System.out.println("query return null");
                } else {
                    skmodel.insertSkill(description);
                    view.displaySuccessfullyMessage("Skill Created Succesfully!");
                }
            } catch (Exception ex) {
                System.out.println("" + ex);
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
            AdminHomeView ad2 = new AdminHomeView();
            AdminHomeController contr = new AdminHomeController(ad2, model, "admin");
            ad2.setVisible(true);
            view.setVisible(false);
        }
    }

    public class ConfirmChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idSkill = 0;
            boolean x= false;
            String vecchio="";
            int selezionato = view.getSelectedRow();//riga selezionata della tabella competenza
            if (selezionato != -1) {
                int i = Integer.parseInt(view.getIdSelected(selezionato));
                vecchio=view.getDescriptionSelected(selezionato);
                //System.out.println("id che voglio modificare:"+i);
                idSkill = i;
                String descrizione = view.getDescription1();
                //System.out.println("descrizione:"+descrizione);
                if (descrizione.equals("")) {
                    view.displayErrorMessage("The description can not be empty!");
                } else if (descrizione.equals(vecchio)) {
                    view.displayErrorMessage("New competence must be different from the previous");
                }
                else {
                    x=skmodel.modifySkill(idSkill, descrizione);
                    
                    if (x) {
                    //view.showAddComp(true);
                    view.displayErrorMessage("Updated succesfully");
                    view.setDescriptionSelected(selezionato,1, descrizione); //visualizza modificaaaaa!!!
                    view.setDescription1("");
                    }else 
                    view.displayErrorMessage("Can not update!");
                }
                
            } else{ //se non ho selezionato un id competenza
                view.displayErrorMessage("Select a competence to modify!");
            }
        }
    }

    public class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selezionato = view.getSelectedRow();//riga selezionata della tabella competenza
            //System.out.println(selezionato);
            if (selezionato != -1) {
                // ActivityDao dao= new ActivityDao();
                String id = view.getIdSelected(selezionato); //id competenza selezionata
                int idSkill = Integer.parseInt(id);
                //System.out.println(username);
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
    
    public class Back1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminHomeView ad2 = new AdminHomeView();
            AdminHomeController contr = new AdminHomeController(ad2, model, "admin");
            ad2.setVisible(true);
            view.setVisible(false);
        }
    }
}
