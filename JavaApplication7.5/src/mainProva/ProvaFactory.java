/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProva;

import model.EmployeeFactory;
import model.EmployeeModel;
import model.UserFactory;

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
        EmployeeModel employeePL = employeeFactory.build
                (EmployeeFactory.Role.PLANNER,
                 "ciao ","kuywhi");
        System.out.println(employeePL.toString());
        
        EmployeeModel employeeMA = employeeFactory.build
                (EmployeeFactory.Role.MAINTAINER,
                 "tdryfgujkh","iuhqsjp");
        System.out.println(employeeMA.toString());
        employeeMA.createUser(employeeMA.getUsername(), employeeMA.getPassword());
        
        
    }
    
}
