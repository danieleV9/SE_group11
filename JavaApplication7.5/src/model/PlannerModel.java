/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PlannerDAO;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lyuba
 */
public class PlannerModel extends EmployeeModel implements Serializable{
    
    // Used when notifying listeners so they know what has changed
    public static final String PASSWORD_CHANGE = "password";

    private PlannerDAO dao;
    
    // This class is observable
    private PropertyChangeSupport changeSupport;

    public PlannerModel(String username, String password) {
        super(username, password);
        dao = new PlannerDAO();
        changeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public UserModel findUser(String username, String password) throws Exception {
        PlannerModel ad = (PlannerModel) dao.findUser(username, password);
        return ad;
    }

    @Override
    public EmployeeModel findUsername(String username) {
        PlannerModel ad = (PlannerModel) dao.findUsername(username);
        return ad;
    }

    @Override
    public boolean createUser(String username, String password) {
        boolean ad = dao.createUser(username, password);
        return ad;
    }

    @Override
    public boolean deleteUser(String username) {
        return dao.deleteUser(username);
    }

    @Override
    public boolean updateUserPassword(String username, String newpass) {
        System.out.println(this.getPassword()+"password iniziale");
        if (!this.getPassword().equals(newpass) ) {
            String previousPassword = this.getPassword();
            this.setPassword(newpass);
            changeSupport.firePropertyChange(PASSWORD_CHANGE,previousPassword,this.getPassword()); //questa è la nuova password
            System.out.println("Cambio password"+this.getPassword());
        }
        else
            System.out.println("Non Cambio password"+this.getPassword());
        return dao.updateUserPassword(username, newpass);
    }

    @Override
    public <PlannerModel extends EmployeeModel> List<PlannerModel> listUsers() {
        List<PlannerModel> list = new ArrayList<>();
        list = (List<PlannerModel>) dao.listPlanners();
        return list;
    }

    @Override
    public String toString() {
        return "PlannerModel{" + super.toString() + '}';
    }
    
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        changeSupport.addPropertyChangeListener(pcl);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        changeSupport.removePropertyChangeListener(pcl);
    }
    public Connection getDaoConnection() {
        return dao.getConn();
    }
}
