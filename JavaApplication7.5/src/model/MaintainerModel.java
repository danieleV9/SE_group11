/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.MaintainerDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lyuba
 */
public class MaintainerModel extends EmployeeModel implements Serializable {

    private MaintainerDAO dao;

    public MaintainerModel(String username, String password) {
        super(username, password);
        dao = new MaintainerDAO();
    }

    @Override
    public UserModel findUser(String username, String password) throws Exception {
        MaintainerModel ad = (MaintainerModel) dao.findUser(username, password);
        return ad;
    }

    @Override
    public EmployeeModel findUsername(String username) {
        MaintainerModel ad = (MaintainerModel) dao.findUsername(username);
        return ad;
    }

    @Override
    public boolean deleteUser(String username) {
        return dao.deleteUser(username);
    }

    @Override
    public boolean createUser(String username, String password) {
        boolean ad = dao.createUser(username, password);
        return ad;
    }

    @Override
    public boolean updateUserPassword(String username, String newpass) {
        if (!this.getPassword().equals(newpass) ) {
            String previousPassword = this.getPassword();
            this.setPassword(newpass);
            super.getChangeSupport().firePropertyChange(PASSWORD_CHANGE,previousPassword,this.getPassword()); //questa è la nuova password
        }
        
        return dao.updateUserPassword(username, newpass);
    }

    public boolean addCompetence(String username, int id) {
        return dao.addCompetence(username, id);
    }

    public boolean hasCompetences(String username, int id) {
        return dao.hasCompetences(username, id);
    }

    public boolean removeCompetence(String username, int id) {
        return dao.removeCompetence(username, id);
    }

    public String getDisponibilitaGiorno(String username, int week, int day) {
        return dao.getDisponibilitaGiorno(username, week, day);
    }

    public int getNumGiorno(String username, int week, int day) {
        return dao.getNumGiorno(username, week, day);
    }

    public List<MaintainerModel> listMaintainersDisponibili() {
        return dao.listMaintainersDisponibili();
    }

    @Override
    public <MaintainerModel extends EmployeeModel> List<MaintainerModel> listUsers() {
        List<MaintainerModel> list = new ArrayList<>();
        list = (List<MaintainerModel>) dao.listMaintainers();
        return list;
    }
    
    @Override
    public String toString() {
        return "MaintainerModel{" + super.toString()+'}';
    }

    public Connection getDaoConnection() {
        return dao.getConn();
    }
    
    
}
