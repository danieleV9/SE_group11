/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AdminModel;
import view.UsersListView;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.MaintainerModel;
import model.PlannerModel;
import model.factory.EmployeeFactory;
import model.factory.UserFactory;
import view.AdminHomeView;
import view.ModifyUserView;

/**
 *
 * @author dava9
 */
public class UsersListController {

    private final UsersListView view;
    private final AdminHomeView prevView;//pagina precedente
    private final AdminModel model;
    UserFactory employeeFactory = new EmployeeFactory();
    PlannerModel plmodel =  (PlannerModel) employeeFactory.build(UserFactory.Role.PLANNER,"","");
    MaintainerModel mamodel = (MaintainerModel) employeeFactory.build(UserFactory.Role.MAINTAINER,"","");

    public UsersListController(AdminHomeView prev, UsersListView view, AdminModel model) {
        this.prevView = prev;
        this.view = view;
        this.model = model;
        this.view.addCreateListener(new CreateListener());
        this.view.addNewUserListener(new NewUserListener());
        this.view.addModifyListener(new ModifyListener());
        this.view.addDeleteListener(new DeleteListener());
        this.view.addBackListener(new BackListener());
        this.view.addChangedListener(new ChangedListener());
    }
    
    public class ChangedListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            if(view.getTabbed().getSelectedIndex() == 0){
                view.getTableMaintainer().setEnabled(false);
                view.getTablePlanner().setEnabled(true);
            }
            else if(view.getTabbed().getSelectedIndex() == 1){
                view.getTableMaintainer().setEnabled(true);
                view.getTablePlanner().setEnabled(false);
            }
        }
        
    }

    public void populateTables() {
        List<PlannerModel> listpl = plmodel.listUsers();
        List<MaintainerModel> listma = mamodel.listUsers();
        for (int i = 0; i < listpl.size(); i++) {
            plmodel = listpl.get(i);
            String array[] = {plmodel.getUsername(), plmodel.getPassword()};
            view.getTable().addRow(array);
        }

        for (int j = 0; j < listma.size(); j++) {
            mamodel = listma.get(j);
            String array[] = {mamodel.getUsername(), mamodel.getPassword()};
            view.getTable1().addRow(array);

        }
    }

    public class NewUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            view.showCreateFields();
        }
    }

    public class ModifyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String role = "";
            String username = "";
            int selezionatopl = view.getSelectedRow();//riga selezionata della tabella planner
            int selezionatoma = view.getSelectedRow1();//riga selezionata della tabella maintainer
            if(view.getTabbed().getSelectedIndex()==1){
                if (selezionatoma != -1) {
                    selezionatopl = -1;
                    role = "Maintainer";
                    username = view.getUsernameSelected1(selezionatoma); //username maintainer selezionato
                    ModifyUserView viewmod = new ModifyUserView();
                    UserModifyController controllermod = new UserModifyController(viewmod, view, username, role);
                    view.setVisible(false);
                    viewmod.setVisible(true);
                    controllermod.fillTextField();
                    controllermod.populateCompetences(username);
                    controllermod.populateCompetences();
                }
            }   else if(view.getTabbed().getSelectedIndex()==0){
                    if (selezionatopl != -1) {
                    selezionatoma = -1;
                    role = "Planner";
                    username = view.getUsernameSelected(selezionatopl); //username planner selezionato
                    ModifyUserView viewmod = new ModifyUserView();
                    UserModifyController controllermod = new UserModifyController(viewmod, view, username, role);
                    view.setVisible(false);
                    viewmod.setVisible(true);
                    controllermod.fillTextField();
                    }
            }   else{ //se non ho selezionato un planner o un maintainer
                view.displayErrorMessage("Select a user to modify!");
            }
            
        }
    }

    public class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //AdminHomeView home= new AdminHomeView(prev.getUsername());
            //AdminHomeController contr = new AdminHomeController(home,model);
            prevView.setVisible(true);
            view.setVisible(false);
        }
    }

    public class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selezionatopl = view.getSelectedRow();//riga selezionata della tabella planner
            int selezionatoma = view.getSelectedRow1();//riga selezionata della tabella maintainer

            if (selezionatoma != -1) {
                selezionatopl = -1;
                String username = view.getUsernameSelected1(selezionatoma); //username maintainer selezionato
                // remove selected row from the model
                if (mamodel.deleteUser(username)) {
                    view.removeRow1(selezionatoma);
                }
            } else if (selezionatopl != -1) {
                selezionatoma = -1;
                String username = view.getUsernameSelected(selezionatopl); //username planner selezionato
                // remove selected row from the model
                if (plmodel.deleteUser(username)) {
                    view.removeRow(selezionatopl);
                }
            } else //se non ho selezionato un planner o un maintainer
            {
                view.displayErrorMessage("Select a user to modify!");
            }
        }
    }

    public class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsernameCreate();
            String password = view.getPasswordCreate();
            if (username.equals("") || password.equals("")) {
                view.displayErrorMessage("Fill all fields!");
            } else {
                if (view.selectedPlannerCreate()) {
                    boolean result = plmodel.createUser(username, password);
                    if (result == false) {
                        view.displayErrorMessage("This username exists yet.");
                    }
                    if (result == true) {
                        view.displayErrorMessage("Planner created succesfully!");
                        String[] row = {username, password};
                        view.getTable().addRow(row);
                    }
                } else if (view.selectedMaintainerCreate()) {
                    boolean result = mamodel.createUser(username, password);
                    if (result == false) {
                        view.displayErrorMessage("This username exists yet.");
                    }
                    if (result == true) {
                        view.displayErrorMessage("Maintainer created succesfully!");
                        String[] row = {username, password};
                        view.getTable1().addRow(row);
                    }
                } else {
                    view.displayErrorMessage("Select a role!");
                }
            }
        }
    }

}
