/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.MaintainerModel;
import model.PlannerModel;
import view.AdminHomeView;
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
        this.modelpl = new PlannerModel("","");
        this.modelma = new MaintainerModel("","");
        this.view.addModifyPassListener(new ModifyPassListener());//voglio modificare la pass
        this.view.addConfirmModListener(new ConfirmModListener()); //conferma modifica password
        this.view.addBackListener(new BackListener());//tasto indietro
        this.view.addDeleteCompListener(new DeleteCompListener()); //tasto cancella competenza
        this.view.addNewCompListener(new NewCompListener());//aggiungi competenze
        this.view.addConfirmCompListener(new ConfirmCompListener()); //conferma aggiunta competenze selezionate
        
    }

    public String getSelUsername() {
        return username;
    }

    public String getSelRole() {
        return role;
    }
    
    public void fillTextField(){
        view.setUsername(getSelUsername());
        if(getSelRole().equals("Planner")){
            view.setPassword(modelpl.findPlanner(getSelUsername()).getPassword());
        }
        else{ //se sei maintainer
            view.setPassword(modelma.findMaintainer(getSelUsername()).getPassword());
            view.showMaintainerStuff(true);
        }
    }
    
    public class ModifyPassListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            view.showNewPassword(true);
        }
    }
    public class ConfirmModListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String newpass = view.getNewPassword();
            if(newpass.equals("")){
                view.displayErrorMessage("La password non può essere vuota");
            }
            else if(newpass.equals(view.getPassword())){
                view.displayErrorMessage("La nuova password deve essere diversa dalla precedente");
            }
            else{
                if(getSelRole().equals("Planner")){
                    if(modelpl.updatePlannerPassword(username, newpass)){
                        view.showNewPassword(false);
                        view.displayErrorMessage("Password modificata con successo");
                        view.setPassword(newpass);
                    }
                }
                else{ //sei un maintainer
                    if(modelma.updateMainatainerPassword(username, newpass)){
                        view.showNewPassword(false);
                        view.displayErrorMessage("Password modificata con successo");
                        view.setPassword(newpass);
                    }
                }
            }
        }
    }
    public class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            view.setVisible(false);
            prev = new UsersListView();
            AdminHomeView viewhome = new AdminHomeView();
            AdminHomeController viewcontroller = new AdminHomeController(viewhome,null,"admin");
            UsersListController prevController = new UsersListController(viewhome,prev,null);
            prevController.populateTables();
            prev.setVisible(true);
        }
    }
    public class DeleteCompListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("ciao");
        }
    }
    public class NewCompListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            view.showAddComp(true);
        }
    }
    public class ConfirmCompListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("ciao");
        }
    }
}
