/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lyuba
 */
public abstract class UserFactory {
    
    
    public enum Role {MAINTAINER,PLANNER};
    
    public EmployeeModel build(Role role, String username,String password) {
        EmployeeModel v = selectRole(role);
        v.setUsername(username);
        v.setPassword(password);
        return v;
    }
    
    // This is the "factory method"
    protected abstract EmployeeModel selectRole(Role role);
    
    
}
