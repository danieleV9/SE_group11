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
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.List;
import model.MaintainerModel;
import model.PlannerModel;
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
    private PlannerModel plmodel = new PlannerModel("", "");
    private MaintainerModel mamodel = new MaintainerModel("", "");

    public UsersListController(AdminHomeView prev, UsersListView view, AdminModel model) {
        this.prevView = prev;
        this.view = view;
        this.model = model;
        this.view.addCreateListener(new CreateListener());
        this.view.addSelectedRowListener(new ClickOnTableListener()); //selezione tabella planner
        this.view.addSelectedRowListener1(new ClickOnTableListener1()); //selezione tabella mainteiner
        this.view.addNewUserListener(new NewUserListener());
        this.view.addModifyListener(new ModifyListener());
        this.view.addDeleteListener(new DeleteListener());
        this.view.addBackListener(new BackListener());
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

    public class ClickOnTableListener implements MouseListener {

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
            System.out.println("Ho cliccato sulla tabella planner");
            int r = view.getSelectedRow();
            System.out.println(r);
            String username = view.getUsernameSelected(r);
            System.out.println(username);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public class ClickOnTableListener1 implements MouseListener {

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
            System.out.println("Ho cliccato sulla tabella maintainer");
            int r = view.getSelectedRow1();
            System.out.println(r);

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            //System.out.println(selezionato);
            int selezionatoma = view.getSelectedRow1();//riga selezionata della tabella maintainer

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
                //controllermod.populateCompetences();
            } else if (selezionatopl != -1) {
                selezionatoma = -1;
                role = "Planner";
                username = view.getUsernameSelected(selezionatopl); //username planner selezionato
                ModifyUserView viewmod = new ModifyUserView();
                UserModifyController controllermod = new UserModifyController(viewmod, view, username, role);
                view.setVisible(false);
                viewmod.setVisible(true);
                controllermod.fillTextField();

            } else //se non ho selezionato un planner o un maintainer
            {
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
            //System.out.println(selezionato);
            int selezionatoma = view.getSelectedRow1();//riga selezionata della tabella maintainer

            if (selezionatoma != -1) {
                selezionatopl = -1;
                // ActivityDao dao= new ActivityDao();
                String username = view.getUsernameSelected1(selezionatoma); //username maintainer selezionato
                //System.out.println(username);
                // remove selected row from the model
                if (mamodel.deleteUser(username)) {
                    view.removeRow1(selezionatoma);
                }
            } else if (selezionatopl != -1) {
                selezionatoma = -1;
                // ActivityDao dao= new ActivityDao();
                String username = view.getUsernameSelected(selezionatopl); //username planner selezionato
                //System.out.println(username);
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
