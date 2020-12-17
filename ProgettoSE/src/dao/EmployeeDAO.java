/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.EmployeeModel;

/**
 *
 * @author jenni
 */
public interface EmployeeDAO extends UserDAO {

    public abstract EmployeeModel findUsername(String username);

    public abstract boolean createUser(String username, String password);

    public abstract boolean updateUserPassword(String username, String password);

    public abstract boolean usernameExists(String username);

    public abstract boolean deleteUser(String username);

}
