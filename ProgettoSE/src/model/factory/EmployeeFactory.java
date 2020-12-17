/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

import model.EmployeeModel;
import model.MaintainerModel;
import model.PlannerModel;

/**
 *
 * @author lyuba
 */


public class EmployeeFactory extends UserFactory {

    @Override
    protected EmployeeModel selectRole(Role role) {
        if (role==null) return null; else switch (role) {
            case MAINTAINER:
                return new MaintainerModel("","");
            case PLANNER:
                return new PlannerModel("","");
            default:
                return null;
        }
    }
    
}