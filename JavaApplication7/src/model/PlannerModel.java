/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.PlannerDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author lyuba
 */
public class PlannerModel{
    private String username;
    private String password;

    public PlannerModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public PlannerModel findPlanner(String username, String password, String role) throws Exception{
        PlannerDAO dao = new PlannerDAO();
        PlannerModel ad = dao.findPlanner(username, password, role);
        return ad;
    }
    
    public PlannerModel findPlanner(String username){
        PlannerDAO dao = new PlannerDAO();
        PlannerModel ad = dao.findPlanner(username);
        return ad;
    }
    
    public List<PlannerModel> listPlanners(){ //getsate
        PlannerDAO dao = new PlannerDAO();
        List<PlannerModel> list = new ArrayList<>();
        list = dao.listPlanners();
        return list;
    }
    
    public boolean createPlanner(String username, String password){
        PlannerDAO dao = new PlannerDAO();
        boolean ad = dao.createPlanner(username, password);
        return ad;
    }
    
    public boolean deletePlanner(String username){
        PlannerDAO dao = new PlannerDAO();
        return dao.deletePlanner(username);
    }
    
    public boolean updatePlannerPassword(String username, String newpass){
        PlannerDAO dao = new PlannerDAO();
        return dao.updatePlannerPassword(username, newpass);
    }

    @Override
    public String toString() {
        return "PlannerModel{" + "username=" + username + ", password=" + password + '}';
    }

}
