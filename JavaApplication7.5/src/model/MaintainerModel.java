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
public class MaintainerModel extends EmployeeModel {

    public MaintainerModel(String username, String password) {
        super(username, password);
    }

    @Override
    public UserModel findUser(String username, String password, String role) throws Exception {
        MaintainerDAO dao = new MaintainerDAO();
        MaintainerModel ad = (MaintainerModel) dao.findUser(username, password, role);
        return ad;
    }

    @Override
    public EmployeeModel findUsername(String username) {
        MaintainerDAO dao = new MaintainerDAO();
        MaintainerModel ad = (MaintainerModel) dao.findUsername(username);
        return ad;
    }

    public List<MaintainerModel> listMaintainers() { //getsate
        MaintainerDAO dao = new MaintainerDAO();
        List<MaintainerModel> list = new ArrayList<>();
        list = dao.listMaintainers();
        return list;
    }

    @Override
    public boolean deleteUser(String username) {
        MaintainerDAO dao = new MaintainerDAO();
        return dao.deleteUser(username);
    }

    @Override
    public boolean createUser(String username, String password) {
        MaintainerDAO dao = new MaintainerDAO();
        boolean ad = dao.createUser(username, password);
        return ad;
    }

    @Override
    public boolean updateUserPassword(String username, String newpass) {
        MaintainerDAO dao = new MaintainerDAO();
        return dao.updateUserPassword(username, newpass);
    }

    public boolean addCompetence(String username, int id) {
        MaintainerDAO dao = new MaintainerDAO();
        return dao.addCompetence(username, id);
    }

    public boolean hasCompetences(String username, int id) {
        MaintainerDAO dao = new MaintainerDAO();
        return dao.hasCompetences(username, id);
    }
    public boolean removeCompetence(String username, int id) {
        MaintainerDAO dao = new MaintainerDAO();
        return dao.removeCompetence(username, id);
    }
   
    public String getDisponibilitaGiorno(String username,int week, int day){ 
        MaintainerDAO dao=new MaintainerDAO();
        return dao.getDisponibilitaGiorno(username, week, day);
    }
    
    public int getNumGiorno(String username,int week, int day){ 
        MaintainerDAO dao=new MaintainerDAO();
        return dao.getNumGiorno(username, week, day);
    }
    
    public List<MaintainerModel> listMaintainersDisponibili(){
         MaintainerDAO dao=new MaintainerDAO();
         return dao.listMaintainersDisponibili();
    }
}
