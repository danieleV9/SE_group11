
import controller.LoginController;
import model.AdminModel;
import view.LoginView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dava9
 */
public class Prova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginView login = new LoginView();
        AdminModel admin = new AdminModel("","");
        LoginController contr = new LoginController(login,admin);
        login.setVisible(true);
    }
    
}
