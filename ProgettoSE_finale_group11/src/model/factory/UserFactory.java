/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.factory;

import model.UserModel;

/**
 *
 * @author lyuba
 */
public abstract class UserFactory {
    
    public enum Category {MANAGER,EMPLOYEE};
    public enum Role {MAINTAINER,PLANNER,ADMINISTRATOR};
    
    public static UserModel make(Category category, Role role, String username, String password){
        UserFactory factory = null;
        if(category == Category.MANAGER){
            factory = new ManagerFactory();
        }
        else{
            factory = new EmployeeFactory();
        }
        return factory.build(role, username, password);
    }
    
    public UserModel build(Role role, String username,String password) {
        UserModel v = selectRole(role);
        v.setUsername(username);
        v.setPassword(password);
        return v;
    }
    
    // This is the "factory method"
    protected abstract UserModel selectRole(Role role);
    
    
}
