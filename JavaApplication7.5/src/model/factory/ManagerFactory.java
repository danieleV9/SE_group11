/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

import model.AdminModel;
import model.ManagerModel;

/**
 *
 * @author dava9
 */
public class ManagerFactory extends UserFactory{

    @Override
    protected ManagerModel selectRole(Role role) {
        if (role==null) return null; else switch (role) {
            case ADMINISTRATOR:
                return new AdminModel("","");
            default:
                return null;
        }
    }
    
}
