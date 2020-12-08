/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jenni
 */
public abstract class EmployeeModel extends UserModel {

    public EmployeeModel(String username, String password) {
        super(username, password);
    }

    public abstract boolean createUser(String username, String password);

    public abstract boolean deleteUser(String username);

    public abstract boolean updateUserPassword(String username, String newpass);

    public abstract EmployeeModel findUsername(String username);

}
