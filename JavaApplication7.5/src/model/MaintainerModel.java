/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.MaintainerDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lyuba
 */
public class MaintainerModel {
    private String username;
    private String password;

    public MaintainerModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public MaintainerModel findMaintainer(String username, String password, String role) throws Exception{
        MaintainerDAO dao = new MaintainerDAO();
        MaintainerModel ad = dao.findMaintainer(username, password, role);
        return ad;
    }
    
    public MaintainerModel findMaintainer(String username){
        MaintainerDAO dao = new MaintainerDAO();
        MaintainerModel ad = dao.findMaintainer(username);
        return ad;
    }
    
    public List<MaintainerModel> listMaintainers(){ //getsate
        MaintainerDAO dao = new MaintainerDAO();
        List<MaintainerModel> list = new ArrayList<>();
        list = dao.listMaintainers();
        return list;
    }
    
    public boolean deleteMaintainer(String username){
        MaintainerDAO dao = new MaintainerDAO();
        return dao.deleteMaintainer(username);
    }
    
    public boolean createMaintainer(String username, String password){
        MaintainerDAO dao = new MaintainerDAO();
        boolean ad = dao.createMaintainer(username, password);
        return ad;
    }

    public boolean updateMainatainerPassword(String username, String newpass){
        MaintainerDAO dao = new MaintainerDAO();
        return dao.updateMaintainerPassword(username, newpass);
    }

    @Override
    public String toString() {
            return "MaintainerModel{" + "username=" + username + ", password=" + password + '}';
    }
   
    public boolean addCompetence(String username,String description){
      MaintainerDAO dao = new MaintainerDAO();
      return dao.addCompetence(username,description);
    }
    
    public boolean hasCompetences(String username,String description){
        MaintainerDAO dao = new MaintainerDAO();
        return dao.hasCompetences(username,description);
    }
    
}
