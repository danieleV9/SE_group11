/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 *
 * @author jenni
 */
public abstract class EmployeeModel extends UserModel {
    
    // Used when notifying listeners so they know what has changed
    public static final String PASSWORD_CHANGE = "password";
    // This class is observable
    private PropertyChangeSupport changeSupport;

    public EmployeeModel(String username, String password) {
        super(username, password);
        changeSupport = new PropertyChangeSupport(this);
    }

    public abstract boolean createUser(String username, String password);

    public abstract boolean deleteUser(String username);

    public abstract boolean updateUserPassword(String username, String newpass);

    public abstract EmployeeModel findUsername(String username);

    public abstract <T extends EmployeeModel> List<T> listUsers();

    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        changeSupport.addPropertyChangeListener(pcl);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        changeSupport.removePropertyChangeListener(pcl);
    }

}
