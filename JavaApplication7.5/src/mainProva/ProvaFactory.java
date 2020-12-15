/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProva;

import model.factory.EmployeeFactory;
import model.EmployeeModel;
import model.factory.ManagerFactory;
import model.ManagerModel;
import model.UserModel;
import model.factory.UserFactory;

/**
 *
 * @author lyuba
 */
public class ProvaFactory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        UserFactory employeeFactory = new EmployeeFactory();
        
        UserModel employeePL = employeeFactory.build(UserFactory.Role.PLANNER,"ciao ","kuywhi");
        System.out.println(employeePL.toString());
        
        UserModel employeeMA = employeeFactory.build(UserFactory.Role.MAINTAINER,"tdryfgujkh","iuhqsjp");
        System.out.println(employeeMA.toString());
        
        //employeeMA.createUser(employeeMA.getUsername(), employeeMA.getPassword());
        
        UserFactory managerFactory = new ManagerFactory();
        
        UserModel managerAD = managerFactory.build(UserFactory.Role.ADMINISTRATOR, "aaa", "aaaaa");
        System.out.println(managerAD.toString());
        
    }
    
}
