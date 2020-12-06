package mainProva;

import controller.LoginController;
import model.AdminModel;
import model.MaintainerModel;
import model.PlannerModel;
import view.LoginView;
import view.UsersListView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dava9anner1
 */
public class Prova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginView login = new LoginView();
        UsersListView users = new UsersListView();
        AdminModel admin = new AdminModel("", "");
        MaintainerModel maintainer = new MaintainerModel("", "");
        PlannerModel planner = new PlannerModel("", "");
        LoginController contr = new LoginController(login, admin, planner, maintainer);

    }

}
